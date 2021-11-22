package model.service;

import java.sql.SQLException;
import java.util.ArrayList;

import model.dao.ServiceDAO;
import model.dto.Care;
import model.dto.Service;

public class ServiceManager {
	private static ServiceManager serviceMan = new ServiceManager();
	private ServiceDAO serviceDAO;

	private ServiceManager() {
		try {
			serviceDAO = new ServiceDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ServiceManager getInstance() {
		return serviceMan;
	}

	public ArrayList<Service> findProvideServiceList(String sitterId) throws SQLException {
		return serviceDAO.findProvideServiceList(sitterId);
	}
	
	public String createReceiveService(int careId, String petId, String serviceId) throws SQLException {
		   return serviceDAO.createReceiveService(careId, petId, serviceId);
	}
	
	public int deleteReceiveService(int careId) throws SQLException {
		return serviceDAO.deleteReceiveService(careId);
	}
}
