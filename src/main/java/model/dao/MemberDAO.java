package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.dto.Member;

public class MemberDAO {
private JDBCUtil jdbcUtil = null;
   
   public MemberDAO() {         
      jdbcUtil = new JDBCUtil();   // JDBCUtil 객체 생성
   }
   
   /* 특정 회원정보 반환 */
   public Member findMember(String memberId) throws SQLException {
        String sql = "SELECT password, name, birth, gender, email, phone, address, identity, img_src "
                 + "FROM member JOIN attachment USING (member_id) "
                 + "WHERE member_id=? AND category_id = 'AtchId04'";              
      jdbcUtil.setSqlAndParameters(sql, new Object[] {memberId});   // JDBCUtil에 query문과 매개 변수 설정

      try {
         ResultSet rs = jdbcUtil.executeQuery();      
         if (rs.next()) {                  
            Member member = new Member(      
               memberId,
               rs.getString("password"),
               rs.getString("name"),
               rs.getString("birth"),
               rs.getString("gender"),
               rs.getString("email"),               
               rs.getString("phone"),
               rs.getString("address"),
               rs.getString("identity"),
               rs.getString("img_src")
            );
            return member;
         }
      } catch (Exception ex) {
         ex.printStackTrace();
      } finally {
         jdbcUtil.close();      
      }
      return null;
   }
   
   /* 회원정보 생성 */
   public int createMember(Member newMember) throws SQLException {
		  String sql = "INSERT ALL "
				  + "INTO MEMBER VALUES (?, ?, ?, TO_DATE(?, 'YYYYMMDD'), ?, ?, ?, ?, ?) "
		  		  + "INTO ATTACHMENT VALUES (?, ?, ?) "
				  + "SELECT * FROM DUAL";

		  Object[] param = new Object[] {newMember.getId(), newMember.getPassword(), newMember.getName(), newMember.getBirth(),
				  newMember.getGender(), newMember.getEmail(), newMember.getPhone(), newMember.getAddress(), newMember.getIdentity(),
				  newMember.getProfileImage(), newMember.getId(), "AtchId04"};

		  jdbcUtil.setSqlAndParameters(sql, param);
		  
	      try {
	         int recordCount = jdbcUtil.executeUpdate();
	         
	         return recordCount;
	      } catch (Exception ex) {
	          jdbcUtil.rollback();
	    	  ex.printStackTrace();
	      } finally {
	    	 jdbcUtil.commit();
	         jdbcUtil.close();      
	      }
	      return 0;
	   }
   
   /* 회원 정보 업데이트 */
   public int update(Member updateInfo) throws SQLException {
	    String sql = null;
	    Object[] param = null;
	    
	    if (updateInfo.getPassword() == null) {
	    	sql = "UPDATE member "
					+ "SET email=?, phone=?, address=? "
					+ "WHERE member_id=?";
	    	
	    	param = new Object[] {updateInfo.getEmail(), 
					updateInfo.getPhone(), updateInfo.getAddress(), updateInfo.getId()};
	    }
	    else {
	    	sql = "UPDATE member "
					+ "SET password=?, email=?, phone=?, address=? "
					+ "WHERE member_id=?";
	    	
	    	param = new Object[] {updateInfo.getPassword(), updateInfo.getEmail(), 
					updateInfo.getPhone(), updateInfo.getAddress(), updateInfo.getId()};
	    }
						
		jdbcUtil.setSqlAndParameters(sql, param);
			
		try {				
			int result = jdbcUtil.executeUpdate();
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}		
		return 0;
	}
   
   /* 특정 회원의 프로필 사진 삭제 */
   public int deleteProfilePic (String memberId) throws SQLException {
	   String like = "%profile-" + memberId + "-%";
	   String sql = "DELETE FROM attachment "
				+ "WHERE member_id = ? AND img_src LIKE ?";     
		jdbcUtil.setSqlAndParameters(sql, new Object[] {memberId, like});	
						
		try {
			int rs = jdbcUtil.executeUpdate();			
			return rs;					
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}		
		return 0;
   }
}
