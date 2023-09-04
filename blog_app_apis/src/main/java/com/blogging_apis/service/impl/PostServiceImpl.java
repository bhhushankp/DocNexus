package com.blogging_apis.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blogging_apis.entities.Category;
import com.blogging_apis.entities.Post;
import com.blogging_apis.entities.User;
import com.blogging_apis.exceptions.ResourceNotFoundException;
import com.blogging_apis.playloads.PostDto;
import com.blogging_apis.playloads.PostResponce;
import com.blogging_apis.repository.CategoryRepository;
import com.blogging_apis.repository.PostRepository;
import com.blogging_apis.repository.UserRepository;
import com.blogging_apis.service.PostService;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepository pr;
	
	@Autowired
	private UserRepository ur;
	
	@Autowired
	private CategoryRepository cr;
	
	@Autowired
	private ModelMapper mp;
	

	@Override
	public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
		User user = ur.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "Id", userId));
		
		Category category = cr.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "categoryId", categoryId));
		
		Post post = mp.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setCategory(category);
		post.setUser(user);
		
		Post savePost = pr.save(post);
		
		return mp.map(savePost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		
		Post post = pr.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "postId", postId));
		
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(post.getImageName());
		
		Post save = pr.save(post);
		
		return mp.map(save, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		
		Post post = pr.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "postId", postId));
		pr.delete(post);
		
	}

	@Override
	public PostDto getPost(Integer postId) {
		Post post = pr.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "postId", postId));

		return mp.map(post, PostDto.class);
	}

	@Override
	public PostResponce getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir) {
	
		Sort sort=(sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : (Sort.by(sortBy).descending());
		Pageable p1 = PageRequest.of(pageNumber, pageSize, sort);
		
		Page<Post> page = pr.findAll(p1);
		 
		List<Post> allPost = page.getContent();

		List<PostDto> pdto=new ArrayList<>();
		allPost.forEach((post)->{
			pdto.add(mp.map(post, PostDto.class));
		});
	
		PostResponce p=new PostResponce();
		p.setContent(pdto);
		p.setPageNumber(page.getNumber());
		p.setPageSize(page.getSize());
		p.setTotalElements(page.getTotalElements());
		p.setTotalPages(page.getTotalPages());
		p.setLastPage(page.isLast());
		
		
		
		return p;
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		
		Category cat = cr.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "categoryId", categoryId));
		
		List<Post> allCat = pr.findByCategory(cat);
		List<PostDto> all=new ArrayList<>();
		
		allCat.forEach((category)->{
			all.add(mp.map(category, PostDto.class));
		});
		
		return all;
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		
		User findUser = ur.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "Id", userId));
		
		List<Post> find = pr.findByUser(findUser);
		List<PostDto> all=new ArrayList<>();
		find.forEach((user)->{
			all.add(mp.map(user,PostDto.class));
		});
		return all;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		List<Post> post = pr.findByTitleContaining(keyword);
		
		List<PostDto> posts=new ArrayList<>();
		
		post.forEach((p)->{
			posts.add(mp.map(p, PostDto.class));
		});
		

		return posts;
}

	

}
