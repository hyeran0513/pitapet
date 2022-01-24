package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dto.Member;
import model.dto.PetSitterApplication;

public class PetSitterApplicationDAO {
	private JDBCUtil jdbcUtil = null;

	public PetSitterApplicationDAO() {         
	      jdbcUtil = new JDBCUtil();   // JDBCUtil 객체 생성
	   }

	/* 회원의 돌보미 지원 상태 조회 */
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
	
	/* 돌보미 지원자 리스트 조회 */
	public List<PetSitterApplication> findApplicationList() throws SQLException {
		String sql = "SELECT apply_id, apply_date, member_id "
				+ "FROM petsitter_application "
				+ "WHERE approval_status='X'";
		jdbcUtil.setSqlAndParameters(sql, null);

		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<PetSitterApplication> applicationLists = new ArrayList<PetSitterApplication>();
			if(rs.next()) {
				PetSitterApplication pApplication = new PetSitterApplication(
						rs.getString("apply_id"), 
						rs.getString("apply_date"),
						new Member(rs.getString("member_id")));
				applicationLists.add(pApplication);
				while (rs.next()) {
					pApplication = new PetSitterApplication(
							rs.getString("apply_id"), 
							rs.getString("apply_date"),
							new Member(rs.getString("member_id")));
					applicationLists.add(pApplication);
				}
				return applicationLists;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}
	
	/* 돌보미 지원 정보 반환 */
	public PetSitterApplication findApplication(String applyId) throws SQLException {
		String sql = "SELECT apply_date, career, certification, introduction, member_id, address, img_src "
				+ "FROM petsitter_application ps JOIN member m USING(member_id) "
				+ "JOIN attachment atm USING (member_id) "
				+ "WHERE apply_id=? AND atm.category_id = 'AtchId04'";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {applyId});

		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if(rs.next()) {
				PetSitterApplication applyInfo = new PetSitterApplication(
						applyId,
						rs.getString("apply_date"), 
						rs.getString("career"),
						rs.getString("certification"),
						rs.getString("introduction"),
						new Member(rs.getString("member_id")
								,rs.getString("address")
								, rs.getString("img_src")));
				return applyInfo;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
		
	}
	
	/* 아이디로 돌보미 지원내역 찾기 */
	public PetSitterApplication findApplicationByMemberId(String memberId) throws SQLException {
		String sql = "SELECT apply_id, apply_date, career, certification, introduction, member_id, name, gender, phone, address, img_src "
				+ "FROM petsitter_application ps JOIN member m USING(member_id) "
				+ "JOIN attachment atm USING (member_id) "
				+ "WHERE member_id=? AND atm.category_id = 'AtchId04'";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {memberId});

		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if(rs.next()) {
				PetSitterApplication applyInfo = new PetSitterApplication(
						rs.getString("apply_id"),
						rs.getString("apply_date"), 
						rs.getString("career"),
						rs.getString("certification"),
						rs.getString("introduction"),
						new Member(memberId
								,rs.getString("name")
								,rs.getString("gender")
								,rs.getString("phone")
								,rs.getString("address")
								, rs.getString("img_src")));
				return applyInfo;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
		
	}
	
	/* 돌보미 지원서 자격증 첨부파일 반환 */
	public List<String> findApplyAttachments(String memberId) throws SQLException {
		String sql = "SELECT img_src " + "FROM attachment " + "WHERE member_id=? AND category_id=? AND img_src LIKE ?";

		String like = "%application-" + memberId + "-%";

		jdbcUtil.setSqlAndParameters(sql, new Object[] { memberId, "AtchId02", like }); // JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<String> imgList = new ArrayList<String>();
			while (rs.next()) {
				String img_src = rs.getString("img_src");
				imgList.add(img_src);
			}
			return imgList;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}
	
	/* 관리자의 돌보미 승인 */
	public int applyStatus(String applyId) throws SQLException {
		String sql = "UPDATE petsitter_application "
					+ "SET approval_status='Y' "
					+ "WHERE apply_id=?";
		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {applyId});
			
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
	
	/* 관리자의 돌보미 거절 */
	public int refuseStatus(String applyId) throws SQLException {
		String sql = "UPDATE petsitter_application "
					+ "SET approval_status='Z' "
					+ "WHERE apply_id=?";
		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {applyId});
			
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
	
	/* 시스템에 등록된 돌보미 지원 수 반환 */
		public int countAllApplication() {
			String sql = "SELECT COUNT(?) FROM PETSITTER_APPLICATION";
			Object[] param = new Object[] { "apply_id" };
			jdbcUtil.setSqlAndParameters(sql, param);
			int count = -1;
			try {
				ResultSet rs = jdbcUtil.executeQuery();
				if (rs.next()) {
					count = rs.getInt(1);
				}
			} catch (Exception ex) {
				jdbcUtil.rollback();
				ex.printStackTrace();
			} finally {
				jdbcUtil.commit();
				jdbcUtil.close();
			}
			return count;
		}
			
	/* 돌보미 지원정보 추가 */
	public int addApplication(String memberId, PetSitterApplication applicationInfo) {
		String sql = "INSERT INTO PETSITTER_APPLICATION VALUES (?, SYSDATE, ?, ?, ?, ?, ?)";
		Object[] param = new Object[] { applicationInfo.getId(), applicationInfo.getCareer(),
				applicationInfo.getCertification(), applicationInfo.getIntroduction(),
				applicationInfo.getApprovalStatus(), memberId };
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
	
	/* 돌보미 지원 취소 */
	public int cancelApplication(String applyId) {
		String sql = "DELETE FROM PETSITTER_APPLICATION WHERE apply_id=?";
		Object[] param = new Object[] { applyId };
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
	
	/* 돌보미 지원 정보 자기소개 업데이트 */
	public int updateApplcationIntroduction(String memberId, String introduction) throws SQLException {
		String sql = "UPDATE petsitter_application SET introduction = ? " 
				+ "WHERE member_id = ?";

		jdbcUtil.setSqlAndParameters(sql, new Object[] { introduction, memberId });

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
