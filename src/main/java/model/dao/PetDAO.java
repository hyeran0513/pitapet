package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dto.Member;
import model.dto.Pet;
import model.dto.PetKind;

public class PetDAO {
	private JDBCUtil jdbcUtil = null;

	public PetDAO() {
		jdbcUtil = new JDBCUtil(); // JDBCUtil 객체 생성
	}

	/* 회원의 반려동물 리스트 조회 */
	public ArrayList<Pet> findPetListOfMember(String memberId) throws SQLException {
		String sql = "SELECT pet_id, name, birth, gender, kind_id, large_category, small_category "
				+ "FROM pet JOIN pet_kind USING (kind_id) " + "WHERE member_id = ?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { memberId });

		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {
				ArrayList<Pet> petList = new ArrayList<>();

				Pet pet = new Pet(rs.getString("pet_id"));
				pet.setName(rs.getString("name"));
				pet.setBirth(rs.getString("birth"));
				pet.setGender(rs.getString("gender"));
				pet.setKind(new PetKind(rs.getString("kind_id"), rs.getString("large_category"),
						rs.getString("small_category")));
				petList.add(pet);

				while (rs.next()) {
					pet = new Pet(rs.getString("pet_id"));
					pet.setName(rs.getString("name"));
					pet.setBirth(rs.getString("birth"));
					pet.setGender(rs.getString("gender"));
					pet.setKind(new PetKind(rs.getString("kind_id"), rs.getString("large_category"),
							rs.getString("small_category")));
					petList.add(pet);
				}

				return petList;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}

	/* 보호자의 반려동물 중 선택한 돌보미가 돌봄 가능한 동물 리스트 */
	public List<Pet> findAbleCarePetList(String memberId, String sitterId) throws SQLException {
		String sql = "SELECT pet_id, name, birth, gender, kind_id, large_category, small_category "
				+ "FROM pet JOIN pet_kind USING (kind_id) " + "JOIN available_pet_kind apk USING (kind_id) "
				+ "WHERE member_id = ? AND apk.sitter_id = ?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { memberId, sitterId });

		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {
				ArrayList<Pet> petList = new ArrayList<>();

				Pet pet = new Pet(rs.getString("pet_id"));
				pet.setName(rs.getString("name"));
				pet.setBirth(rs.getString("birth"));
				pet.setGender(rs.getString("gender"));
				pet.setKind(new PetKind(rs.getString("kind_id"), rs.getString("large_category"),
						rs.getString("small_category")));
				petList.add(pet);

				while (rs.next()) {
					pet = new Pet(rs.getString("pet_id"));
					pet.setName(rs.getString("name"));
					pet.setBirth(rs.getString("birth"));
					pet.setGender(rs.getString("gender"));
					pet.setKind(new PetKind(rs.getString("kind_id"), rs.getString("large_category"),
							rs.getString("small_category")));
					petList.add(pet);
				}

				return petList;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}

	/* 전체 반려동물 종 리스트 검색 */
	public ArrayList<PetKind> findAllPetKindList() throws SQLException {
		String sql = "SELECT kind_id, large_category, small_category " + "FROM pet_kind " + "ORDER BY large_category";

		jdbcUtil.setSqlAndParameters(sql, null);

		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {
				ArrayList<PetKind> petKindList = new ArrayList<>();
				PetKind petKind = new PetKind(rs.getString("kind_id"), rs.getString("large_category"),
						rs.getString("small_category"));
				petKindList.add(petKind);
				while (rs.next()) {
					petKind = new PetKind(rs.getString("kind_id"), rs.getString("large_category"),
							rs.getString("small_category"));
					petKindList.add(petKind);
				}
				return petKindList;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}

	/* 전체 돌보미의 돌봄 가능 종 리스트 검색 */
	public ArrayList<PetKind> findAllAblePetKindList() throws SQLException {
		String sql = "SELECT DISTINCT kind_id, large_category, small_category "
				+ "FROM available_pet_kind JOIN pet_kind USING (kind_id) " + "ORDER BY large_category";

		jdbcUtil.setSqlAndParameters(sql, null);

		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {
				ArrayList<PetKind> petKindList = new ArrayList<>();
				PetKind petKind = new PetKind(rs.getString("kind_id"), rs.getString("large_category"),
						rs.getString("small_category"));
				petKindList.add(petKind);
				while (rs.next()) {
					petKind = new PetKind(rs.getString("kind_id"), rs.getString("large_category"),
							rs.getString("small_category"));
					petKindList.add(petKind);
				}
				return petKindList;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}

	/* 특정 돌보미의 돌봄 가능 종 리스트 검색 */
	public ArrayList<PetKind> findAblePetKindList(String sitterId) throws SQLException {
		String sql = "SELECT kind_id, large_category, small_category "
				+ "FROM available_pet_kind JOIN pet_kind USING (kind_id) " + "WHERE sitter_id = ?";

		jdbcUtil.setSqlAndParameters(sql, new Object[] { sitterId });

		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {
				ArrayList<PetKind> petKindList = new ArrayList<>();
				PetKind petKind = new PetKind(rs.getString("kind_id"), rs.getString("large_category"),
						rs.getString("small_category"));
				petKindList.add(petKind);
				while (rs.next()) {
					petKind = new PetKind(rs.getString("kind_id"), rs.getString("large_category"),
							rs.getString("small_category"));
					petKindList.add(petKind);
				}
				return petKindList;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}

	/* 반려동물의 이미지 반환 */
	public String findPetAttachment(String memberId, String petId) throws SQLException {
		String sql = "SELECT img_src " + "FROM attachment " + "WHERE member_id=? AND img_src LIKE ?";

		String like = "%pet-" + petId + "-%";
		like = like.replaceAll(" ", "");

		jdbcUtil.setSqlAndParameters(sql, new Object[] { memberId, like }); // JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {
				String img_src = rs.getString("img_src");
				return img_src;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}

	/* 돌봄 내역에 해당하는 반려동물 리스트 조회 */
	public ArrayList<Pet> findCarePetList(Integer careId) throws SQLException {
		String sql = "SELECT DISTINCT pet_id, name, birth, gender, kind_id "
				+ "FROM pet JOIN receive_service USING (pet_id) " + "WHERE care_id = ?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { careId });

		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {
				ArrayList<Pet> petList = new ArrayList<>();
				Pet pet = new Pet(rs.getString("pet_id"));
				pet.setName(rs.getString("name"));
				pet.setBirth(rs.getString("birth"));
				pet.setGender(rs.getString("gender"));
				pet.setKind(new PetKind(rs.getString("kind_id")));
				petList.add(pet);
				while (rs.next()) {
					pet = new Pet(rs.getString("pet_id"));
					pet.setName(rs.getString("name"));
					pet.setBirth(rs.getString("birth"));
					pet.setGender(rs.getString("gender"));
					pet.setKind(new PetKind(rs.getString("kind_id")));
					petList.add(pet);
				}
				return petList;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}

	// 시스템에 등록된 반려동물 마릿수 반환
	public int countAllPet() {
		String sql = "SELECT COUNT(?) FROM PET";
		Object[] param = new Object[] { "pet_id" };
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

	/* 반려동물 추가 */
	public int addPet(String memberId, Pet pet) {
		String sql = "INSERT INTO PET VALUES (?, ?, ?, ?, ?, ?)";
		Object[] param = new Object[] { pet.getId(), pet.getName(), pet.getBirth(), pet.getGender(), memberId, pet.getKind().getId() };
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

	/* petId에 해당하는 반려동물 정보 조회 */
	public Pet findPetInfo(String petId) throws SQLException {
		String sql = "SELECT pet_id, name, birth, gender, member_id, kind_id " + "FROM pet " + "WHERE pet_id = ?";

		jdbcUtil.setSqlAndParameters(sql, new Object[] { petId });

		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {
				Pet pet = new Pet(petId, rs.getString("name"), rs.getString("birth"), rs.getString("gender"),
						new PetKind(rs.getString("kind_id")), new Member(rs.getString("member_id")));

				return pet;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}
	
	/* 반려동물 첨부파일(이미지) 추가 */
	public int addAttachment(String image, String memberId) throws SQLException {
		String sql = "INSERT INTO attachment (img_src, member_id, category_id) " 
				+ "VALUES (?, ?, 'AtchId01')";

		jdbcUtil.setSqlAndParameters(sql, new Object[] { image, memberId });
		
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
	
	// 반려동물 첨부파일(이미지) 삭제
	public int removePetAttachments(String petId) throws SQLException {
		String like = "%" + petId + "%";
		like = like.replaceAll(" ", "");
		
		String sql = "DELETE FROM attachment "
				+ "WHERE img_src LIKE ?";     
		jdbcUtil.setSqlAndParameters(sql, new Object[] { like });	
						
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

	// 반려동물 정보 삭제
	public int remove(String petId) throws SQLException {
		String sql = "DELETE FROM pet "
				+ "WHERE pet_id =?";     
		jdbcUtil.setSqlAndParameters(sql, new Object[] { petId });	
						
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

	/* 돌보미의 돌봄 가능 동물 종 추가 */
	public int addAblePetKind(String memberId, String kindId) {
		String sql = "INSERT INTO available_pet_kind (kind_id, sitter_id) " 
				+ "VALUES (?, ?)";

		jdbcUtil.setSqlAndParameters(sql, new Object[] { kindId, memberId });
		
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
