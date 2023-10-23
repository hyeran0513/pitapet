package model.dao.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;

import model.dao.mybatis.mapper.ServiceMapper;
import model.dto.CareDetails;
import model.dto.Service;

public class ServiceDAO {
	private SqlSessionFactory sqlSessionFactory;
	
	public ServiceDAO() {
		String resource = "mybatis-config.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	/* 모든 서비스 타입 리스트 반환 */
	public List<Service> findAllServiceList() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			List<Service> services = sqlSession.getMapper(ServiceMapper.class).findAllServiceKinds();
			System.out.println(services);
			return services;
		} finally {
			sqlSession.close();
		}
	}
	
	/* 특정 돌보미의 제공가능 서비스 정보를 검색하여 List에 저장 및 반환 */
	public List<Service> findProvideServiceList(String sitterId) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			List<Service> services = sqlSession.getMapper(ServiceMapper.class).findProvideServicesBySitter(sitterId);
			System.out.println(services);
			return services;
		} finally {
			sqlSession.close();
		}
	}
	
	/* 특정 돌봄내역에 대한 제공 서비스 정보를 검색해 List에 저장 및 반환 */
	public List<CareDetails> findReceiveServiceList(int careId) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			List<CareDetails> services = sqlSession.getMapper(ServiceMapper.class).findReceiveServicesByCare(careId);
			return services;
		} finally {
			sqlSession.close();
		}
	}
	
	/* 특정 반려동물에 대해 제공 서비스가 있는지 확인 */
	public int countReceiveServiceByPetId(String petId) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int count = sqlSession.getMapper(ServiceMapper.class).countReceiveServiceByPetId(petId);
			return count;
		} finally {
			sqlSession.close();
		}
	}
	
	/* 특정 돌봄 내역 삭제 시 참조된 모든 제공 서비스 레코드 삭제 */
	public int deleteReceiveService(int careId) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int result = sqlSession.getMapper(ServiceMapper.class).deleteReceiveService(careId);
			if(result > 0)
				sqlSession.commit();
			else
				sqlSession.rollback();
			return result;
		} finally {
			sqlSession.close();
		}
	}
	
	/* 돌보미 서비스 추가 */
	public int addProvideService(List<Service> provideServices, String sitterId) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			List<Map<String, String>> prvdServices = new ArrayList<Map<String, String>>();
			for (Service s : provideServices) {
				Map<String, String> param = new HashMap<String, String>();
				param.put("sitterId", sitterId);
				param.put("service", s.getId());
				prvdServices.add(param);
			}
			int result = sqlSession.getMapper(ServiceMapper.class).addProvideService(prvdServices);
			
			if (result > 0)
				sqlSession.commit();
			else
				sqlSession.rollback();
			
			return result;
			
		} finally {
			sqlSession.close();
		}
	}
}
