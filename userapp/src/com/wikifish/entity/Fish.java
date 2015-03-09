package com.wikifish.entity;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class Fish implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static String KEY = "fish";
	@SerializedName("id")
	public Integer id;

	@SerializedName("usualName")
	public String usualName;

	@SerializedName("cientificName")
	public String cientificName;

	@SerializedName("ph")
	public double ph;

	@SerializedName("dh")
	public double dh;

	@SerializedName("temperature")
	public double temperature;
	
	@SerializedName("maximumLength")
	public double maximumLength;
	
	@SerializedName("aquarium_liters")
	public double aquarium_liters;
	
	@SerializedName("alimentation")
	public String alimentation;
	
	@SerializedName("reproduction")
	public String reproduction;
	
	@SerializedName("aquariumLight")
	public String aquariumLight;
	
	@SerializedName("aquariumSetUp")
	public String aquariumSetUp;
	
	@SerializedName("swimming")
	public String swimming;
	
	@SerializedName("urlPicture")
	public String urlPicture;
	
	@SerializedName("comments")
	public ArrayList<Comment> comments;

}
