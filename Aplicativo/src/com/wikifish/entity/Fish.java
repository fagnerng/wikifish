
package com.wikifish.entity;

import com.wikifish.enums.AquariumLighting;
import com.wikifish.enums.AquariumSetUp;
import com.wikifish.enums.FeedingHabit;
import com.wikifish.enums.Reproduction;
import com.wikifish.enums.SwimmingHabits;
import com.wikifish.enums.Temperament;

import org.json.JSONObject;

import java.util.ArrayList;

public class Fish {
    private Long id = 0L;
    private String usualName = "";
    private String cientificName;

    private Float ph;
    private Float dh;
    private Float temperature;
    private Float maximumLength;
    private Float aquarium_liters;

    private AquariumLighting aquariumLight;
    private AquariumSetUp aquariumSetUp;
    private FeedingHabit feedingHabit;
    private Reproduction reproduction;
    private SwimmingHabits swimmingHabits;
    private Temperament temperament;
    private ArrayList<Comment> comments;
    private Object Region;

    public Fish() {

    }

    public Fish(final JSONObject json) {

        initValues(json);
    }

    private void initValues(final JSONObject json) {

        id = (Long) getObjectByTag("id", json, 0L);
        usualName = (String) getObjectByTag("usualName", json, "");
        cientificName = (String) getObjectByTag("cientificName", json, "");
        setPh((Float) getObjectByTag("ph", json, 0));
        dh = (Float) getObjectByTag("dh", json, 0);
        temperature = (Float) getObjectByTag("temperature", json, 0);
        maximumLength = (Float) getObjectByTag("maximumLength", json, 0);
        aquarium_liters = (Float) getObjectByTag("aquarium_liters", json, 0);
        initEnums(json);
    }

    private void initEnums(final JSONObject json) {
        aquariumLight = (AquariumLighting) getEnumByTag("aquariumLight", json,
                AquariumLighting.BrightNoSunlight);
        aquariumSetUp = (AquariumSetUp) getEnumByTag("aquariumSetUp", json,
                AquariumSetUp.DenselyPlanted);
        feedingHabit = (FeedingHabit) getEnumByTag("feedingHabit", json, FeedingHabit.DryPackaged);
        reproduction = (Reproduction) getEnumByTag("reproduction", json, Reproduction.Egglayer);
        swimmingHabits = (SwimmingHabits) getEnumByTag("swimmingHabits", json,
                SwimmingHabits.Bottom);
        temperament = (Temperament) getEnumByTag("temperament", json, Temperament.NotForBegginers);

    }

    private Object getObjectByTag(final String tag, final JSONObject json, final Object defaultValue) {
        Object value = null;
        try {
            value = json.get(tag);
        } catch (final Exception e) {
            e.getMessage();
        }

        return value != null ? value : defaultValue;
    }

    private Object getEnumByTag(final String tag, final JSONObject json, final Object defaultValue) {
        return null;
    }

    public Float getPh() {
        return ph;
    }

    public void setPh(final Float ph) {
        this.ph = ph;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getUsualName() {
        return usualName;
    }

    public void setUsualName(final String usualName) {
        this.usualName = usualName;
    }

    public String getCientificName() {
        return cientificName;
    }

    public void setCientificName(final String cientificName) {
        this.cientificName = cientificName;
    }

    public Float getDh() {
        return dh;
    }

    public void setDh(final Float dh) {
        this.dh = dh;
    }

    public Float getTemperature() {
        return temperature;
    }

    public void setTemperature(final Float temperature) {
        this.temperature = temperature;
    }

    public Float getMaximumLength() {
        return maximumLength;
    }

    public void setMaximumLength(final Float maximumLength) {
        this.maximumLength = maximumLength;
    }

    public Float getAquarium_liters() {
        return aquarium_liters;
    }

    public void setAquarium_liters(final Float aquarium_liters) {
        this.aquarium_liters = aquarium_liters;
    }

    public AquariumLighting getAquariumLight() {
        return aquariumLight;
    }

    public void setAquariumLight(final AquariumLighting aquariumLight) {
        this.aquariumLight = aquariumLight;
    }

    public AquariumSetUp getAquariumSetUp() {
        return aquariumSetUp;
    }

    public void setAquariumSetUp(final AquariumSetUp aquariumSetUp) {
        this.aquariumSetUp = aquariumSetUp;
    }

    public FeedingHabit getFeedingHabit() {
        return feedingHabit;
    }

    public void setFeedingHabit(final FeedingHabit feedingHabit) {
        this.feedingHabit = feedingHabit;
    }

    public Reproduction getReproduction() {
        return reproduction;
    }

    public void setReproduction(final Reproduction reproduction) {
        this.reproduction = reproduction;
    }

    public SwimmingHabits getSwimmingHabits() {
        return swimmingHabits;
    }

    public void setSwimmingHabits(final SwimmingHabits swimmingHabits) {
        this.swimmingHabits = swimmingHabits;
    }

    public Temperament getTemperament() {
        return temperament;
    }

    public void setTemperament(final Temperament temperament) {
        this.temperament = temperament;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(final ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public Object getRegion() {
        return Region;
    }

    public void setRegion(final Object region) {
        Region = region;
    }
}
