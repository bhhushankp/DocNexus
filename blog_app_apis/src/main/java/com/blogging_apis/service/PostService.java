package com.blogging_apis.service;

import java.util.List;

import com.blogging_apis.playloads.PostDto;
import com.blogging_apis.playloads.PostResponce;

public interface PostService {

	
	PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
	
	PostDto updatePost(PostDto postDto,Integer postId);
	
	void deletePost(Integer postId);
	
	PostDto getPost(Integer postId);
	
	PostResponce getAllPost(Integer pageNumber,Integer pageSize,String sortBy, String sortDir);
	
	List<PostDto> getPostByCategory(Integer categoryId);
	
	List<PostDto> getPostByUser(Integer userId);
	
	List<PostDto> searchPosts(String keyword);

	
	
}
