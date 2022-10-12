package com.ninjaone.backendinterviewproject.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ninjaone.backendinterviewproject.database.DeviceRepository;
import com.ninjaone.backendinterviewproject.model.Device;


/**
 * The Class DeviceService. this class allows the interaction between the
 * controller and the model include the business logic for devices entities
 *  @author ASUAREZ
 */

@Service
public class DeviceService {

	/** The device repository. */
	private final DeviceRepository deviceRepository;

	/**
	 * Instantiates a new device service.
	 *
	 * @param deviceRepository the device repository
	 */
	public DeviceService(DeviceRepository deviceRepository) {
		this.deviceRepository = deviceRepository;
	}

	/**
	 * create a new device entity.
	 *
	 * @param device the device
	 * @return the device
	 */
	public Device saveDeviceEntity(Device device) {
		if (!validateDuplicateDevice(device.getSystemName(), device.getType())) {
			return deviceRepository.save(device);
		}
		return null;
	}

	/**
	 * Update device entity.
	 *
	 *
	 * @param id     the id
	 * @param device the device
	 * @return the device
	 */
	public Device updateDeviceEntity(String id, Device device) {
		Optional<Device> deviceData = deviceRepository.findById(id);
		if (deviceData.isPresent()) {
			Device _device = deviceData.get();
			// validate duplicates if name or type change
			if (!_device.getSystemName().equals(_device.getSystemName())) {
				_device.setSystemName(device.getSystemName());
				if (validateDuplicateDevice(device.getSystemName(), device.getType())) {
					return null;
				}
			}
			if (!_device.getType().equals(_device.getType())) {
				_device.setType(device.getType());
				if (validateDuplicateDevice(device.getSystemName(), device.getType())) {
					return null;
				}
			}
			return deviceRepository.save(device);
		} else {
			return null;
		}

	}

	/**
	 * Gets the device entity by id.
	 *
	 * @param id the id
	 * @return the device entity
	 */
	public Optional<Device> getDeviceEntity(String id) {
		return deviceRepository.findById(id);
	}

	/**
	 * Gets the all device entity.
	 *
	 * @return the all device entity
	 */
	public List<Device> getAllDeviceEntity() {
		List<Device> devices = new ArrayList<Device>();
		deviceRepository.findAll().forEach(devices::add);
		return devices;
	}

	/**
	 * Delete device entity.
	 *
	 * @param id the id
	 */
	public void deleteDeviceEntity(String id) {
		deviceRepository.deleteById(id);
	}

	/**
	 * Validate duplicate device using params.
	 *
	 * @param systemName the system name
	 * @param type       the type
	 * @return true, if successful
	 */
	//TODO: create validators and remove this validation
	public boolean validateDuplicateDevice(String systemName, String type) {
		return deviceRepository.findBySystemNameAndType(systemName, type).size() != 0 ? true : false;
	}

}
