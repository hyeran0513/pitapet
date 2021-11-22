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
	
	//보호자의 돌봄 내역 반환
	public List<Care> findCareSchedules(String memberId) throws SQLException {
		String sql = "SELECT care_id, start_date, end_date, sitter_id, care_status "
				+ "FROM care "
				+ "WHERE member_id = ?";     
		jdbcUtil.setSqlAndParameters(sql, new Object[] {memberId});	
						
		try {
			ResultSet rs = jdbcUtil.executeQuery();			
			List<Care> careList = new ArrayList<Care>();	
			while (rs.next()) {
				Care care = new Care(			
						rs.getInt("care_id"),
						rs.getString("start_date"),
						rs.getString("end_date"),
						new Member(memberId),
						new PetSitter(
								new Member(
										rs.getString("sitter_id")
								)
						),
						rs.getString("care_status")
				);
				careList.add(care);				
				}		
				return careList;					
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				jdbcUtil.close();
			}
		
		return null;
	}
	
	//돌보미의 돌봄 내역 반환
	public List<Care> findCareSchedulesOfSitter(String sitterId) throws SQLException {
		String sql = "SELECT care_id, start_date, end_date, member_id, care_status "
				+ "FROM care "
				+ "WHERE sitter_id = ?";     
		jdbcUtil.setSqlAndParameters(sql, new Object[] {sitterId});	
						
		try {
			ResultSet rs = jdbcUtil.executeQuery();			
			List<Care> careList = new ArrayList<Care>();	
			while (rs.next()) {
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
				}		
				return careList;					
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				jdbcUtil.close();
			}
		
		return null;
	}
	
	//보호자-돌보미 간 돌봄 완료 내역 반환
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
			jdbcUtil.close();
		}		
		return 0;
	}
}
