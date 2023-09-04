package com.blogging_apis.service;

import java.util.List;

import com.blogging_apis.playloads.CategoryDto;

public interface CategoryService {
	
	//create
	public CategoryDto createCategory(CategoryDto categoryDto);
	
	//update
	public CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);

	//delete
	public void deleteCategory(Integer categoryId);
	
	//get
	public CategoryDto getCategory(Integer categoryId);

	
	//get all
	public List<CategoryDto> getAllCategory();

}
