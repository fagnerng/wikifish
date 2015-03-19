package com.wikifish.entity;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class Comment implements Comparable<Comment>, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SerializedName("_id")
	public String id;
	@SerializedName("text")
	public String comment;
	@SerializedName("parent")
	public String parent;;
	@SerializedName("user")
	public User owner;
	@SerializedName("likes")
	public ArrayList<String> commentLikes;
	
	public void like(){
		//TODO fazer o post aqui
		commentLikes.add("");
	}
	
	@Override
	public int compareTo(Comment another) {
		// TODO Auto-generated method stub
		return id.compareTo(another.id);
	}
}
