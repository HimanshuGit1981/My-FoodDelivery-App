package com.iris.eatfeat.foodcatalogue.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FoodItemDTO {

	private int id;
	private String itemName;
	private String itemDescription;
	private Boolean isVeg;
	private Double price;
	private Integer restaurantId;
	private Integer quantity;

}
