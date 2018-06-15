package com.springboot.rest.test.amphibian;

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
public class AmphibianResource {

	@Autowired
	private AmphibianRepository amphibianRepository;
	@Autowired
	private VehicleRepository vehicleRepository;

	@GetMapping("/amphibians")
	public List<Amphibian> retrieveAllAmphibian() {
		return amphibianRepository.findAll();
	}

	@GetMapping("/amphibians/{id}")
	public Amphibian retrieveAmphibian(@PathVariable long id) {
		Optional<Amphibian> amphibian = amphibianRepository.findById(id);

		if (!amphibian.isPresent())
			throw new AmphibianNotFoundException("id-" + id);

		return amphibian.get();
	}

	@GetMapping(path = "/amphibians/search")
	public Optional<Amphibian> getAllByCriteria(@RequestParam MultiValueMap<String, String> criteria) {
		if (criteria.containsKey("id")) {
			String id = criteria.getFirst("id");
			if (criteria.containsKey("specie")) {
				String specie = criteria.get("specie").get(0);
				return amphibianRepository.findByIdAndSpecie(id, specie);
			}
			return amphibianRepository.findById(Long.parseLong(id));
		}
		else if (criteria.containsKey("specie")) {
			return amphibianRepository.findBySpecie(criteria.get("specie").get(0));
		}
		return null;
	}

	@DeleteMapping("/amphibians/{id}")
	public void deleteAmphibian(@PathVariable long id) {
		amphibianRepository.deleteById(id);
	}

	@PostMapping("/amphibians")
	public ResponseEntity<Object> createAmphibian(@RequestBody Amphibian amphibian) {
		Amphibian savedAmphibian = amphibianRepository.save(amphibian);
		Vehicle vehicle = new Vehicle();
		vehicle.setId(amphibian.getId());
		vehicle.setType("Drone");
		vehicle.setTimestamp(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
		vehicleRepository.save(vehicle);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/"+ amphibian.getId().toString())
				.buildAndExpand(savedAmphibian.getId()).toUri();

		return ResponseEntity.created(location).build();

	}

	@PutMapping("/amphibians/{id}")
	public ResponseEntity<Object> updateAmphibian(@RequestBody Amphibian amphibian, @PathVariable long id) {

		Optional<Amphibian> amphibianOptional = amphibianRepository.findById(id);

		if (!amphibianOptional.isPresent())
			return ResponseEntity.notFound().build();

		amphibian.setId(id);
		
		amphibianRepository.save(amphibian);

		return ResponseEntity.noContent().build();
	}
}
