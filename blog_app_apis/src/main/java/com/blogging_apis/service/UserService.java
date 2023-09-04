package com.blogging_apis.service;

import java.util.List;

import com.blogging_apis.playloads.UserDto;

public interface UserService {
	
	UserDto createUser(UserDto userDto);
	
	UserDto updateUser(UserDto userDto,int userId);
	
	UserDto getUserById(int userId);
	
	List<UserDto> getAllUsers();
	
	void deleteUser(int userId);

}
