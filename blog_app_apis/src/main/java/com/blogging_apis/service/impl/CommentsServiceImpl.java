package com.blogging_apis.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogging_apis.entities.Comments;
import com.blogging_apis.entities.Post;
import com.blogging_apis.exceptions.ResourceNotFoundException;
import com.blogging_apis.playloads.CommentsDto;
import com.blogging_apis.repository.CommentsRepository;
import com.blogging_apis.repository.PostRepository;
import com.blogging_apis.service.CommentsService;

@Service
public class CommentsServiceImpl implements CommentsService {
	
	@Autowired
	private PostRepository ps;
	
	@Autowired
	private CommentsRepository cr;
	
	@Autowired
	private ModelMapper mp;

	@Override
	public CommentsDto createComment(CommentsDto commentsDto, Integer postId) {
		
		Post post = ps.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","postId",postId));
		
		Comments comments = mp.map(commentsDto, Comments.class);
		comments.setPost(post);
		Comments saveComments= cr.save(comments);
		
		return mp.map(saveComments, CommentsDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {

		Comments comment = cr.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comments", "commentsId", commentId));
		
		cr.delete(comment);
		
	}
	
	

}
