package com.springboot.rest.test.drone;

import com.springboot.rest.test.vehicle.Vehicle;
import com.springboot.rest.test.vehicle.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class DroneResource {

	@Autowired
	private DroneRepository droneRepository;
	@Autowired
	private VehicleRepository vehicleRepository;

	@GetMapping("/drones")
	public List<Drone> retrieveAllDrone() {
		return droneRepository.findAll();
	}

	@GetMapping("/drones/{id}")
	public Drone retrieveDrone(@PathVariable long id) {
		Optional<Drone> drone = droneRepository.findById(id);

		if (!drone.isPresent())
			throw new DroneNotFoundException("id-" + id);

		return drone.get();
	}

	@GetMapping(path = "/drones/search")
	public Optional<Drone> getAllByCriteria(@RequestParam MultiValueMap<String, String> criteria) {
		if (criteria.containsKey("id")) {
			String id = criteria.getFirst("id");
			if (criteria.containsKey("cyclePerMinute")) {
				String cyclePerMinute = criteria.get("cyclePerMinute").get(0);
				return droneRepository.findByIdAndCycleperMinute(id, cyclePerMinute);
			}
			return droneRepository.findById(Long.parseLong(id));
		}
		else if (criteria.containsKey("cyclePerMinute")) {
			return droneRepository.findByCycleperMinute(criteria.get("cyclePerMinute").get(0));
		}
		return null;
	}

	@DeleteMapping("/drones/{id}")
	public void deleteDrone(@PathVariable long id) {
		droneRepository.deleteById(id);
	}

	@PostMapping("/drones")
	public ResponseEntity<Object> createDrone(@RequestBody Drone drone) {
		Drone savedDrone = droneRepository.save(drone);
		Vehicle vehicle = new Vehicle();
		vehicle.setId(drone.getId());
		vehicle.setType("Drone");
		vehicle.setTimestamp(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
		vehicleRepository.save(vehicle);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/"+ drone.getId().toString())
				.buildAndExpand(savedDrone.getId()).toUri();

		return ResponseEntity.created(location).build();

	}

	@PutMapping("/drones/{id}")
	public ResponseEntity<Object> updateDrone(@RequestBody Drone drone, @PathVariable long id) {

		Optional<Drone> droneOptional = droneRepository.findById(id);

		if (!droneOptional.isPresent())
			return ResponseEntity.notFound().build();

		drone.setId(id);
		
		droneRepository.save(drone);

		return ResponseEntity.noContent().build();
	}
}
