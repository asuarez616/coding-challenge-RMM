package com.ninjaone.backendinterviewproject.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ninjaone.backendinterviewproject.model.Device;
import com.ninjaone.backendinterviewproject.service.DeviceService;


/**
 * The Class DeviceController.
 * this class represent the controller for devices
 * @author ASUAREZ
 */
@RestController
@RequestMapping("/devices")
public class DeviceController {
	
	/** The device service. */
	private final DeviceService deviceService;

	/**
	 * Instantiates a new device controller.
	 *
	 * @param deviceService the device service
	 */
	public DeviceController(DeviceService deviceService) {
		this.deviceService = deviceService;
	}

	/**
	 * Create device entity.
	 *
	 * @param Device the device
	 * @return the device
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	private Device postDeviceEntity(@RequestBody Device Device) {
		//TODO change response object when validation fails it returns a null value without explanation
		return deviceService.saveDeviceEntity(Device);
	}

	/**
	 * update device entity.
	 *
	 * @param id the id
	 * @param Device the device
	 * @return the device
	 */
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	private Device putDeviceEntity(@PathVariable String id, @RequestBody Device Device) {
		//TODO change response object when validation fails it returns a null value without explanation
		return deviceService.updateDeviceEntity(id, Device);
	}

		
	/**
	 * Gets the device entity by id.
	 *
	 * @param id the id
	 * @return the device entity
	 */
	@GetMapping("/{id}")
	private Device getDeviceEntity(@PathVariable String id) {
		return deviceService.getDeviceEntity(id).orElseThrow();
	}

	/**
	 * Gets the all device entity.
	 *
	 * @return the all device entity
	 */
	@GetMapping()
	private List<Device> getAllDeviceEntity() {
		return deviceService.getAllDeviceEntity();
	}

	/**
	 * Delete device entity.
	 *
	 * @param id the id
	 */
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	private void deleteDeviceEntity(@PathVariable String id) {
		deviceService.deleteDeviceEntity(id);
	}

}
