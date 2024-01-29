package com.iris.eatfeat.restaurantlisting.controller;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.iris.eatfeat.restaurantlisting.dto.RestaurantDTO;
import com.iris.eatfeat.restaurantlisting.exception.RestaurantNotFoundException;
import com.iris.eatfeat.restaurantlisting.service.RestaurantService;

@RestController
@RequestMapping("/restaurant")
@CrossOrigin
public class RestaurantController {

	private Logger logger = LoggerFactory.getLogger(RestaurantController.class);

	@Autowired
	private RestaurantService restaurantService;

	@GetMapping("/fetchAllRestaurants")
	public ResponseEntity<List<RestaurantDTO>> fetchAllRestaurants() {
		logger.info("fetchAllRestaurants() invoked");
		List<RestaurantDTO> allRestaurants = restaurantService.findAllRestaurants();
		
		if(allRestaurants.size()==0) {
			StringBuilder errMsg = new StringBuilder("No restaurant details found");
			logger.error(errMsg.toString());
			throw new RestaurantNotFoundException(errMsg.toString());
		}
		return new ResponseEntity<List<RestaurantDTO>>(allRestaurants, HttpStatus.OK);
	}

	@PostMapping("/addRestaurant")
	public ResponseEntity<RestaurantDTO> saveRestaurant(@RequestBody RestaurantDTO restaurantDTO) {

		logger.info("Save Restaurant with restaurant Id: {}", restaurantDTO.getId());

		RestaurantDTO addedRestaurant = restaurantService.addRestaurant(restaurantDTO);
		
		if(addedRestaurant==null) {
			StringBuilder errMsg = new StringBuilder("Restaurant not saved");
			logger.error(errMsg.toString());
			throw new RestaurantNotFoundException(errMsg.toString());
		}

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(addedRestaurant.getId()).toUri();
		return ResponseEntity.created(location).build();

	}

	@GetMapping("/fetchById/{id}")
	public ResponseEntity<RestaurantDTO> findRestaurantById(@PathVariable Integer id) {
		logger.info("Fetch Restaurant By restaurant Id: {}", id);
		ResponseEntity<RestaurantDTO> restaurantById = restaurantService.findRestaurantById(id);

		if (restaurantById.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
			StringBuilder errMsg = new StringBuilder("Restaurant Not Found for id: ").append(id);
			logger.error(errMsg.toString());
			throw new RestaurantNotFoundException(errMsg.toString());
		}
		return restaurantById;

	}

	@GetMapping("/fetchByName/{name}")
	public ResponseEntity<List<RestaurantDTO>> findRestaurantsByName(@PathVariable String name) {
		logger.info("Fetch Restaurant By restaurant name: {}", name);

		ResponseEntity<List<RestaurantDTO>> restaurantsByName = restaurantService.findRestaurantsByName(name);

		if (restaurantsByName.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
			StringBuilder errMsg = new StringBuilder("Restaurant Not Found for name: ").append(name);
			logger.error(errMsg.toString());
			throw new RestaurantNotFoundException(errMsg.toString());
		}

		return restaurantsByName;

	}

	@GetMapping("/fetchByAddress/{address}")
	public ResponseEntity<List<RestaurantDTO>> findRestaurantsByAddress(@PathVariable String address) {
		logger.info("Fetch Restaurant By restaurant address: {}", address);
		ResponseEntity<List<RestaurantDTO>> restaurantsByAddress = restaurantService.findRestaurantsByAddress(address);

		if (restaurantsByAddress.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
			StringBuilder errMsg = new StringBuilder("Restaurant Not Found for address: ").append(address);
			logger.error(errMsg.toString());
			throw new RestaurantNotFoundException(errMsg.toString());
		}

		return restaurantsByAddress;

	}

	@GetMapping("/fetchByCity/{city}")
	public ResponseEntity<List<RestaurantDTO>> findRestaurantsByCity(@PathVariable String city) {
		logger.info("Fetch Restaurant By restaurant city: {}", city);

		ResponseEntity<List<RestaurantDTO>> restaurantsByCity = restaurantService.findRestaurantsByCity(city);

		if (restaurantsByCity.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
			StringBuilder errMsg = new StringBuilder("Restaurant Not Found for city: ").append(city);
			logger.error(errMsg.toString());
			throw new RestaurantNotFoundException(errMsg.toString());
		}

		return restaurantsByCity;

	}
}
