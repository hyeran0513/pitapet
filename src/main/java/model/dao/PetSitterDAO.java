package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dto.Member;
import model.dto.PetSitter;
import model.dto.PetSitterApplication;

public class PetSitterDAO {
	private JDBCUtil jdbcUtil = null;
	
	public PetSitterDAO() {         
		jdbcUtil = new JDBCUtil();   // JDBCUtil 객체 생성
	}
	
	/* 돌보미 전체 리스트 조회 (프로필 이미지 포함) */
	public ArrayList<PetSitter> findPetSitterList() throws SQLException {
		String sql = "SELECT ps.sitter_id, ps.tag, ps.notes, ps.sitter_like, ps.sitter_view, m.address, img_src "
				+ "FROM member m JOIN petsitter ps ON (m.member_id = ps.sitter_id) "
				+ "JOIN attachment atm ON (m.member_id = atm.member_id) "
				+ "WHERE ps.public_status = 'Y' AND atm.category_id = 'AtchId04' "
				+ "ORDER BY ps.sitter_like DESC, ps.sitter_view DESC";
		jdbcUtil.setSqlAndParameters(sql, null);
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {
				ArrayList<PetSitter> sitterList = new ArrayList<>();
				PetSitter sitter = new PetSitter(
						new Member(rs.getString("sitter_id"), rs.getString("address"), rs.getString("img_src")),
						rs.getString("tag"), rs.getString("notes"), rs.getInt("sitter_like"), rs.getInt("sitter_view"));
				sitterList.add(sitter);
				while (rs.next()) {
					sitter = new PetSitter(
							new Member(rs.getString("sitter_id"), rs.getString("address"), rs.getString("img_src")),
							rs.getString("tag"), rs.getString("notes"), rs.getInt("sitter_like"),
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
	
	/* 검색 옵션 정보에 따른 돌보미 리스트 조회 (프로필 이미지 포함) */
	public ArrayList<PetSitter> findPetSitterListByKeyword(List<String> options) throws SQLException {
		String sql = "SELECT ps.sitter_id, ps.tag, ps.notes, ps.sitter_like, ps.sitter_view, m.address, img_src "
				+ "FROM member m JOIN petsitter ps ON (m.member_id = ps.sitter_id) "
				+ "JOIN attachment atm ON (m.member_id = atm.member_id) ";
		String keyword = "";
		if (options.get(0).equals("city")) {
			keyword = "%" + options.get(1) + "%";
			sql += "WHERE m.address LIKE ? AND ps.public_status = 'Y' AND atm.category_id = 'AtchId04'";
		} else if (options.get(0).equals("tag")) {
			keyword = "%" + options.get(1) + "%";
			sql += "WHERE ps.tag LIKE ? AND ps.public_status = 'Y' AND atm.category_id = 'AtchId04'";
		} else if (options.get(0).equals("category")) {
			keyword = options.get(1);
			sql += "JOIN available_pet_kind apk ON (ps.sitter_id = apk.sitter_id) ";
			sql += "WHERE apk.kind_id = ? AND ps.public_status = 'Y' AND atm.category_id = 'AtchId04'";
		}
		sql += "ORDER BY ps.sitter_like DESC, ps.sitter_view DESC";

		jdbcUtil.setSqlAndParameters(sql, new Object[] { keyword });
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {
				ArrayList<PetSitter> sitterList = new ArrayList<>();
				PetSitter sitter = new PetSitter(
						new Member(rs.getString("sitter_id"), rs.getString("address"), rs.getString("img_src")),
						rs.getString("tag"), rs.getString("notes"), rs.getInt("sitter_like"), rs.getInt("sitter_view"));
				sitterList.add(sitter);
				while (rs.next()) {
					sitter = new PetSitter(
							new Member(rs.getString("sitter_id"), rs.getString("address"), rs.getString("img_src")),
							rs.getString("tag"), rs.getString("notes"), rs.getInt("sitter_like"),
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
	
	/* 추천 돌보미 리스트 조회(현재 사용자의 반려동물과 같은 종 돌봄이 가능한 돌보미) */
	public ArrayList<PetSitter> findPetSitterListOfRecommend(String memberId) throws SQLException {
		String sql = "SELECT ps.sitter_id, ps.tag, ps.notes, ps.sitter_like, ps.sitter_view, m.address, img_src "
				+ "FROM member m JOIN petsitter ps ON (m.member_id = ps.sitter_id) "
				+ "JOIN attachment atm ON (m.member_id = atm.member_id) "
				+ "JOIN available_pet_kind apk ON (ps.sitter_id = apk.sitter_id) "
				+ "WHERE ps.public_status = 'Y' AND atm.category_id = 'AtchId04' "
				+ "AND apk.kind_id IN (SELECT p.kind_id FROM member usr JOIN PET p ON (usr.member_id = p.member_id) "
				+ "WHERE usr.member_id = ?)";

		jdbcUtil.setSqlAndParameters(sql, new Object[] { memberId });
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {
				ArrayList<PetSitter> sitterList = new ArrayList<>();
				PetSitter sitter = new PetSitter(
						new Member(rs.getString("sitter_id"), rs.getString("address"), rs.getString("img_src")),
						rs.getString("tag"), rs.getString("notes"), rs.getInt("sitter_like"), rs.getInt("sitter_view"));
				sitterList.add(sitter);
				while (rs.next()) {
					sitter = new PetSitter(
							new Member(rs.getString("sitter_id"), rs.getString("address"), rs.getString("img_src")),
							rs.getString("tag"), rs.getString("notes"), rs.getInt("sitter_like"),
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
	
	/* 특정 회원이 찜한 돌보미 리스트 조회 (프로필 이미지 포함) */
	public ArrayList<PetSitter> findPetSitterListOfLike(String memberId) throws SQLException {
		String sql = "SELECT ps.sitter_id, ps.tag, ps.notes, ps.sitter_like, ps.sitter_view, m.address, img_src "
				+ "FROM member m JOIN petsitter ps ON (m.member_id = ps.sitter_id) "
				+ "JOIN attachment atm ON (m.member_id = atm.member_id) "
				+ "JOIN likelist ll ON (ps.sitter_id = ll.sitter_id) "
				+ "WHERE ps.public_status = 'Y' AND atm.category_id = 'AtchId04' AND ll.member_id = ?"
				+ "ORDER BY ps.sitter_like DESC, ps.sitter_view DESC";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { memberId });
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {
				ArrayList<PetSitter> sitterList = new ArrayList<>();
				PetSitter sitter = new PetSitter(
						new Member(rs.getString("sitter_id"), rs.getString("address"), rs.getString("img_src")),
						rs.getString("tag"), rs.getString("notes"), rs.getInt("sitter_like"), rs.getInt("sitter_view"));
				sitterList.add(sitter);
				while (rs.next()) {
					sitter = new PetSitter(
							new Member(rs.getString("sitter_id"), rs.getString("address"), rs.getString("img_src")),
							rs.getString("tag"), rs.getString("notes"), rs.getInt("sitter_like"),
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
	
	/* 돌보미 상세 정보 반환 */
	public PetSitter findPetSitter(String sitterId) throws SQLException {
		String sql = "SELECT ps.sitter_id, ps.public_status, ps.able_date, ps.caculated_price, "
				+ "ps.tag, ps.notes, ps.avg_rate, ps.sitter_like, ps.sitter_view, ps.apply_id, m.address, img_src "
				+ "FROM member m JOIN petsitter ps ON (m.member_id = ps.sitter_id) "
				+ "JOIN attachment atm ON (m.member_id = atm.member_id) "
				+ "WHERE ps.sitter_id = ? AND atm.category_id = 'AtchId04'";

		jdbcUtil.setSqlAndParameters(sql, new Object[] { sitterId });

		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {
				PetSitter sitter = new PetSitter(
						new Member(rs.getString("sitter_id"), rs.getString("address"), rs.getString("img_src")),
						rs.getString("public_status"), rs.getString("able_date"), rs.getString("caculated_price"),
						rs.getString("tag"), rs.getString("notes"), rs.getFloat("avg_rate"), rs.getInt("sitter_like"),
						rs.getInt("sitter_view"), new PetSitterApplication(rs.getString("apply_id")));

				return sitter;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}
	
	/* 특정 돌보미의 좋아요 수 업데이트 */
	public int updateLikes(String sitterId, String status) throws SQLException {
		String sql = "UPDATE petsitter ";
		if (status.equals("add"))
			sql += "SET sitter_like = sitter_like + 1 ";
		else
			sql += "SET sitter_like = sitter_like - 1 ";
		sql += "WHERE sitter_id = ?";

		jdbcUtil.setSqlAndParameters(sql, new Object[] { sitterId });
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

	/* 특정 돌보미의 조회 수 업데이트 */
	public int updateViews(String sitterId) throws SQLException {
		String sql = "UPDATE petsitter " + "SET sitter_view = sitter_view + 1 " + "WHERE sitter_id = ?";

		jdbcUtil.setSqlAndParameters(sql, new Object[] { sitterId });
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

	/* 보호자에서 돌보미로 등급 조정 */
	public int upgradeSitter(String memberId) throws SQLException {
		String sql = "UPDATE member " + "SET identity='S' " + "WHERE member_id=?";

		jdbcUtil.setSqlAndParameters(sql, new Object[] { memberId });

		try {
			int result = jdbcUtil.executeUpdate();
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		return 0;
	}
	
	/* 돌보미 등록 */
	public int createSitter(String memberId, PetSitter sitter, String applyId) throws SQLException {
		String sql = "INSERT ALL " 
				+ "INTO PETSITTER VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) " 
				+ "SELECT * FROM DUAL";

		Object[] param = new Object[] { sitter.getPublicStatus(), sitter.getAbleDate(), sitter.getCalculatedPrice(),
				sitter.getTag(), sitter.getNotes(), null, 0, 0, applyId, memberId };

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
		
}
	
