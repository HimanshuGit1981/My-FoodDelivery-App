package com.iris.eatfeat.foodcatalogue.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.iris.eatfeat.foodcatalogue.dto.FoodItemDTO;
import com.iris.eatfeat.foodcatalogue.entity.FoodItem;

@Mapper
public interface FoodItemMapper {

	FoodItemMapper INSTANCE = Mappers.getMapper(FoodItemMapper.class);

	FoodItem mapFoodItemDTOToFoodItem(FoodItemDTO foodItemDTO);

	FoodItemDTO mapFoodItemToFoodItemDTO(FoodItem foodItem);

}
