package model.dao.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;

import model.dao.mybatis.mapper.CareMapper;
import model.dao.mybatis.mapper.ServiceMapper;
import model.dto.Care;
import model.dto.CareDetails;
import model.dto.CareRecord;

public class CareDAO {
	private SqlSessionFactory sqlSessionFactory;
	
	public CareDAO() {
		String resource = "mybatis-config.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	/* 보호자 및 특정 돌보미의 돌봄 스케쥴 조회 */
	public List<Care> findCareSchedules(String memberId, String sitterId) {
		SqlSession sqlSession = sqlSessionFactory.openSession(true);
		try {
			List<Care> careList = sqlSession.getMapper(CareMapper.class).findCareSchedules(memberId, sitterId);
			return careList;
		} finally {
			sqlSession.close();
		}
	}
	
	/* 특정 돌보미의 돌봄 스케쥴 조회 */
	public List<Care> findSitterCareSchedules(String memberId, String sitterId) {
		SqlSession sqlSession = sqlSessionFactory.openSession(true);
		try {
			List<Care> careList = sqlSession.getMapper(CareMapper.class).findSitterCareSchedules(memberId, sitterId);
			return careList;
		} finally {
			sqlSession.close();
		}
	}
	
	/* 돌봄 예약내역 조회 */
	public Care findReservation(int careId) {
		SqlSession sqlSession = sqlSessionFactory.openSession(true);
		try {
			Care care = sqlSession.getMapper(CareMapper.class).findReservation(careId);
			return care;
		} finally {
			sqlSession.close();
		}
	}
	
	/* 보호자-돌보미 간 돌봄 완료 내역 반환 */
	public List<Care> findCareList(String memberId, String sitterId) {
		SqlSession sqlSession = sqlSessionFactory.openSession(true);
		try {
			List<Care> careList = sqlSession.getMapper(CareMapper.class).findCareList(memberId, sitterId);
			return careList;
		} finally {
			sqlSession.close();
		}
	}
	
	/* 돌봄 내역 생성 */
	public int createCare(Care care) {
		SqlSession sqlSession = sqlSessionFactory.openSession(true);
		try {
			int result1 = sqlSession.getMapper(CareMapper.class).createCare(care);
			int careId = care.getId();
			for (CareDetails careDetail : care.getCareList()) {
				// careId 추가
				careDetail.getCareInfo().setId(careId);
				// recvId 추가
				String recvId = Integer.toString(careId) + careDetail.getCarePet().getId().replaceAll("[^0-9]", "") 
						+ careDetail.getServiceInfo().getId().replaceAll("[^0-9]", "");
				careDetail.setId(recvId);
			}
			int result2 = sqlSession.getMapper(ServiceMapper.class).createReceiveServices(care.getCareList());

			if (result1 > 0 && result2 > 0)
				sqlSession.commit();
			else
				sqlSession.rollback();
			return result2;
			
		} finally {
			sqlSession.close();
		}
	}
	
	/* 돌봄일지 리스트 반환 */
	public Care findCareRecordsByCare(int careId) {
		SqlSession sqlSession = sqlSessionFactory.openSession(true);
		try {
			return sqlSession.getMapper(CareMapper.class).getCareRecordByCareId(careId);
		} finally {
			sqlSession.close();
		}
	}
	
	/* 돌봄일지 개수 확인 후 돌봄상태 '돌봄완료'로 변겅 */
	public int updageCareStatusToZ(int careId) {
		SqlSession sqlSession = sqlSessionFactory.openSession(true);
		try {
			Map<String, Integer> recordCountMap = sqlSession.getMapper(CareMapper.class).findCareRecordCount(careId);
			int totalDays = Integer.parseInt(String.valueOf(recordCountMap.get("dayCount"))) + 1;
			int writeCareRecords = Integer.parseInt(String.valueOf(recordCountMap.get("countCareRecord")));
			if (totalDays > writeCareRecords)
				return -1;
			else {
				int result = sqlSession.getMapper(CareMapper.class).updateCareStatus(careId, "Z");
				if (result > 0)
					sqlSession.commit();
				else
					sqlSession.rollback();
				return result;
			}
		} finally {
			sqlSession.close();
		}
	}
	
	/* 돌봄일지 추가 */
	public int createCareRecord(CareRecord careRecord) {
		SqlSession sqlSession = sqlSessionFactory.openSession(true);
		try {
			int result1 = sqlSession.getMapper(CareMapper.class).createCareRecord(careRecord);
			int recordId = careRecord.getId();
			// 돌보미가 체크한 제공 서비스 리스트
			for (CareDetails careDetail : careRecord.getCheckList()) {
				// recordId 추가
				careDetail.setRecordId(recordId);
				careDetail.setCheck("Y");
			}
			// 돌보미가 미체크한 제공 서비스 리스트
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("careId", careRecord.getCareInfo().getId());
			param.put("careDetailsList", careRecord.getCheckList());
			List<CareDetails> nReceiveServices = sqlSession.getMapper(ServiceMapper.class)
					.findNReceiveServices(param);
			for (CareDetails careDetail : nReceiveServices) {
				// recordId 추가
				careDetail.setRecordId(recordId);
				careDetail.setCheck("N");
				careRecord.getCheckList().add(careDetail);
			}
			
			int result2 = sqlSession.getMapper(ServiceMapper.class).createCareCheckList(careRecord.getCheckList());
			
			if (result1 > 0 && result2 > 0)
				sqlSession.commit();
			else
				sqlSession.rollback();
			return result2;
			
		} finally {
			sqlSession.close();
		}
	}
	
	/* 돌봄 내역 삭제 */
	public int deleteCare(int careId) {
		SqlSession sqlSession = sqlSessionFactory.openSession(true);
		try {
			return sqlSession.getMapper(CareMapper.class).deleteCare(careId);
		} finally {
			sqlSession.close();
		}
	}
	
	/* 제공받는 서비스 했는지 확인(체크) */
	public String getCheckInfo(String rcvId) {
		SqlSession sqlSession = sqlSessionFactory.openSession(true);
		try {
			String check = sqlSession.getMapper(CareMapper.class).getCheckInfo(rcvId);
			return check;
		} finally {
			sqlSession.close();
		}
	}
	
	/* 돌봄 진행 상태로 업데이트 */
	public int updateCareSchedule(Care care) {
		SqlSession sqlSession = sqlSessionFactory.openSession(true);
		try {
			return sqlSession.getMapper(CareMapper.class).updateCareStatus(care.getId(), "Y");
		} finally {
			sqlSession.close();
		}
	}
}
