package com.blogging_apis.service;

import com.blogging_apis.playloads.CommentsDto;

public interface CommentsService {
	
	CommentsDto createComment(CommentsDto commentsDto,Integer postId);
	
	void deleteComment(Integer commentId);

}
