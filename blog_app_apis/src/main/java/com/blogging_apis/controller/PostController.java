package com.blogging_apis.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blogging_apis.config.AppConstants;
import com.blogging_apis.playloads.ApiResponce;
import com.blogging_apis.playloads.PostDto;
import com.blogging_apis.playloads.PostResponce;
import com.blogging_apis.service.FileService;
import com.blogging_apis.service.PostService;

@RestController
@RequestMapping("/api")
public class PostController {
	
	@Autowired
	private PostService ps;
	
	@Autowired
	private FileService fs;
	
	@Value("${project.images}")
	private String path;
	
	
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(
			@RequestBody PostDto postDto,
			@PathVariable Integer userId,
			@PathVariable Integer categoryId
			)
	{
		PostDto createPost = ps.createPost(postDto, userId, categoryId);
		
		return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
	}
	
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostByUSer(@PathVariable Integer userId)
	{
		List<PostDto> posts = ps.getPostByUser(userId);
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
		
	}
	
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId)
	{
		List<PostDto> posts = ps.getPostByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
		
	}
	
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPost(@PathVariable Integer postId)
	{
		PostDto post = ps.getPost(postId);
		return new ResponseEntity<PostDto>(post,HttpStatus.OK);
	}
	
	@GetMapping("/posts")
	public ResponseEntity<PostResponce> getAllPost(
			@RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
			@RequestParam(value="pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize,
			@RequestParam(value="sortBy",defaultValue = AppConstants.SORT_BY,required = false) String sortBy,
			@RequestParam(value="sortDir",defaultValue = AppConstants.SORT_DIR,required = false) String sortDir
			)
	{
		PostResponce allPost = ps.getAllPost(pageNumber,pageSize,sortBy,sortDir);
		
		return new ResponseEntity<PostResponce>(allPost,HttpStatus.OK);
	}
	
	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<ApiResponce> deletePost(@PathVariable Integer postId)	
	{
		ps.deletePost(postId);
		return new ResponseEntity<ApiResponce>(new ApiResponce("Post Deleted Succesfully !!",true),HttpStatus.OK);
	}

	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable Integer postId)
	{
		PostDto updatePost = ps.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK); 
	}
	
	@GetMapping("/posts/search/{keyword}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(
			@PathVariable("keyword") String keyword) {
		List<PostDto> posts = ps.searchPosts(keyword);
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
		
	}
	
	//post image upload
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadPostImage(
			@RequestParam("image") MultipartFile image,
			@PathVariable Integer postId
			) throws IOException
	{
		PostDto postDto = ps.getPost(postId);
		String fileName = fs.uploadImage(path, image);
		postDto.setImageName(fileName);
		PostDto updatePost = ps.updatePost(postDto, postId);
		
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
		
		
		
		
	}
}
