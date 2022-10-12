package com.ninjaone.backendinterviewproject.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ninjaone.backendinterviewproject.dto.TotalServiceCostDTO;
import com.ninjaone.backendinterviewproject.model.Device;
import com.ninjaone.backendinterviewproject.model.ServiceDetail;
import com.ninjaone.backendinterviewproject.service.ServiceByDeviceServices;


/**
 * The Class ServiceByDeviceController.
 * this controller handle the interaction between services and devices 
 * @author ASUAREZ
 */
@RestController
@RequestMapping("/serviceByDevice")
public class ServiceByDeviceController {

	/** The service device services. */
	private final ServiceByDeviceServices serviceDeviceServices;

	/**
	 * Instantiates a new service by device controller.
	 *
	 * @param serviceDeviceServices the service device services
	 */
	public ServiceByDeviceController(ServiceByDeviceServices serviceDeviceServices) {
		this.serviceDeviceServices = serviceDeviceServices;
	}

	/**
	 * Gets the total services cost for the all the services registered
	 *
	 * @return the total services cost
	 */
	@GetMapping("/servicesCost")
	@ResponseStatus(HttpStatus.OK)
	private TotalServiceCostDTO getTotalServicesCost() {
		return serviceDeviceServices.getTotalServicesCost();
	} 
	
	/**
	 * Gets the total services cost for all the devices registered
	 *
	 * @return the total services
	 */
	@GetMapping("/servicesCostByDevices")
	@ResponseStatus(HttpStatus.OK)
	private TotalServiceCostDTO getServicesCostByDevices() {
		return serviceDeviceServices.getServicesCostByDevices();
	}

	/**
	 * Calculate services cost. for a receive device list
	 *
	 * @param devices the devices
	 * @return the total service cost DTO
	 */
	@PostMapping("/totalServiceCostByDevices")
	@ResponseStatus(HttpStatus.OK)
	private TotalServiceCostDTO calculateServicesCost(@RequestBody List<Device> devices) {
		return serviceDeviceServices.getServicesCostByDevices(devices);
	}
	
	/**
	 * Calculate services cost. for a receive service list
	 *
	 * @param devices the devices
	 * @return the total service cost DTO
	 */
	@PostMapping("/totalServiceCostByServices")
	@ResponseStatus(HttpStatus.OK)
	private TotalServiceCostDTO calculateServicesCostOfServices(@RequestBody List<ServiceDetail> services) {
		return serviceDeviceServices.getServicesCostByServices(services);
	}
}
