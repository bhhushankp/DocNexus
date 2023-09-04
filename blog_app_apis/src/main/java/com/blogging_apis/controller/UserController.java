package com.blogging_apis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogging_apis.playloads.ApiResponce;
import com.blogging_apis.playloads.UserDto;
import com.blogging_apis.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

	
	@Autowired
	private UserService us;
	
	//Post - create user
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto)
	{
		UserDto createdUserDto=us.createUser(userDto);
		return new ResponseEntity<>(createdUserDto,HttpStatus.CREATED);
	}
	
	//Put - update User
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable int userId)
	{
		UserDto updatedUser=us.updateUser(userDto,userId);
		return new ResponseEntity<UserDto>(updatedUser,HttpStatus.OK);
	}
	
	//Delete - Delete User
		@DeleteMapping("/{userId}")
		public ResponseEntity<ApiResponce> deleteUser(@PathVariable("userId") int userId)
		{
			us.deleteUser(userId);
			return new ResponseEntity<ApiResponce>(new ApiResponce("user deleted successfully",true),HttpStatus.OK);
			
		}
	
	//Get All User
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUser()
	{
		return new ResponseEntity<List<UserDto>>(us.getAllUsers(),HttpStatus.OK);
	}
	
	//Get User
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUser(@PathVariable int userId)
	{
		return new ResponseEntity<UserDto>(us.getUserById(userId),HttpStatus.OK);
	}
	
}
