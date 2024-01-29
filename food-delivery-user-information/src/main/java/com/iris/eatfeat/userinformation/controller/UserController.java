package com.iris.eatfeat.userinformation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iris.eatfeat.userinformation.dto.UserDto;
import com.iris.eatfeat.userinformation.exception.UserNotFoundException;
import com.iris.eatfeat.userinformation.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

	private Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@PostMapping("/addUser")
	public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) {
		logger.info("addUser called with UserId: {}, UserName: {}", userDto.getUserId(), userDto.getUserName());
		UserDto savedUser = userService.addUser(userDto);
		if(savedUser==null) {
			StringBuilder errMsg = new StringBuilder("User not added");
			logger.error(errMsg.toString());
			throw new UserNotFoundException(errMsg.toString());
		}
		return new ResponseEntity<UserDto>(savedUser, HttpStatus.CREATED);
	}

	@GetMapping("/fetchUserById/{userId}")
	public ResponseEntity<UserDto> fetchUserById(@PathVariable Integer userId) {
		ResponseEntity<UserDto> fetchUserById = userService.fetchUserById(userId);
		
		if (fetchUserById.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
			StringBuilder errMsg = new StringBuilder("User Not Found for UserId: ").append(userId);
			logger.error(errMsg.toString());
			throw new UserNotFoundException(errMsg.toString());
		}

		return fetchUserById;
	}
}
