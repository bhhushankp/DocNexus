package com.blogging_apis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogging_apis.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
