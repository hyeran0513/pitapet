package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dto.LikeList;
import model.dto.Member;
import model.dto.PetSitter;
import model.dto.PetSitterApplication;

public class PetSitterDAO {
	private JDBCUtil jdbcUtil = null;
	
	public PetSitterDAO() {         
		jdbcUtil = new JDBCUtil();   // JDBCUtil 객체 생성
	}
	
	public ArrayList<PetSitter> findPetSitterList() throws SQLException {
		String sql = "SELECT ps.sitter_id, ps.tag, ps.notes, ps.sitter_like, ps.sitter_view, m.address "
                 + "FROM member m JOIN petsitter ps ON (m.member_id = ps.sitter_id) "        
				 + "WHERE ps.public_status = 'Y'";
		jdbcUtil.setSqlAndParameters(sql, null);
	      try {
	         ResultSet rs = jdbcUtil.executeQuery();
	         if (rs.next()) {                  
	        	 ArrayList<PetSitter> sitterList = new ArrayList<>();
	        	 PetSitter sitter = new PetSitter (
	        			 new Member (
	        					 rs.getString("sitter_id"), 
	        					 rs.getString("address")),
	        			 rs.getString("tag"),
	        			 rs.getString("notes"),
	        			 rs.getInt("sitter_like"),
	        			 rs.getInt("sitter_view"));
	        	 sitterList.add(sitter);
	        	 while (rs.next()) {
	        		 sitter = new PetSitter (
	        			 new Member (
	        					 rs.getString("sitter_id"), 
	        					 rs.getString("address")),
	        			 rs.getString("tag"),
	        			 rs.getString("notes"),
	        			 rs.getInt("sitter_like"),
	        			 rs.getInt("sitter_view"));
	        		 sitterList.add(sitter);
	        	 }
	            return sitterList;
	         }
	      } catch (Exception ex) {
	         ex.printStackTrace();
	      } finally {
	         jdbcUtil.close();      
	      }
	      return null;
	   }
	
	public PetSitter findPetSitter(String sitterId) throws SQLException {
		// 돌보미 이름을 보여주는 게 맞나? id를 보여줘야 하나...? (개인정보 보호)
		/*
		 * 돌보미 이름 - member 테이블
		 * 돌보미 제공 가능 서비스 리스트 - provide_service 테이블
		 * 돌보미 돌봄 가능 반려동물 리스트 - available_pet_kind 테이블
		 */
		
		// sql 쿼리문 미완성
		String sql = "SELECT ps.sitter_id, ps.public_status, ps.able_date, ps.caculated_price, "
				+ "ps.tag, ps.notes, ps.avg_rate, ps.sitter_like, ps.sitter_view, ps.apply_id, m.address "
                + "FROM member m JOIN petsitter ps ON (m.member_id = ps.sitter_id) "        
				 + "WHERE ps.sitter_id = ?";
		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {sitterId});
		
	      try {
	         ResultSet rs = jdbcUtil.executeQuery();
	         if (rs.next()) {                  
	        	 PetSitter sitter = new PetSitter (
	        			 new Member (
	        					 rs.getString("sitter_id"), 
	        					 rs.getString("address")),
	        			 rs.getString("public_status"),
	        			 rs.getString("able_date"),
	        			 rs.getString("caculated_price"),
	        			 rs.getString("tag"),
	        			 rs.getString("notes"),
	        			 rs.getFloat("avg_rate"),
	        			 rs.getInt("sitter_like"),
	        			 rs.getInt("sitter_view"),
	        			 new PetSitterApplication(rs.getString("apply_id")));
	        	 
	            return sitter;
	         }
	      } catch (Exception ex) {
	         ex.printStackTrace();
	      } finally {
	         jdbcUtil.close();      
	      }
	      return null;
	   }

}
