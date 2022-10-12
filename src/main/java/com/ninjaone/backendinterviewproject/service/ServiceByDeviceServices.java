package com.ninjaone.backendinterviewproject.service;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ninjaone.backendinterviewproject.dto.TotalServiceCostDTO;
import com.ninjaone.backendinterviewproject.model.Device;
import com.ninjaone.backendinterviewproject.model.ServiceDetail;

/**
 * The Class ServiceByDeviceServices. this class handles the operation between
 * services and devices
 */
@Service
public class ServiceByDeviceServices {

	/** The device services. */
	DeviceService deviceServices;

	/** The serv detail services. */
	ServiceDetailService servDetailServices;

	/**
	 * Instantiates a new service by device services.
	 *
	 * @param servDetailServices the serv detail services
	 * @param deviceServices     the device services
	 */
	public ServiceByDeviceServices(ServiceDetailService servDetailServices, DeviceService deviceServices) {
		this.servDetailServices = servDetailServices;
		this.deviceServices = deviceServices;
	}

	/**
	 * Gets the total services cost.
	 *
	 * it looks for all the services in the DB. count the number of devices that
	 * uses each services multiply them to the cost and the sum
	 * 
	 * @return the total services cost
	 */
	public TotalServiceCostDTO getTotalServicesCost() {
		// get all the services registered at database
		List<ServiceDetail> services = servDetailServices.getAllServiceDetailEntity();
		return getServicesCostByServices(services);
	}

	/**
	 * Gets the services cost by services. it receives a list of services. count the
	 * number of devices that uses each service. then multiply them to the cost and
	 * finally sum
	 * 
	 * @param devices the devices
	 * @return the services cost by devices
	 */
	public TotalServiceCostDTO getServicesCostByServices(List<ServiceDetail> services) {

		TotalServiceCostDTO totalCostDTO = new TotalServiceCostDTO();
		totalCostDTO.setDescription("Explanation:");
		var wrapper = new Object() {
			double totalCost = 0;
		};
		// loop through the list of services, count the number of devices you have and
		// multiply for the cost
		// finally sum the results
		// TODO: change common sum and multiply to streams sum and multiply to get a
		// better performance
		services.stream().forEach(service -> {
			double total = service.getCost() * service.getDevices().size();
			// include the result to the output object
			totalCostDTO.addCostByService(service.getName(), total);
			wrapper.totalCost += total;
		});

		totalCostDTO.setTotalCost("$" + wrapper.totalCost);
		return totalCostDTO;
	}

	/**
	 * Gets the total services cost by devices. it looks for all the devices in the
	 * DB. group by every type of service and then count the number of devices that
	 * uses each services multiply them to the cost and the sum
	 * 
	 * @return the services cost by devices
	 */
	public TotalServiceCostDTO getServicesCostByDevices() {
		return getServicesCostByDevices(deviceServices.getAllDeviceEntity());
	}

	/**
	 * Gets the services cost by devices. it receives a list of devices. group by
	 * every type of service and then count the number of devices that uses each
	 * service. then multiply them to the cost and finally sum
	 * 
	 * @param devices the devices
	 * @return the services cost by devices
	 */
	public TotalServiceCostDTO getServicesCostByDevices(List<Device> devices) {
		double totalCost = 0;
		TotalServiceCostDTO totalCostDTO = new TotalServiceCostDTO();
		totalCostDTO.setDescription("Explanation:");
		// group the services with device that used
		Map<String, Long> serviceByDevice = devices.stream()
				// create entries with each device and service, using the type and cost as a key
				// for the map
				.flatMap(device -> device.getServices().stream()
						.map(service -> new AbstractMap.SimpleEntry<>(device,
								service.getName() + "_" + service.getType() + "|" + service.getCost())))
				// grouping the services of which each device is a part of
				.collect(Collectors.groupingBy(Map.Entry::getValue,
						Collectors.mapping(Map.Entry::getKey, Collectors.counting())));

		// go through the map getting the cost and the total for determine service

		for (var entry : serviceByDevice.entrySet()) {
			var name = entry.getKey().split("_")[0];
			var cost = Double.parseDouble(entry.getKey().split("\\|")[1]);
			double total = cost * entry.getValue().doubleValue();
			// include the result to the output object
			totalCostDTO.addCostByService(name, total);
			totalCost += total;
		}

		totalCostDTO.setTotalCost("$" + totalCost);
		return totalCostDTO;

	}
}
