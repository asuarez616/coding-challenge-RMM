package com.ninjaone.backendinterviewproject.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ninjaone.backendinterviewproject.model.Device;
import com.ninjaone.backendinterviewproject.model.ServiceDetail;

import lombok.Getter;
import lombok.Setter;

// TODO: Auto-generated Javadoc
/**
 * this class represents a the output object response for service cost .
 *
 * @author ASUAREZ
 */
/**
 * Gets the cost by service.
 *
 * @return the cost by service
 */

/**
 * Gets the cost by service.
 *
 * @return the cost by service
 */
@Getter

/**
 * Sets the cost by service.
 *
 * @param costByService the cost by service
 */

/**
 * Sets the cost by service.
 *
 * @param costByService the cost by service
 */
@Setter
public class TotalServiceCostDTO {

	/** The description. */
	private String description;

	/** The cost by service. */
	private Map<String, String> costByService;
	
	private String totalCost;

	/**
	 * Instantiates a new total service cost DTO.
	 */
	public TotalServiceCostDTO() {
		this.costByService = new HashMap<>();
	}


	/**
	 * Adds the cost by service
	 * checking if the key already exists on the map
	 *
	 * @param key the key
	 * @param total the total
	 */
	public void addCostByService(String key, Double total) {
		if (costByService.containsKey(key)) {
		double result=Double.parseDouble(costByService.get(key).replace("$","")) +total;
			costByService.put(key,"$" +result);
		} else {
			costByService.put(key, "$"+total);
		}

	}

}
