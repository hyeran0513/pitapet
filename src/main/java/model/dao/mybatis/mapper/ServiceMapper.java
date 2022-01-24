package model.dao.mybatis.mapper;

import java.util.List;
import java.util.Map;

import model.dto.CareDetails;
import model.dto.Service;

public interface ServiceMapper {
	
	/* 모든 서비스타입 리스트 조회 */
	public List<Service> findAllServiceKinds();
	
	/* 제공가능한 서비스 리스트 조회 */
	public List<Service> findProvideServicesBySitter(String sitterId);
	
	/* 돌봄내역에 대한 제공서비스 리스트 조회 */
	public List<CareDetails> findReceiveServicesByCare(int careId);
	
	/* 특정 반려동물에 대해 제공 서비스가 있는지 확인 */
	public int countReceiveServiceByPetId(String petId);
	
	/* 돌봄일지 작성 시 돌보미가 체크하지 않은 서비스 리스트 */
	public List<CareDetails> findNReceiveServices(Map<String, Object> param);
	
	/* 제공받을 서비스 삽입 */
	public int createReceiveServices(List<CareDetails> rcevServices);
	
	/* 제공받을 서비스 삭제 */
	public int deleteReceiveService(int careId);
	
	/* 돌보미 서비스 추가 */
	public int addProvideService(List<Map<String, String>> provideServices);
	
	/* 서버스 제공 체크 리스트 추가  */
	public int createCareCheckList(List<CareDetails> careCheckList);
}
