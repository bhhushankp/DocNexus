package com.blogging_apis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogging_apis.entities.Comments;

public interface CommentsRepository extends JpaRepository<Comments, Integer>{

}
