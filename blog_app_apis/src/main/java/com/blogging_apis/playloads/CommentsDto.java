package com.blogging_apis.playloads;

public class CommentsDto {
	
	private Integer id;
	
	private String content;

	@Override
	public String toString() {
		return "CommentsDto [id=" + id + ", content=" + content + "]";
	}

	public CommentsDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CommentsDto(Integer id, String content) {
		super();
		this.id = id;
		this.content = content;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
