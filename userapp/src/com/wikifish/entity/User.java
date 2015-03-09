package com.wikifish.entity;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class User implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SerializedName("email")
	public String email;
	@SerializedName("password")
	public String password;
}
