package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.dto.Care;
import model.dto.Member;
import model.dto.PetSitter;
import model.dto.Service;

public class ServiceDAO {
	private JDBCUtil jdbcUtil = null;

	public ServiceDAO() {
		jdbcUtil = new JDBCUtil(); // JDBCUtil 객체 생성
	}

	public ArrayList<Service> findProvideServiceList(String sitterId) throws SQLException {
		String sql = "SELECT service_id, s.title, s.content "
				+ "FROM provide_service prvds JOIN service s USING (service_id) "
				+ "WHERE prvds.sitter_id = ?";
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

// 아직 완료x	
	public String createReceiveService(int careId, String petId, String serviceId) throws SQLException {
		String sql = "INSERT INTO RECEIVE_SERVICE VALUES (?, ?, ?, ?)";
		petId = petId.replaceAll(" ", "");
		String recvId = Integer.toString(careId) + petId.replaceAll("[^0-9]", "") + serviceId.replaceAll("[^0-9]", "");
		System.out.println(careId + " " + petId + " " + serviceId);
		Object[] param = new Object[] { careId, recvId, petId, serviceId };
		jdbcUtil.setSqlAndParameters(sql, param);
		 
		try {
			int result = jdbcUtil.executeUpdate();
			
			sql = "SELECT rcv_id "
					+ "FROM receive_service "
					+ "WHERE rcv_id = ?";
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

	public int deleteReceiveService(int careId) throws SQLException {
		String sql = "DELETE FROM receive_service "
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
