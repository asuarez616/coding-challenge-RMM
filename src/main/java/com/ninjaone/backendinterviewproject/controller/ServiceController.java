package com.ninjaone.backendinterviewproject.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ninjaone.backendinterviewproject.model.Device;
import com.ninjaone.backendinterviewproject.model.ServiceDetail;
import com.ninjaone.backendinterviewproject.service.ServiceDetailService;


/**
 * The Class ServiceController.
 * it contains all the related crud endpoints for a ServiceDetail
 * @author ASUAREZ
 */
@RestController
@RequestMapping("/services")
public class ServiceController {
	
	/** The services service. */
	private final ServiceDetailService servicesService;

	/**
	 * Instantiates a new service controller.
	 *
	 * @param servicesService the services service
	 */
	public ServiceController(ServiceDetailService servicesService) {
	        this.servicesService = servicesService;
	    }

	/**
	 * create services entity.
	 *
	 * @param services the services
	 * @return the service detail
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	private ServiceDetail postServiceEntity(@RequestBody ServiceDetail services) {
		//TODO change response object when validation fails it returns a null value without explanation
		return servicesService.saveServiceDetailEntity(services);
	}

	/**
	 * Gets the services entity by id
	 *
	 * @param id the id
	 * @return the services entity
	 */
	@GetMapping("/{id}")
	private ServiceDetail getServiceEntity(@PathVariable String id) {
		return servicesService.getServiceDetailEntity(id).orElseThrow();
	}

	/**
	 * Gets  all services entity.
	 *
	 * @return the all device entity
	 */
	@GetMapping()
	private List<ServiceDetail> getAllServiceEntity() {
		return servicesService.getAllServiceDetailEntity();
	}
	
	
	/**
	 * Delete services entity.
	 *
	 * @param id the id
	 */
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	private void deleteServiceEntity(@PathVariable String id) {
		servicesService.deleteServiceDetailEntity(id);
	}
	
	/**
	 * update device entity.
	 *
	 * @param id the id
	 * @param services the services
	 * @return the service detail
	 */
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	private ServiceDetail putDeviceEntity(@PathVariable String id,@RequestBody ServiceDetail services) {
		//TODO change response object when validation fails it returns a null value without explanation
		return servicesService.updateServiceDetailEntity(id, services);
	}
	
	
}
