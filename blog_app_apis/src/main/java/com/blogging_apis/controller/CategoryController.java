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
import com.blogging_apis.playloads.CategoryDto;
import com.blogging_apis.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
	
	@Autowired
	private CategoryService cr;
	
	//post -create
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto)
	{
		CategoryDto createdCategory = cr.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(createdCategory,HttpStatus.CREATED);
		
	}
	
	//put -update
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable Integer categoryId) 
	{
	
		CategoryDto updateCategory = cr.updateCategory(categoryDto, categoryId);
	return new ResponseEntity<CategoryDto>(updateCategory,HttpStatus.OK);

	}

	//delete -delete
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponce> deleteCategory(@PathVariable Integer categoryId)
	{
		cr.deleteCategory(categoryId);
		return new ResponseEntity<ApiResponce>(new ApiResponce("Category Deleted Succesfully !!",true),HttpStatus.OK);
	}
	
	
	//get -get
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer categoryId)
	{
		CategoryDto category = cr.getCategory(categoryId);
		return new ResponseEntity<CategoryDto>(category,HttpStatus.FOUND);
	}
	
	
	//get -getall
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategory()
	{
		return new ResponseEntity<List<CategoryDto>>(cr.getAllCategory(),HttpStatus.FOUND);
	}
	

}
