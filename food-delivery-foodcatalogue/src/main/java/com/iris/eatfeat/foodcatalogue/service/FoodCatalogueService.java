package com.iris.eatfeat.foodcatalogue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.iris.eatfeat.foodcatalogue.dto.FoodCataloguePage;
import com.iris.eatfeat.foodcatalogue.dto.FoodItemDTO;
import com.iris.eatfeat.foodcatalogue.dto.Restaurant;
import com.iris.eatfeat.foodcatalogue.entity.FoodItem;
import com.iris.eatfeat.foodcatalogue.mapper.FoodItemMapper;
import com.iris.eatfeat.foodcatalogue.repo.FoodItemRepo;

@Service
public class FoodCatalogueService {

	@Autowired
	private FoodItemRepo foodItemRepo;

	@Autowired
	private RestTemplate restTemplate;

	public FoodItemDTO addFoodItem(FoodItemDTO foodItemDTO) {
		FoodItem savedFoodItem = foodItemRepo.save(FoodItemMapper.INSTANCE.mapFoodItemDTOToFoodItem(foodItemDTO));

		return FoodItemMapper.INSTANCE.mapFoodItemToFoodItemDTO(savedFoodItem);

	}

	public FoodCataloguePage fetchFoodCatalogePageDetails(Integer restaurantId) {
		List<FoodItem> foodItemsList = fetchFoodItemsList(restaurantId);
		Restaurant restaurant = fetchRestaurantDetailsFromRestaurantMicroService(restaurantId);
		FoodCataloguePage foodCataloguePage = createFoodCataloguePage(foodItemsList, restaurant);
		return foodCataloguePage;
	}

	private FoodCataloguePage createFoodCataloguePage(List<FoodItem> foodItemsList, Restaurant restaurant) {
		FoodCataloguePage foodCataloguePage = new FoodCataloguePage();
		foodCataloguePage.setFoodItemsList(foodItemsList);
		foodCataloguePage.setRestaurant(restaurant);
		return foodCataloguePage;
	}

	private Restaurant fetchRestaurantDetailsFromRestaurantMicroService(Integer restaurantId) {
		return restTemplate.getForObject("http://RESTAURANT-SERVICE/restaurant/fetchById/" + restaurantId,
				Restaurant.class);
	}

	private List<FoodItem> fetchFoodItemsList(Integer restaurantId) {
		return foodItemRepo.findByRestaurantId(restaurantId);
	}
}
