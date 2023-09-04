package com.blogging_apis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogging_apis.playloads.ApiResponce;
import com.blogging_apis.playloads.CommentsDto;
import com.blogging_apis.service.CommentsService;

@RestController
@RequestMapping("/api/")
public class CommentsController {
	
	@Autowired
	private CommentsService cs;
	
	@PostMapping("/post/{postId}/comments")
	public ResponseEntity<CommentsDto> createComments(@RequestBody CommentsDto commentsDto,
			@PathVariable Integer postId)
	{
		CommentsDto createComment = cs.createComment(commentsDto, postId);
		return new ResponseEntity<CommentsDto>(createComment,HttpStatus.OK);
	}
	
	@DeleteMapping("/comments/{commentsId}")
	public ResponseEntity<ApiResponce> createComments(@PathVariable Integer commentsId)
	{
		cs.deleteComment(commentsId);
		return new ResponseEntity<ApiResponce>(new ApiResponce("Comment Deleted Succesfully",true),HttpStatus.OK);
	}
	

}
