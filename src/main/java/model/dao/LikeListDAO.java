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
}
