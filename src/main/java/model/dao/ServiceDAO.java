package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dto.Care;
import model.dto.CareDetails;
import model.dto.Pet;
import model.dto.Service;

public class ServiceDAO {
	private JDBCUtil jdbcUtil = null;

	public ServiceDAO() {
		jdbcUtil = new JDBCUtil(); // JDBCUtil 객체 생성
	}

	/* 특정 돌보미의 제공 서비스 리스트 검색 */
	public ArrayList<Service> findProvideServiceList(String sitterId) throws SQLException {
		String sql = "SELECT service_id, s.title, s.content "
				+ "FROM provide_service prvds JOIN service s USING (service_id) " + "WHERE prvds.sitter_id = ?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { sitterId });
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {
				ArrayList<Service> serviceList = new ArrayList<>();
				Service service = new Service(rs.getString("service_id"), rs.getString("title"),
						rs.getString("content"));
				serviceList.add(service);
				while (rs.next()) {
					service = new Service(rs.getString("service_id"), rs.getString("title"), rs.getString("content"));
					serviceList.add(service);
				}
				return serviceList;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}

	/* 돌봄 예약 정보(돌봄 내역) 추가 */
	public String createReceiveService(int careId, String petId, String serviceId) throws SQLException {
		String sql = "INSERT INTO RECEIVE_SERVICE VALUES (?, ?, ?, ?)";
		petId = petId.replaceAll(" ", "");
		String recvId = Integer.toString(careId) + petId.replaceAll("[^0-9]", "") + serviceId.replaceAll("[^0-9]", "");
		System.out.println(careId + " " + petId + " " + serviceId);
		Object[] param = new Object[] { careId, recvId, petId, serviceId };
		jdbcUtil.setSqlAndParameters(sql, param);

		try {
			jdbcUtil.executeUpdate();

			sql = "SELECT rcv_id " + "FROM receive_service " + "WHERE rcv_id = ?";
			param = new Object[] { recvId };
			jdbcUtil.setSqlAndParameters(sql, param);
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {
				String rcv_id = rs.getString("rcv_id");
				return rcv_id;
			}

		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		return null;
	}

	/* 예약내역을 참조하는 제공 서비스 삭제 */
	public int deleteReceiveService(int careId) throws SQLException {
		String sql = "DELETE FROM receive_service " + "WHERE care_id = ?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { careId });

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

	/* 예약 내여겍에 대한 제공 서비스 리스트 조회  */
	public List<CareDetails> findReceiveServiceByCareId(Care care) throws SQLException {
		String sql = "SELECT care_id, rcv_id, pet_id, service_id " + "FROM receive_service " + "WHERE care_id = ?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { care.getId() });

		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {
				List<CareDetails> rsList = new ArrayList<CareDetails>();
				CareDetails cd = new CareDetails(rs.getString("rcv_id"), care, new Service(rs.getString("service_id")),
						new Pet(rs.getString("pet_id")));
				rsList.add(cd);
				while (rs.next()) {
					cd = new CareDetails(rs.getString("rcv_id"), care, new Service(rs.getString("service_id")),
							new Pet(rs.getString("pet_id")));
					rsList.add(cd);
				}
				return rsList;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}

	/* 특정 반려동물이 받을 서비스 개수 반환 */
	public int countReceiveServiceByPetId(String petId) {
		String like = "%" + petId + "%";
		String sql = "SELECT COUNT(*) FROM receive_service WHERE pet_id LIKE ?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { like });
		int count = -1;
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
				return count;
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

	/* 특정 서비스 정보 조회 */
	public Service findServiceInfo(String serviceId) throws SQLException {
		String sql = "SELECT service_id, title, content " + "FROM service " + "WHERE service_id = ?";

		jdbcUtil.setSqlAndParameters(sql, new Object[] { serviceId });

		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {
				Service service = new Service(serviceId, rs.getString("title"), rs.getString("content"));

				return service;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}

	/* 전체 서비스 목록 조회 */
	public List<Service> findAllServiceList() throws SQLException {
		String sql = "SELECT service_id, title, content " + "FROM service";
		
		jdbcUtil.setSqlAndParameters(sql, null);
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {
				List<Service> serviceList = new ArrayList<>();
				Service service = new Service(rs.getString("service_id"), rs.getString("title"),
						rs.getString("content"));
				serviceList.add(service);
				while (rs.next()) {
					service = new Service(rs.getString("service_id"), rs.getString("title"),
							rs.getString("content"));
					serviceList.add(service);
				}
				return serviceList;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}
}
