package model.dao.mybatis.mapper;

import org.apache.ibatis.annotations.Param;

import model.dto.Member;

public interface MemberMapper {
	
	/* 특정 회원정보 반환 */
	public Member selectMember (String memberId);
	
	/* 회원정보 생성 */
	public int createMember (Member newMember);
	
	/* 회원정보 업데이트 */
	public int updateMember (Member updateInfo);
	
	/* 프로필 사진 삭제 */
	public int deleteProfilePic (@Param("memberId") String memberId, @Param("like") String like);
	
	/* 프로필 사진 추가 */
	public int addAttachment (@Param("memberId") String memberId, @Param("image") String image);
}
