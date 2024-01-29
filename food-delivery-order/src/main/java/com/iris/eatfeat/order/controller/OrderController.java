package com.iris.eatfeat.order.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iris.eatfeat.order.dto.OrderDTO;
import com.iris.eatfeat.order.dto.OrderDTOFromFrontEnd;
import com.iris.eatfeat.order.service.OrderService;

@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {

	private Logger logger = LoggerFactory.getLogger(OrderController.class);

	@Autowired
	private OrderService orderService;

	@PostMapping("/saveOrder")
	public ResponseEntity<OrderDTO> saveOrder(@RequestBody OrderDTOFromFrontEnd orderDetails) {
		logger.info("SaveOrder called with UserId: {} and RestaurantId: {}", orderDetails.getUserId(), orderDetails.getRestaurant().getId());
		OrderDTO orderSavedInDB = orderService.saveOrderInDB(orderDetails);
		return new ResponseEntity<OrderDTO>(orderSavedInDB, HttpStatus.CREATED);
	}
}
