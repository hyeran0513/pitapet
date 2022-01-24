package model.dao.mybatis.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import model.dto.Care;
import model.dto.CareRecord;

public interface CareMapper {

	/* 보호자 및 특정 돌보미의 돌봄 스케쥴 조회 */
	public List<Care> findCareSchedules(@Param("memberId") String memberId, @Param("sitterId") String sitterId);
	
	/* 특정 돌보미의 돌봄 스케쥴 조회 */
	public List<Care> findSitterCareSchedules(@Param("memberId") String memberId, @Param("sitterId") String sitterId);
	
	/* 돌봄 예약내역 조회 */
	public Care findReservation(int careId);
	
	/* 보호자-돌보미 간 돌봄 완료 내역 반환 */
	public List<Care> findCareList(@Param("memberId") String memberId, @Param("sitterId") String sitterId);
	
	/* 돌봄 내역 생성 */
	public int createCare(Care care);
	
	/* 돌봄일지 리스트 조회 */
	public Care getCareRecordByCareId(int careId);
	
	/* 돌봄일지 작성 개수 반환 */
	public Map<String, Integer> findCareRecordCount(int careId);
	
	/* 돌봄상태 변경 */
	public int updateCareStatus(@Param("careId") int careId, @Param("status") String status);
	
	/* 돌봄일지 추가 */
	public int createCareRecord(CareRecord careRecord);
	
	/* 돌봄 내역 삭제 */
	public int deleteCare(int careId);
	
	/* 제공받는 서비스 했는지 확인(체크) */
	public String getCheckInfo(String rcvId);
}
