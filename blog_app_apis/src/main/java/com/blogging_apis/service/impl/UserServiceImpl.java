package com.blogging_apis.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogging_apis.entities.User;
import com.blogging_apis.exceptions.ResourceNotFoundException;
import com.blogging_apis.playloads.UserDto;
import com.blogging_apis.repository.UserRepository;
import com.blogging_apis.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	//User Repository object inject
	@Autowired
	private UserRepository ur;
	
	@Autowired
	private ModelMapper mp;

	//create user method
	@Override
	public UserDto createUser(UserDto userDto) {
		
		User user=dtoToUser(userDto);
		User saveUser=ur.save(user);
		UserDto userToDto = userToDto(saveUser);
		return userToDto;
	}

	//update user method
	@Override
	public UserDto updateUser(UserDto userDto,int userId) {
		User user=ur.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id", userId));

			user.setName(userDto.getName());
			user.setEmail(userDto.getEmail());
			user.setPassword(userDto.getPassword());
			user.setAbout(userDto.getAbout());
			
			User updateUser = ur.save(user);
			return userToDto(updateUser);
		
	}

	//delete user method
	@Override
	public void deleteUser(int userId) {
		User user=ur.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "Id", userId));
		ur.delete(user);

	}


	//get user by id method
	@Override
	public UserDto getUserById(int userId) {
		User user=ur.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "Id", userId));
		
		return userToDto(user);
	}

	//get all users method
	@Override
	public List<UserDto> getAllUsers() {
		List<User> users=ur.findAll();
		
		List<UserDto> userDtos=new ArrayList<UserDto>();
		
//		for(User user:users)
//			userDtos.add(userToDto(user));
		
		users.forEach((user)->{
			userDtos.add(userToDto(user));
			});
		return userDtos;
	}
	
	//convert Dto to User
	private User dtoToUser(UserDto userDto)
	{
		User user=mp.map(userDto,User.class);
		
		//logic
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
		
		 return user;
	}
	
	//convert User to Dto
	private UserDto userToDto(User user)
	{
		UserDto userDto=mp.map(user, UserDto.class);
		
		//logic
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());
		
		return userDto;
	}

	

}
