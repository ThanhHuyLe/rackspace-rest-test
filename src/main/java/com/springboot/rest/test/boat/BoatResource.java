package com.springboot.rest.test.boat;

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
public class BoatResource {

	@Autowired
	private BoatRepository boatRepository;
	@Autowired
	private VehicleRepository vehicleRepository;

	@GetMapping("/boats")
	public List<Boat> retrieveAllBoat() {
		return boatRepository.findAll();
	}

	@GetMapping("/boats/{id}")
	public Boat retrieveBoat(@PathVariable long id) {
		Optional<Boat> boat = boatRepository.findById(id);

		if (!boat.isPresent())
			throw new BoatNotFoundException("id-" + id);

		return boat.get();
	}

	@GetMapping(path = "/boats/search")
	public Optional<Boat> getAllByCriteria(@RequestParam MultiValueMap<String, String> criteria) {
		if (criteria.containsKey("id")) {
			String id = criteria.getFirst("id");
			if (criteria.containsKey("brandName")) {
				String brandName = criteria.get("brandName").get(0);
				return boatRepository.findByIdAndBrandName(id, brandName);
			}
			return boatRepository.findById(Long.parseLong(id));
		}
		else if (criteria.containsKey("brandName")) {
			return boatRepository.findByBrandName(criteria.get("brandName").get(0));
		}
		return null;
	}

	@DeleteMapping("/boats/{id}")
	public void deleteBoat(@PathVariable long id) {
		boatRepository.deleteById(id);
	}

	@PostMapping("/boats")
	public ResponseEntity<Object> createBoat(@RequestBody Boat boat) {
		Boat savedBoat = boatRepository.save(boat);
		Vehicle vehicle = new Vehicle();
		vehicle.setId(boat.getId());
		vehicle.setType("Drone");
		vehicle.setTimestamp(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
		vehicleRepository.save(vehicle);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/"+ boat.getId().toString())
				.buildAndExpand(savedBoat.getId()).toUri();

		return ResponseEntity.created(location).build();

	}

	@PutMapping("/boats/{id}")
	public ResponseEntity<Object> updateBoat(@RequestBody Boat boat, @PathVariable long id) {

		Optional<Boat> boatOptional = boatRepository.findById(id);

		if (!boatOptional.isPresent())
			return ResponseEntity.notFound().build();

		boat.setId(id);
		
		boatRepository.save(boat);

		return ResponseEntity.noContent().build();
	}
}
