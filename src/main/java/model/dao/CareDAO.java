package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dto.Care;
import model.dto.Member;
import model.dto.PetSitter;

public class CareDAO {
	private JDBCUtil jdbcUtil = null;
	
	public CareDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 객체 생성
	}
	
	/* 보호자 및 특정 돌보미의 돌봄 스케쥴 조회 */
	public List<Care> findCareSchedules(String memberId, String sitterId) throws SQLException {
		String sql = "SELECT DISTINCT care_id, start_date, end_date, c.member_id, m.name, c.sitter_id, care_status " 
				+ "FROM care c JOIN member m ON (c.sitter_id = m.member_id) ";
		if (sitterId == null) {
			sql += "WHERE c.member_id = ? ";
			jdbcUtil.setSqlAndParameters(sql, new Object[] { memberId });
		}
		else {
			sql += "WHERE c.member_id = ? OR c.sitter_id = ? ";
			jdbcUtil.setSqlAndParameters(sql, new Object[] { memberId, memberId });
		}

		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<Care> careList = null;
			if (rs.next()) {
				careList = new ArrayList<Care>();
				Care care = new Care(rs.getInt("care_id"), rs.getString("start_date"), rs.getString("end_date"),
						new Member(rs.getString("member_id")), new PetSitter(new Member(rs.getString("sitter_id"), rs.getString("name"))),
						rs.getString("care_status"));
				careList.add(care);
				while (rs.next()) {
					care = new Care(rs.getInt("care_id"), rs.getString("start_date"), rs.getString("end_date"),
							new Member(rs.getString("member_id")), new PetSitter(new Member(rs.getString("sitter_id"), rs.getString("name"))),
							rs.getString("care_status"));
					careList.add(care);
				}
				return careList;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}
	
	/* 돌봄 예약내역 조회 */
	public Care findReservation(int careId) throws SQLException {
		   String sql = "SELECT sitter_id, member_id, care_id, start_date, end_date, total_price, notes, care_status "
	                 + "FROM care "
	                 + "WHERE care_id=?";              
	      jdbcUtil.setSqlAndParameters(sql, new Object[] {careId});

	      try {
	         ResultSet rs = jdbcUtil.executeQuery();      
	         if (rs.next()) {                  
	            Care care = new Care(
	               careId,
	               rs.getString("start_date"),
	               rs.getString("end_date"),
	               rs.getInt("total_price"),
	               rs.getString("notes"),
	               rs.getString("care_status"),               
	               new Member(rs.getString("member_id")),
	               new PetSitter(new Member(rs.getString("sitter_id")))
	            );
	            return care;
	         }
	      } catch (Exception ex) {
	         ex.printStackTrace();
	      } finally {
	         jdbcUtil.close();      
	      }
	      return null;
	   }
	
	/* 보호자-돌보미 간 돌봄 완료 내역 반환 */
	public List<Care> findCareList(String memberId, String sitterId) throws SQLException {
		String sql = "SELECT care_id, start_date, end_date, member_id, care_status "
				+ "FROM care "
				+ "WHERE member_id = ? AND sitter_id = ? AND care_status = 'Z'";     
		jdbcUtil.setSqlAndParameters(sql, new Object[] {memberId, sitterId});	
						
		try {
			ResultSet rs = jdbcUtil.executeQuery();			
			if (rs.next()) {
				List<Care> careList = new ArrayList<Care>();	
				Care care = new Care(			
						rs.getInt("care_id"),
						rs.getString("start_date"),
						rs.getString("end_date"),
						new Member(rs.getString("member_id")),
						new PetSitter(
								new Member(sitterId)
						),
						rs.getString("care_status")
				);
				careList.add(care);	
				while (rs.next()) {
					care = new Care(			
						rs.getInt("care_id"),
						rs.getString("start_date"),
						rs.getString("end_date"),
						new Member(rs.getString("member_id")),
						new PetSitter(
								new Member(sitterId)
						),
						rs.getString("care_status")
					);
					careList.add(care);	
				}
				return careList;
			}								
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}		
		return null;
	}
	
	/* 돌봄 내역 생성 */
	public int createCare(Care care) throws SQLException {
		String sql = "INSERT INTO CARE VALUES (?, ?, care_seq.nextval, ?, TO_DATE(?, 'yyyy/MM/dd HH24:mi:ss'), ?, ?, ?)";
		Object[] param = new Object[] { care.getSitter().getSitter().getId(), care.getCompanion().getId(),
				care.getStartDate(), care.getEndDate(), care.getTotalPrice(), care.getNotes(), care.getStatus() };
		jdbcUtil.setSqlAndParameters(sql, param);

		String key[] = { "care_id" };
		try {
			jdbcUtil.executeUpdate(key);
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
	
	/* 돌봄 내역 삭제 */
	public int deleteCare(int careId) throws SQLException {
		String sql = "DELETE FROM care "
				+ "WHERE care_id = ?";     
		jdbcUtil.setSqlAndParameters(sql, new Object[] {careId});	
						
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
	
	/* 제공받는 서비스 했는지 확인(체크) */
	public String getCheckInfo(String rcvId) throws SQLException {
		String sql = "SELECT care_check "
				+ "FROM care_checklist "
				+ "WHERE rcv_id = ?";     
		jdbcUtil.setSqlAndParameters(sql, new Object[] {rcvId});	
						
		try {
			ResultSet rs = jdbcUtil.executeQuery();			
			if (rs.next()) {
				String check = rs.getString("care_check");
				return check;	
			}					
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}
	
	/* 돌봄 진행 상태로 업데이트 */
	public int updateCareSchedule(Care care) throws SQLException {
		String sql = "UPDATE care SET care_status = ? "
				+ "WHERE care_id = ?";

		jdbcUtil.setSqlAndParameters(sql, new Object[] { "Y", care.getId() });
		
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
