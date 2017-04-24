package projectHotelManagement.dao;

import projectHotelManagement.data.AdditionalServices;

/**
 * 
 * @author Nemanja
 *
 */
public interface AdditionalServicesDAO {
	
	/**
	 * Finding additional services by id.
	 * @author Nemanja
	 * @param servicesId
	 * @return
	 */
	public AdditionalServices findById(int servicesId);
	
}
