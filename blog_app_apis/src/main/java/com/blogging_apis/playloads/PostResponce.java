package com.blogging_apis.playloads;

import java.util.List;

public class PostResponce {
	
	private List<PostDto> content;
	
	private Integer pageNumber;
	
	private Integer pageSize;

	private long totalElements;
	
	private long totalPages;
	
	private boolean lastPage;

	public PostResponce() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PostResponce(List<PostDto> content, Integer pageNumber, Integer pageSize, Integer totalElements,
			Integer totalPages, boolean lastPage) {
		super();
		this.content = content;
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.totalElements = totalElements;
		this.totalPages = totalPages;
		this.lastPage = lastPage;
	}

	@Override
	public String toString() {
		return "PostResponce [content=" + content + ", pageNumber=" + pageNumber + ", pageSize=" + pageSize
				+ ", totalElements=" + totalElements + ", totalPages=" + totalPages + ", lastPage=" + lastPage + "]";
	}

	public List<PostDto> getContent() {
		return content;
	}

	public void setContent(List<PostDto> content) {
		this.content = content;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(long l) {
		this.totalElements = l;
	}

	public long getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(long totalPages) {
		this.totalPages = totalPages;
	}

	public boolean isLastPage() {
		return lastPage;
	}

	public void setLastPage(boolean lastPage) {
		this.lastPage = lastPage;
	}
}
