package com.springboot.rest.test.truck;

import com.springboot.rest.test.car.Car;
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
public class TruckResource {

	@Autowired
	private TruckRepository truckRepository;
	@Autowired
	private VehicleRepository vehicleRepository;

	@GetMapping("/trucks")
	public List<Truck> retrieveAllTruck() {
		return truckRepository.findAll();
	}

	@GetMapping("/trucks/{id}")
	public Truck retrieveTruck(@PathVariable long id) {
		Optional<Truck> truck = truckRepository.findById(id);

		if (!truck.isPresent())
			throw new TruckNotFoundException("id-" + id);

		return truck.get();
	}

	@GetMapping(path = "/truck/search")
	public Optional<Truck> getAllByCriteria(@RequestParam MultiValueMap<String, String> criteria) {
		if (criteria.containsKey("id")) {
			String id = criteria.getFirst("id");
			if (criteria.containsKey("brandName")) {
				String brandName = criteria.get("brandName").get(0);
				return truckRepository.findByIdAndBrandName(id, brandName);
			}
			return truckRepository.findById(Long.parseLong(id));
		}
		else if (criteria.containsKey("brandName")) {
			return truckRepository.findByBrandName(criteria.get("brandName").get(0));
		}
		return null;
	}

	@DeleteMapping("/trucks/{id}")
	public void deleteTruck(@PathVariable long id) {
		truckRepository.deleteById(id);
	}

	@PostMapping("/trucks")
	public ResponseEntity<Object> createTruck(@RequestBody Truck truck) {
		Truck savedTruck = truckRepository.save(truck);
		Vehicle vehicle = new Vehicle();
		vehicle.setId(truck.getId());
		vehicle.setType("Car");
		vehicle.setTimestamp(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
		vehicleRepository.save(vehicle);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedTruck.getId()).toUri();

		return ResponseEntity.created(location).build();

	}
	
	@PutMapping("/trucks/{id}")
	public ResponseEntity<Object> updateTruck(@RequestBody Truck truck, @PathVariable long id) {

		Optional<Truck> carOptional = truckRepository.findById(id);

		if (!carOptional.isPresent())
			return ResponseEntity.notFound().build();

		truck.setId(id);
		
		truckRepository.save(truck);

		return ResponseEntity.noContent().build();
	}
}
