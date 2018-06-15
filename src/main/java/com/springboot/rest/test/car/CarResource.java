package com.springboot.rest.test.car;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.springboot.rest.test.vehicle.Vehicle;
import com.springboot.rest.test.vehicle.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class CarResource {

	@Autowired
	private CarRepository carRepository;
	@Autowired
	private VehicleRepository vehicleRepository;

	@GetMapping("/cars")
	public List<Car> retrieveAllCar() {
		return carRepository.findAll();
	}

	@GetMapping("/cars/{id}")
	public Car retrieveCar(@PathVariable long id) {
		Optional<Car> car = carRepository.findById(id);

		if (!car.isPresent())
			throw new CarNotFoundException("id-" + id);

		return car.get();
	}

	@GetMapping(path = "/cars/search")
	public Optional<Car> getAllByCriteria(@RequestParam MultiValueMap<String, String> criteria) {
		if (criteria.containsKey("id")) {
			String id = criteria.getFirst("id");
			if (criteria.containsKey("brandName")) {
				String airline = criteria.get("brandName").get(0);
				return carRepository.findByIdAndBrandName(id, airline);
			}
			return carRepository.findById(Long.parseLong(id));
		}
		else if (criteria.containsKey("brandName")) {
			return carRepository.findByBrandName(criteria.get("brandName").get(0));
		}
		return null;
	}

	@DeleteMapping("/cars/{id}")
	public void deleteCar(@PathVariable long id) {
		carRepository.deleteById(id);
	}

	@PostMapping("/cars")
	public ResponseEntity<Object> createCar(@RequestBody Car car) {
		Car savedCar = carRepository.save(car);
		Vehicle vehicle = new Vehicle();
		vehicle.setId(car.getId());
		vehicle.setType("Car");
		vehicle.setTimestamp(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
		vehicleRepository.save(vehicle);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedCar.getId()).toUri();

		return ResponseEntity.created(location).build();

	}
	
	@PutMapping("/cars/{id}")
	public ResponseEntity<Object> updateCar(@RequestBody Car car, @PathVariable long id) {

		Optional<Car> carOptional = carRepository.findById(id);

		if (!carOptional.isPresent())
			return ResponseEntity.notFound().build();

		car.setId(id);
		
		carRepository.save(car);

		return ResponseEntity.noContent().build();
	}
}
