package com.blogging_apis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogging_apis.entities.Category;
import com.blogging_apis.entities.Post;
import com.blogging_apis.entities.User;

public interface PostRepository  extends JpaRepository<Post, Integer>{
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);

	List<Post> findByTitleContaining(String title);
}
