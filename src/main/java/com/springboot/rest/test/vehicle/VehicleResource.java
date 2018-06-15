package com.springboot.rest.test.vehicle;

import com.springboot.rest.test.airplane.AirplaneRepository;
import com.springboot.rest.test.amphibian.AmphibianRepository;
import com.springboot.rest.test.boat.BoatRepository;
import com.springboot.rest.test.car.CarRepository;
import com.springboot.rest.test.drone.DroneRepository;
import com.springboot.rest.test.truck.TruckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class VehicleResource {

	@Autowired
	private VehicleRepository vehicleRepository;
	@Autowired
	private AirplaneRepository airplaneRepository;
	@Autowired
	private CarRepository carRepository;
	@Autowired
	private TruckRepository truckRepository;
	@Autowired
	private DroneRepository droneRepository;
	@Autowired
	private AmphibianRepository amphibianRepository;
	@Autowired
	private BoatRepository boatRepository;

	@DeleteMapping("/deleteRecent")
	public void deleteVehicle() {
		Vehicle vehicle = vehicleRepository.findLatest();
		long id = vehicle.getId();
		String type = vehicle.getType();
		switch (type) {
			case "Airplane":
				airplaneRepository.deleteById(id);
				break;
			case "Car":
				carRepository.deleteById(id);
				break;
			case "Truck":
				truckRepository.deleteById(id);
				break;
			case "Drone":
				droneRepository.deleteById(id);
				break;
			case "Amphibian":
				amphibianRepository.deleteById(id);
				break;
			case "Boat":
				boatRepository.deleteById(id);
				break;
			default:
				break;
		}
		vehicleRepository.deleteById(id);
	}

}
