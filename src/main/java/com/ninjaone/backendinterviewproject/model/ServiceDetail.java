package com.ninjaone.backendinterviewproject.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * this class represents a service
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

public class ServiceDetail {
	
	/** The id. */
	@Id
	private String id;
	
	/** The name. */
	private String name;
	
	/** The type. */
	private String type;
	
	/** The cost. */
	private double cost;

	/** The devices. */
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "services")
	@JsonIgnore
	private Set<Device> devices;

	public ServiceDetail() {
		this.devices = new HashSet<>();
	}
}
