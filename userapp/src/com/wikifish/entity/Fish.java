package com.wikifish.entity;

import java.io.Serializable;
import java.util.ArrayList;
import com.wikifish.enums.*;

import com.google.gson.annotations.SerializedName;

public class Fish implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static String KEY = "fish";
	@SerializedName("_id")
	public String id;

	@SerializedName("usual_name")
	public String usualName;

	@SerializedName("cientific_name")
	public String cientificName;

	@SerializedName("ph")
	public double ph;

	@SerializedName("dh")
	public double dh;

	@SerializedName("temperature")
	public double temperature;
	
	@SerializedName("maximum_length")
	public double maximumLength;
	
	@SerializedName("aquarium_liters")
	public double aquarium_liters;
	
	@SerializedName("alimentation")
	public String alimentation;
	
	@SerializedName("reproduction")
	public String reproduction;
	
	@SerializedName("aquariumLight")
	public String aquariumLight;
	
	@SerializedName("aquarium_setup")
	public String aquariumSetUp;
	
	@SerializedName("temperament")
	public String temperament;
	
	@SerializedName("swimming")
	public String swimming;
	
	@SerializedName("urlPicture")
	public String urlPicture;
	
	@SerializedName("comments")
	public ArrayList<Comment> comments;

	public void addComment(Comment comment){
		comments.add(comment);
	}
	
	public FeedingHabit getAlimentation() {
		if(alimentation.equalsIgnoreCase("FISH"))return FeedingHabit.LiveFish;
		if(alimentation.equalsIgnoreCase("LIVE_WORMS"))return FeedingHabit.LiveWorms;
		if(alimentation.equalsIgnoreCase("PLANTS"))return FeedingHabit.Vegetarian;
		return FeedingHabit.DryPackaged;
	}

	public Reproduction getReproduction() {
		if( reproduction.equalsIgnoreCase("MOUTHBROODER"))return Reproduction.Mounthbrooder;
		if( reproduction.equalsIgnoreCase("LIVEBEARER"))return Reproduction.Livebearer;
		return Reproduction.Egglayer;
	}

	public AquariumLighting getAquariumLight() {
		if( aquariumLight.equalsIgnoreCase("BRIGHT"))return AquariumLighting.BrightWithSunlight;
		if( aquariumLight.equalsIgnoreCase("DARK"))return AquariumLighting.Dark;
		return AquariumLighting.BrightNoSunlight;
	}

	public AquariumSetUp getAquariumSetUp() {
		if( aquariumSetUp.equalsIgnoreCase("ONLY_SUBSTRACT"))return AquariumSetUp.OnlyGravel;
		if( aquariumSetUp.equalsIgnoreCase("ONLY_PLANTS"))return AquariumSetUp.DenselyPlanted;
		if( aquariumSetUp.equalsIgnoreCase("ONLY_ROCKS"))return AquariumSetUp.RocksNoPlants;
		return AquariumSetUp.RockAndPlants;
	}

	public SwimmingHabits getSwimming() {
		if( swimming.equalsIgnoreCase("TOP"))return SwimmingHabits.Top;
		if( swimming.equalsIgnoreCase("MIDDLE"))return SwimmingHabits.Middle;
		if( swimming.equalsIgnoreCase("BOTTOM"))return SwimmingHabits.Bottom;
		return SwimmingHabits.NoSpecial;
	}
	
	public Temperament getTemperament(){
		if( temperament.equalsIgnoreCase("DANGER"))return Temperament.Peaceful;
		return Temperament.Peaceful;
	}

}
