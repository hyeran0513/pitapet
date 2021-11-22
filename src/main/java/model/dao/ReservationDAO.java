package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.dto.Care;
import model.dto.Member;

public class ReservationDAO {
	private JDBCUtil jdbcUtil = null;
	   
	   public ReservationDAO() {         
	      jdbcUtil = new JDBCUtil();   // JDBCUtil 객체 생성
	   }
	   
	   public int createCare(Care care) throws SQLException {
		  String sql = "INSERT INTO CARE VALUES (?, ?, care_seq.nextval, ?, TO_DATE(?, 'yyyy/MM/dd HH24:mi:ss'), ?, ?, ?)";
          Object[] param = new Object[] {care.getSitter().getSitter().getId(), care.getCompanion().getId(),
	    		  care.getStartDate(), care.getEndDate(), care.getTotalPrice(), care.getNotes(), care.getStatus()};   
          jdbcUtil.setSqlAndParameters(sql, param);
          
          String key[] = {"care_id"}; 
	      try {
	         int result = jdbcUtil.executeUpdate(key);
	         ResultSet rs = jdbcUtil.getGeneratedKeys(); 
	         int generatedKey = 0;
	         if (rs.next()) {
	        	 generatedKey = rs.getInt(1);
	        	 return generatedKey;
	         }
	      } catch (Exception ex) {
	          jdbcUtil.rollback();
	    	  ex.printStackTrace();
	      } finally {
	    	 jdbcUtil.commit();
	         jdbcUtil.close();      
	      }
		   return 0;
	   }
}
