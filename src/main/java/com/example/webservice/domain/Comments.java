package com.example.webservice.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Comments extends BaseTimeEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne(targetEntity=Posts.class)
	@JoinColumn(name="posts_id",nullable=false)
	private Posts posts;
	
	@Column(length=10,nullable=false)
	private String author;
	
	@Column(length=10,nullable=false)
	private String pwd;
	
	@Column(columnDefinition="TEXT",nullable=false)
	private String content;
	
	@Builder
	public Comments(String author,String pwd,String content,Posts posts) {
		this.author=author;
		this.pwd=pwd;
		this.content=content;
		this.posts=posts;
	}
	public Comments(Long id,String author,String pwd,String content,Posts posts) {
		this.author=author;
		this.pwd=pwd;
		this.content=content;
		this.posts=posts;
	}
}
