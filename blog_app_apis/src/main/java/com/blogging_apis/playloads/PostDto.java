package com.blogging_apis.playloads;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class PostDto {
	
	private Integer postId;

	
	@NotEmpty
	@Size(min = 4, message = "minimum siz of 4 charecters")
	private String title;

	@NotEmpty
	@Size(min = 4, message = "minimum siz of 4 charecters")
	private String content;

	private String imageName;

	private Date addedDate;

	private CategoryDto category;

	private UserDto user;
	
	private List<CommentsDto> comment=new ArrayList<>();
	
	
	public PostDto(Integer postId, @NotEmpty @Size(min = 4, message = "minimum siz of 4 charecters") String title,
			@NotEmpty @Size(min = 4, message = "minimum siz of 4 charecters") String content, String imageName,
			Date addedDate, CategoryDto category, UserDto user, List<CommentsDto> comment) {
		super();
		this.postId = postId;
		this.title = title;
		this.content = content;
		this.imageName = imageName;
		this.addedDate = addedDate;
		this.category = category;
		this.user = user;
		this.comment = comment;
	}

	public List<CommentsDto> getComment() {
		return comment;
	}

	public void setComment(List<CommentsDto> comment) {
		this.comment = comment;
	}

	public PostDto(String title, String content, String imageName, Date addedDate, CategoryDto category, UserDto user) {
		super();
		this.title = title;
		this.content = content;
		this.imageName = imageName;
		this.addedDate = addedDate;
		this.category = category;
		this.user = user;
	}
	
	public PostDto(Integer postId, @NotEmpty @Size(min = 4, message = "minimum siz of 4 charecters") String title,
			@NotEmpty @Size(min = 4, message = "minimum siz of 4 charecters") String content, String imageName,
			Date addedDate, CategoryDto category, UserDto user) {
		super();
		this.postId = postId;
		this.title = title;
		this.content = content;
		this.imageName = imageName;
		this.addedDate = addedDate;
		this.category = category;
		this.user = user;
	}

	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Date getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}

	public CategoryDto getCategory() {
		return category;
	}

	public void setCategory(CategoryDto category) {
		this.category = category;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public PostDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "PostDto [postId=" + postId + ", title=" + title + ", content=" + content + ", imageName=" + imageName
				+ ", addedDate=" + addedDate + ", category=" + category + ", user=" + user + ", comment=" + comment
				+ "]";
	}

}
