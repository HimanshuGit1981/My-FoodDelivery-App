package com.iris.eatfeat.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodItemsDTO {
	private int id;
	private String itemName;
	private String itemDescription;
	private Boolean isVeg;
	private Double price;
	private Integer restaurantId;
	private Integer quantity;
}
