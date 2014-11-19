package com.wikifish.entity;

public class Comment {
	private String owner;
	private String comment;
	private int id;

	public Comment(int id, String owner, String comment) {
		super();
		this.id = id;
		this.owner = owner;
		this.comment = comment;
	}

	public int getId() {
		return id;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
