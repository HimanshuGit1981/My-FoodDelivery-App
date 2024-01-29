package com.iris.eatfeat.foodcatalogue.controller;

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

import com.iris.eatfeat.foodcatalogue.dto.FoodCataloguePage;
import com.iris.eatfeat.foodcatalogue.dto.FoodItemDTO;
import com.iris.eatfeat.foodcatalogue.exception.FoodItemsNotFoundException;
import com.iris.eatfeat.foodcatalogue.exception.RestaurantNotFoundException;
import com.iris.eatfeat.foodcatalogue.service.FoodCatalogueService;

@RestController
@RequestMapping("/foodCatalogue")
@CrossOrigin
public class FoodCatalogueController {

	private Logger logger = LoggerFactory.getLogger(FoodCatalogueController.class);

	@Autowired
	private FoodCatalogueService foodCatalogueService;

	@PostMapping("/addFoodItem")
	public ResponseEntity<FoodItemDTO> addFoodItem(@RequestBody FoodItemDTO foodItemDTO) {
		logger.info("addFoodItem called with FoodItemId: {}", foodItemDTO.getId());
		FoodItemDTO foodItemSaved = foodCatalogueService.addFoodItem(foodItemDTO);
		return new ResponseEntity<FoodItemDTO>(foodItemSaved, HttpStatus.CREATED);
	}

	@GetMapping("/fetchRestaurantAndFoodItemsById/{restaurantId}")
	public ResponseEntity<FoodCataloguePage> fetchRestaurantDetailsWithFoodmenu(@PathVariable Integer restaurantId) {
		logger.info("fetchRestaurantAndFoodItemsById called with restaurantId: {}", restaurantId);
		FoodCataloguePage foodCataloguePage = foodCatalogueService.fetchFoodCatalogePageDetails(restaurantId);

		if (foodCataloguePage.getRestaurant() == null) {
			StringBuilder errMsg = new StringBuilder("Restaurant not found with Id: ").append(restaurantId);
			logger.error(errMsg.toString());
			throw new RestaurantNotFoundException(errMsg.toString());
		}

		if (foodCataloguePage.getFoodItemsList().size() == 0) {
			StringBuilder errMsg = new StringBuilder("Food Items for the resuarnat with Id: ").append(restaurantId)
					.append(" not found");
			logger.error(errMsg.toString());
			throw new FoodItemsNotFoundException(errMsg.toString());
		}
		return new ResponseEntity<FoodCataloguePage>(foodCataloguePage, HttpStatus.OK);
	}
}
