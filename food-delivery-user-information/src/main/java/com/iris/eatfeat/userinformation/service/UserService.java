package com.iris.eatfeat.userinformation.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.iris.eatfeat.userinformation.dto.UserDto;
import com.iris.eatfeat.userinformation.entity.User;
import com.iris.eatfeat.userinformation.mapper.UserMapper;
import com.iris.eatfeat.userinformation.repo.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;

	public UserDto addUser(UserDto userDto) {
		User saveUser = userRepo.save(UserMapper.INSTANCE.mapUserDTOToUser(userDto));
		return UserMapper.INSTANCE.mapUserToUserDTO(saveUser);
	}

	public ResponseEntity<UserDto> fetchUserById(Integer userId) {
		Optional<User> fetchedUserById = userRepo.findById(userId);
		if (fetchedUserById.isPresent()) {
			UserDto mapUserToUserDTO = UserMapper.INSTANCE.mapUserToUserDTO(fetchedUserById.get());
			return new ResponseEntity<UserDto>(mapUserToUserDTO, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

	}

}
