package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dto.LikeList;
import model.dto.Member;
import model.dto.PetSitter;

public class LikeListDAO {
   private JDBCUtil jdbcUtil = null;
   
   public LikeListDAO() {         
      jdbcUtil = new JDBCUtil();   // JDBCUtil 객체 생성
   }
   
   /* 특정 회원의 찜 목록 조회 */
   public List<LikeList> findLikeListOfMember(String memberId) throws SQLException {
      String sql = "SELECT sitter_id "
                 + "FROM likelist "        
             + "WHERE member_id = ?";
      
      jdbcUtil.setSqlAndParameters(sql, new Object[] {memberId});
         try {
            ResultSet rs = jdbcUtil.executeQuery();            
               List<LikeList> likeList = new ArrayList<>();
               while (rs.next()) {
                  LikeList like = new LikeList(
                		  memberId, 
                        new PetSitter(
                              new Member(rs.getString("sitter_id"))));
                  likeList.add(like);
              }
               return likeList;
         } catch (Exception ex) {
            ex.printStackTrace();
         } finally {
            jdbcUtil.close();      
         }
         return null;
   }
   
	/* 좋아요 추가 */
	public int add(String memberId, String sitterId) throws SQLException {
		String sql = "INSERT INTO likelist (member_id, sitter_id) " + "VALUES (?, ?)";

		jdbcUtil.setSqlAndParameters(sql, new Object[] { memberId, sitterId });
		try {
			int recordCount = jdbcUtil.executeUpdate();

			return recordCount;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		return 0;
	}

	/* 좋아요 삭제 */
	public int remove(String memberId, String sitterId) throws SQLException {
		String sql = "DELETE FROM likelist " + "WHERE member_id=? AND sitter_id=?";

		jdbcUtil.setSqlAndParameters(sql, new Object[] { memberId, sitterId });
		try {
			int recordCount = jdbcUtil.executeUpdate();

			return recordCount;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		return 0;
	}
}
