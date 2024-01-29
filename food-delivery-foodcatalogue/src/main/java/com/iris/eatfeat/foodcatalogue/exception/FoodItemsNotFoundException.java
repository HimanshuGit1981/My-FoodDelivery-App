package com.iris.eatfeat.foodcatalogue.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class FoodItemsNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public FoodItemsNotFoundException(String message) {
		super(message);
	}

}
