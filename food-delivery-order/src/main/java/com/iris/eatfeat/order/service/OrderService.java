package com.iris.eatfeat.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.iris.eatfeat.order.dto.OrderDTO;
import com.iris.eatfeat.order.dto.OrderDTOFromFrontEnd;
import com.iris.eatfeat.order.dto.UserDTO;
import com.iris.eatfeat.order.entity.Order;
import com.iris.eatfeat.order.mapper.OrderMapper;
import com.iris.eatfeat.order.repo.OrderRepo;

@Service
public class OrderService {

	@Autowired
	private OrderRepo orderRepo;

	@Autowired
	private SequenceGenerator sequenceGenerator;

	@Autowired
	private RestTemplate restTemplate;

	public OrderDTO saveOrderInDB(OrderDTOFromFrontEnd orderDetails) {

		Integer newOrderId = sequenceGenerator.generateNextOrderId();
		UserDTO userDTO = fetchUserDetailsFromUserId(orderDetails.getUserId());

		Order orderTobeSaved = new Order(newOrderId, orderDetails.getFoodItemsList(), orderDetails.getRestaurant(),
				userDTO);
		orderRepo.save(orderTobeSaved);
		return OrderMapper.INSTANCE.mapOrderToOrderDTO(orderTobeSaved);
	}

	private UserDTO fetchUserDetailsFromUserId(Integer userId) {

		return restTemplate.getForObject("http://USER-SERVICE/user/fetchUserById/" + userId, UserDTO.class);
	}
}
