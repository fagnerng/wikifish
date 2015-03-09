package com.wikifish.entity;

import com.google.gson.annotations.SerializedName;

public class Comment implements Comparable<Comment>{
	@SerializedName("id")
	public Integer id;
	@SerializedName("comment")
	public String comment;
	@SerializedName("owner")
	public User owner;
	
	@Override
	public int compareTo(Comment another) {
		// TODO Auto-generated method stub
		return 0;
	}
}
