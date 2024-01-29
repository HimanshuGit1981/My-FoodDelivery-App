package com.iris.eatfeat.order.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class OrderNotSavedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public OrderNotSavedException(String message) {
		super(message);
	}

}
