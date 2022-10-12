package com.ninjaone.backendinterviewproject.database;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ninjaone.backendinterviewproject.model.Device;


/**
 * The Interface DeviceRepository.
 * it manage the interaction with the DB
 * @author ASUAREZ
 */
@Repository

public interface DeviceRepository extends CrudRepository<Device, String> {
	
	/**
	 * Find by system name and type.
	 *
	 * @param systemName the system name
	 * @param type the type
	 * @return the list
	 */
	@Query(value = "select * from device where device.system_Name=:systemName and device.type=:type", nativeQuery = true)
	List<Device> findBySystemNameAndType(@Param("systemName") String systemName, @Param("type") String type);

	/**
	 * Find devices by services id.
	 *
	 * @param servicesId the services id
	 * @return the list
	 */
	List<Device> findDevicesByServicesId(String servicesId);

}
