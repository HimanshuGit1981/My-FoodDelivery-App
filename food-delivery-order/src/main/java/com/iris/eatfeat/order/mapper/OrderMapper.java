package com.iris.eatfeat.order.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.iris.eatfeat.order.dto.OrderDTO;
import com.iris.eatfeat.order.entity.Order;

@Mapper
public interface OrderMapper {

	OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

	Order mapOrderDTOToOrder(OrderDTO orderDTO);

	OrderDTO mapOrderToOrderDTO(Order order);
}
