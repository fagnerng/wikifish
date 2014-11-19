package com.wikifish.entity;

public class Aquarium {
	private Boolean airBomb;
	private Boolean grass;
	private Boolean rocks;
	private Boolean light;
	
	
	public Aquarium(){
		
	}
	
	public Aquarium(Boolean airBomb, Boolean grass, Boolean rocks,
			Boolean light, int numberOfFish, int capacity) {
		
		this.airBomb = airBomb;
		this.grass = grass;
		this.rocks = rocks;
		this.light = light;
		this.numberOfFish = numberOfFish;
		Capacity = capacity;
	}

	public Boolean getAirBomb() {
		return airBomb;
	}
	public void setAirBomb(Boolean airBomb) {
		this.airBomb = airBomb;
	}
	public Boolean getGrass() {
		return grass;
	}
	public void setGrass(Boolean grass) {
		this.grass = grass;
	}
	public Boolean getRocks() {
		return rocks;
	}
	public void setRocks(Boolean rocks) {
		this.rocks = rocks;
	}
	public Boolean getLight() {
		return light;
	}
	public void setLight(Boolean light) {
		this.light = light;
	}
	public int getNumberOfFish() {
		return numberOfFish;
	}
	public void setNumberOfFish(int numberOfFish) {
		this.numberOfFish = numberOfFish;
	}
	public int getCapacity() {
		return Capacity;
	}
	public void setCapacity(int capacity) {
		Capacity = capacity;
	}
	private int numberOfFish;
	private int Capacity;
	
}
