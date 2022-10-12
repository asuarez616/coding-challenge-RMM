package com.ninjaone.backendinterviewproject.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ninjaone.backendinterviewproject.database.ServiceDetailRepository;
import com.ninjaone.backendinterviewproject.model.ServiceDetail;


/**
 * The Class ServiceDetailService.
 * this class allows the interaction between the
 * controller and the model include the business logic for service entities
 * @author ASUAREZ
 */
@Service

public class ServiceDetailService {

	/** The services repository. */
	private final ServiceDetailRepository servicesRepository;

	/**
	 * Instantiates a new service detail service.
	 *
	 * @param servicesRepository the services repository
	 */
	public ServiceDetailService(ServiceDetailRepository servicesRepository) {
		this.servicesRepository = servicesRepository;
	}

	/**
	 * Save service detail entity.
	 *
	 * @param service the service
	 * @return the service detail
	 */
	public ServiceDetail saveServiceDetailEntity(ServiceDetail service) {
		// type property should not be empty
		if( service.getType() == null || service.getType().length() == 0) {
			service.setType("FOR_ALL");
		}
		// validate duplicates before save
		if (!validateDuplicateServiceDetail(service.getName(), service.getType())) {
			return servicesRepository.save(service);
		}
		return null;
	}

	/**
	 * Validate duplicate service detail.
	 *
	 * @param name the name
	 * @param type the type
	 * @return true, if successful
	 */
	//TODO: create validators and remove this validation
	public boolean validateDuplicateServiceDetail(String name, String type) {
		return servicesRepository.findByNameAndType(name, type).size() != 0 ? true : false;
	}

	/**
	 * Gets the service detail entity by id
	 *
	 * @param id the id
	 * @return the service detail entity
	 */
	public Optional<ServiceDetail> getServiceDetailEntity(String id) {
		return servicesRepository.findById(id);
	}

	/**
	 * Delete service detail entity.
	 *
	 * @param id the id
	 */
	public void deleteServiceDetailEntity(String id) {
		servicesRepository.deleteById(id);
	}

	/**
	 * Update service detail entity.
	 *
	 * @param id the id
	 * @param service the service
	 * @return the service detail
	 */
	public ServiceDetail updateServiceDetailEntity(String id, ServiceDetail service) {
		// search for the current service
		Optional<ServiceDetail> servicesData = servicesRepository.findById(id);
		if (servicesData.isPresent()) {
			ServiceDetail _services = servicesData.get();
			_services.setCost(service.getCost());
			// validate duplicates for name if name  was changed
			if(!_services.getName().equals(service.getName())) {
				_services.setName(service.getName());
				if (validateDuplicateServiceDetail(_services.getName(), _services.getType())) {
					return null;
				}
			}
			// validate duplicates for type, if type was changed
			if(!_services.getType().equals(service.getType())) {
				_services.setType(service.getType());
				if (validateDuplicateServiceDetail(_services.getName(), _services.getType())) {
					return null;
				}
			}
			return servicesRepository.save(_services);
		} else {
			return null;
		}

	}

	/**
	 * Gets the all services stored at db
	 *
	 * @return the all service detail entity
	 */
	public List<ServiceDetail> getAllServiceDetailEntity() {
		List<ServiceDetail> services = new ArrayList<ServiceDetail>();
		servicesRepository.findAll().forEach(services::add);
		return services;
	}

	

}
