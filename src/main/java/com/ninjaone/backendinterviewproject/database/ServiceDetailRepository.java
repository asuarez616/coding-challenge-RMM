package com.ninjaone.backendinterviewproject.database;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ninjaone.backendinterviewproject.model.Device;
import com.ninjaone.backendinterviewproject.model.ServiceDetail;


/**
 * The Interface ServiceDetailRepository.
 * it manage the interaction with the DB
 * @author ASUAREZ
 */
@Repository
public interface ServiceDetailRepository extends CrudRepository<ServiceDetail, String> {
	
	/**
	 * Find by name and type.
	 *
	 * @param name the name
	 * @param type the type
	 * @return the list
	 */
	@Query(value = "select * from service_detail services where services.name=:name and services.type=:type", nativeQuery = true)
	List<ServiceDetail> findByNameAndType(@Param("name") String name, @Param("type") String type);
	
}
