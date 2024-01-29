package com.iris.eatfeat.restaurantlisting.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.iris.eatfeat.restaurantlisting.dto.RestaurantDTO;
import com.iris.eatfeat.restaurantlisting.entity.Restaurant;

@Mapper
public interface RestaurantMapper {

	RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);

	Restaurant mapRestaurantDTOToRestaurant(RestaurantDTO restaurantDTO);

	RestaurantDTO mapRestaurantToRestaurantDTO(Restaurant eestaurant);
}
