package com.ninjaone.backendinterviewproject.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.Setter;

/**
 * this class represents a device
 * @author ASUAREZ
 */
@Entity

/**
 * Generate getter for all the properties
 *
 * @return property's value
 */
@Getter

/**
 * Generate setter for all the properties
 *
 * @param property's type the new property's type
 */
@Setter

public class Device {

	/** The id. */
	@Id
	private String id;

	/** The system name. */
	private String systemName;

	/** The type. */
	private String type;

	/** The services. */
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "DEVICES_BY_SERVICE", joinColumns = @JoinColumn(name = "DEVICE_ID", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "SERVICE_ID", referencedColumnName = "id"))
	private Set<ServiceDetail> services;

	public Device() {
		this.services = new HashSet<>();
	}

	/**
	 * Adds the service.
	 *
	 * @param service the service
	 */
	public void addService(ServiceDetail service) {
		this.services.add(service);
		service.getDevices().add(this);
	}

	/**
	 * Removes the service.
	 *
	 * @param id the id
	 */
	public void removeService(String id) {
		ServiceDetail service = this.services.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
		if (service != null) {
			this.services.remove(service);
			service.getDevices().remove(this);
		}
	}
}
