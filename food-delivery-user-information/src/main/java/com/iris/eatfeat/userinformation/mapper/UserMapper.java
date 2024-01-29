package com.iris.eatfeat.userinformation.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.iris.eatfeat.userinformation.dto.UserDto;
import com.iris.eatfeat.userinformation.entity.User;

@Mapper
public interface UserMapper {

	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	User mapUserDTOToUser(UserDto userDto);

	UserDto mapUserToUserDTO(User user);

}
