package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.dto.PetSitterApplication;

public class PetSitterApplicationDAO {
	private JDBCUtil jdbcUtil = null;

	public PetSitterApplicationDAO() {         
	      jdbcUtil = new JDBCUtil();   // JDBCUtil 객체 생성
	   }

	public String getApprovalStatus(String memberId) throws SQLException {
		String sql = "SELECT approval_status " + "FROM petsitter_application "
				+ "WHERE member_id=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { memberId }); // JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {
				String approval_status = rs.getString("approval_status");
				return approval_status;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}
}
