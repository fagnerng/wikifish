package com.wikifish.entity;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class Comment implements Comparable<Comment>, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SerializedName("id")
	public Integer id;
	@SerializedName("comment")
	public String comment;
	@SerializedName("owner")
	public User owner;
	@SerializedName("commentLikes")
	public Integer commentLikes;
	
	public void like(){
		//TODO fazer o post aqui
		commentLikes++;
	}
	
	@Override
	public int compareTo(Comment another) {
		// TODO Auto-generated method stub
		return id.compareTo(another.id);
	}
}
