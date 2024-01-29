package com.iris.eatfeat.restaurantlisting.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.iris.eatfeat.restaurantlisting.dto.RestaurantDTO;
import com.iris.eatfeat.restaurantlisting.entity.Restaurant;
import com.iris.eatfeat.restaurantlisting.mapper.RestaurantMapper;
import com.iris.eatfeat.restaurantlisting.repo.RestaurantRepo;

@Service
public class RestaurantService {

	@Autowired
	private RestaurantRepo restaurantRepo;

	public List<RestaurantDTO> findAllRestaurants() {
		List<Restaurant> restaurants = restaurantRepo.findAll();
		List<RestaurantDTO> restaurantDTOList = restaurants.stream()
				.map(restaurant -> RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(restaurant))
				.collect(Collectors.toList());
		return restaurantDTOList;

	}

	public RestaurantDTO addRestaurant(RestaurantDTO restaurantDTO) {
		Restaurant saveRestaurant = restaurantRepo
				.save(RestaurantMapper.INSTANCE.mapRestaurantDTOToRestaurant(restaurantDTO));
		return RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(saveRestaurant);
	}

	public ResponseEntity<RestaurantDTO> findRestaurantById(Integer id) {
		Optional<Restaurant> restaurant = restaurantRepo.findById(id);
		if (!restaurant.isPresent()) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

		RestaurantDTO mapRestaurantToRestaurantDTO = RestaurantMapper.INSTANCE
				.mapRestaurantToRestaurantDTO(restaurant.get());
		return new ResponseEntity<RestaurantDTO>(mapRestaurantToRestaurantDTO, HttpStatus.OK);

	}

	public ResponseEntity<List<RestaurantDTO>> findRestaurantsByName(String name) {
		List<Restaurant> restaurants = restaurantRepo.findByNameLike(name);
		return getListOfRestaurantDTOResponseEntity(restaurants);
	}

	public ResponseEntity<List<RestaurantDTO>> findRestaurantsByAddress(String address) {
		List<Restaurant> restaurants = restaurantRepo.findByAddressLike(address);
		return getListOfRestaurantDTOResponseEntity(restaurants);
	}

	public ResponseEntity<List<RestaurantDTO>> findRestaurantsByCity(String city) {
		List<Restaurant> restaurants = restaurantRepo.findByCityLike(city);
		return getListOfRestaurantDTOResponseEntity(restaurants);
	}

	private ResponseEntity<List<RestaurantDTO>> getListOfRestaurantDTOResponseEntity(List<Restaurant> restaurants) {
		if (restaurants.isEmpty()) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

		}

		List<RestaurantDTO> restaurantDTOList = restaurants.stream()
				.map(restaurant -> RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(restaurant))
				.collect(Collectors.toList());

		return new ResponseEntity<List<RestaurantDTO>>(restaurantDTOList, HttpStatus.OK);
	}
}
