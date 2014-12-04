
package com.wikifish.entity;

import com.wikifish.enums.AquariumLighting;
import com.wikifish.enums.AquariumSetUp;
import com.wikifish.enums.FeedingHabit;
import com.wikifish.enums.Reproduction;
import com.wikifish.enums.SwimmingHabits;
import com.wikifish.enums.Temperament;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class Fish implements Serializable {
    /**
     * 
     */
    public final static String KEY = "fish";
    private static final long serialVersionUID = 1L;
    private Integer id = 0;
    private String usualName;
    private String scientificName;
    private String urlPicture;

    private Double ph;
    private Integer dh;
    private Double temperature;
    private Double maximumLength;
    private Double aquarium_liters;

    private AquariumLighting aquariumLight;
    private AquariumSetUp aquariumSetUp;
    private FeedingHabit feedingHabit;
    private Reproduction reproduction;
    private SwimmingHabits swimmingHabits;
    private Temperament temperament;
    private ArrayList<Comment> comments;
    private Object region;

    public Fish() {

    }

    public Fish(final JSONObject json) {

        initValues(json);
    }

    private void initValues(final JSONObject json) {

        id = (Integer) getObjectByTag("id", json, 0L);
        usualName = (String) getObjectByTag("usualName", json, "");
        urlPicture = (String) getObjectByTag("urlPicture", json, "");
        scientificName = (String) getObjectByTag("cientificName", json, "");
        ph = (Double) getObjectByTag("ph", json, 0.0);
        dh = (Integer) getObjectByTag("dh", json, 0);
        temperature = (Double) getObjectByTag("temperature", json, 0);
        maximumLength = (Double) getObjectByTag("maximumLength", json, 0);
        aquarium_liters = (Double) getObjectByTag("aquarium_liters", json, 0);
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

    public Double getPh() {
        return ph;
    }

    public void setPh(final Double ph) {
        this.ph = ph;
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getUsualName() {
        return usualName;
    }

    public void setUsualName(final String usualName) {
        this.usualName = usualName;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(final String scientificName) {
        this.scientificName = scientificName;
    }

    public Integer getDh() {
        return dh;
    }

    public void setDh(final Integer dh) {
        this.dh = dh;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(final Double temperature) {
        this.temperature = temperature;
    }

    public Double getMaximumLength() {
        return maximumLength;
    }

    public void setMaximumLength(final Double maximumLength) {
        this.maximumLength = maximumLength;
    }

    public Double getAquarium_liters() {
        return aquarium_liters;
    }

    public void setAquarium_liters(final Double aquarium_liters) {
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
        if (comments == null) {
            comments = new ArrayList<Comment>();
        }
        return comments;
    }

    public void setComments(final ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public Object getRegion() {
        return region;
    }

    public void setRegion(final Object region) {
        this.region = region;
    }

    public String getUrlPicture() {
        return urlPicture;
    }

    public void setUrlPicture(final String urlPicture) {
        this.urlPicture = urlPicture;
    }
}
