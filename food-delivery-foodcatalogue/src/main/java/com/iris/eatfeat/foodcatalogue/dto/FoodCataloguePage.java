package com.iris.eatfeat.foodcatalogue.dto;

import java.util.List;

import com.iris.eatfeat.foodcatalogue.entity.FoodItem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FoodCataloguePage {

	private List<FoodItem> foodItemsList;

	private Restaurant restaurant;

}
