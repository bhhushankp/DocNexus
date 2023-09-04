package com.blogging_apis.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogging_apis.entities.Category;
import com.blogging_apis.exceptions.ResourceNotFoundException;
import com.blogging_apis.playloads.CategoryDto;
import com.blogging_apis.repository.CategoryRepository;
import com.blogging_apis.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository cr;
	
	@Autowired
	private ModelMapper mp;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category = mp.map(categoryDto, Category.class);
		
		Category saveCategory = cr.save(category);
		
		return mp.map(saveCategory, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		Category cat=cr.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "categoryId", categoryId));
		
		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		cat.setCategoryDescription(categoryDto.getCategoryDescription());
		
		Category updatedCategory = cr.save(cat);
		
		
		return mp.map(updatedCategory, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category cat = cr.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "categoryId", categoryId));
		cr.delete(cat);

	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		Category cat = cr.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "categoryId", categoryId));
		
		return mp.map(cat, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> all = cr.findAll();
		List<CategoryDto> categoryDto=new ArrayList<>();
		all.forEach((category)->{
			categoryDto.add(mp.map(category, CategoryDto.class));
		});
		return categoryDto;
	}
	

}
