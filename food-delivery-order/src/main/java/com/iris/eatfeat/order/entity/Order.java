package com.iris.eatfeat.order.entity;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.iris.eatfeat.order.dto.FoodItemsDTO;
import com.iris.eatfeat.order.dto.Restaurant;
import com.iris.eatfeat.order.dto.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("order")
public class Order {

	private Integer orderId;
	private List<FoodItemsDTO> foodItemsList;
	private Restaurant restaurant;
	private UserDTO userDTO;
}
