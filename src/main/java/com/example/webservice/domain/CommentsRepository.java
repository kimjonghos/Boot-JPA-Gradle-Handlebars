package com.example.webservice.domain;

import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommentsRepository extends JpaRepository<Comments,Long> {
    Stream<Comments> findByPostsId(Long postsId);
    
    @Query("SELECT c " +
            "FROM Comments c " +
            "ORDER BY c.id DESC")
    Stream<Comments> findAllDesc();
	
}
