package com.springboot.rest.test.airplane;

import com.springboot.rest.test.vehicle.Vehicle;
import com.springboot.rest.test.vehicle.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class AirplaneResource {

	@Autowired
	private AirplaneRepository airplaneRepository;
	@Autowired
	private VehicleRepository vehicleRepository;

	@GetMapping("/airplanes")
	public List<Airplane> retrieveAllAirplane() {
		return airplaneRepository.findAll();
	}

	@GetMapping("/airplanes/{id}")
	public Airplane retrieveAirplane(@PathVariable long id) {
		Optional<Airplane> airplane = airplaneRepository.findById(id);

		if (!airplane.isPresent())
			throw new AirplaneNotFoundException("id-" + id);

		return airplane.get();
	}

	@GetMapping(path = "/airplanes/search")
	public Optional<Airplane> getAllByCriteria(@RequestParam MultiValueMap<String, String> criteria) {
		if (criteria.containsKey("id")) {
			String id = criteria.getFirst("id");
			if (criteria.containsKey("airline")) {
				String airline = criteria.get("airline").get(0);
				return airplaneRepository.findByIdAndAirline(id, airline);
			}
			return airplaneRepository.findById(Long.parseLong(id));
		}
		else if (criteria.containsKey("airline")) {
			return airplaneRepository.findByAirline(criteria.get("airline").get(0));
		}
		return null;
	}

	@DeleteMapping("/airplanes/{id}")
	public void deleteAirplane(@PathVariable long id) {
		airplaneRepository.deleteById(id);
	}

	@PostMapping("/airplanes")
	public ResponseEntity<Object> createAirplane(@RequestBody Airplane airplane) {
		Airplane savedAirplane = airplaneRepository.save(airplane);
		Vehicle vehicle = new Vehicle();
		vehicle.setId(airplane.getId());
		vehicle.setType("Drone");
		vehicle.setTimestamp(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
		vehicleRepository.save(vehicle);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/"+airplane.getId().toString())
				.buildAndExpand(savedAirplane.getId()).toUri();

		return ResponseEntity.created(location).build();

	}
	
	@PutMapping("/airplanes/{id}")
	public ResponseEntity<Object> updateAirplane(@RequestBody Airplane airplane, @PathVariable long id) {

		Optional<Airplane> airplaneOptional = airplaneRepository.findById(id);

		if (!airplaneOptional.isPresent())
			return ResponseEntity.notFound().build();

		airplane.setId(id);
		
		airplaneRepository.save(airplane);

		return ResponseEntity.noContent().build();
	}
}
