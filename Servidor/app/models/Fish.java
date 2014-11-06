package models;

import com.fasterxml.jackson.annotation.JsonInclude;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

@Entity
@JsonInclude(NON_EMPTY)
public class Fish extends Model {

    @Id
    private long id;

    private String name;

    private String cientificName;

    private float ph;
    private float dh;
    private float temperature;
    private float maximumLength;

    @OneToMany(cascade = ALL, fetch = LAZY)
    private List<Comment> comments;

    @OneToOne
    private Region region;

    public Fish() {
        comments = new ArrayList<>();
    }

    public static final Finder<Long, Fish> FINDER = new Finder(Long.class, Fish.class);

    public float getAquariumLiters() {
        return aquariumLiters;
    }

    public void setAquariumLiters(float aquariumLiters) {
        this.aquariumLiters = aquariumLiters;
    }

    public float getMaximumLength() {
        return maximumLength;
    }

    public void setMaximumLength(float maximumLength) {
        this.maximumLength = maximumLength;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getDh() {
        return dh;
    }

    public void setDh(float dh) {
        this.dh = dh;
    }

    public float getPh() {
        return ph;
    }

    public void setPh(float ph) {
        this.ph = ph;
    }

    public String getCientificName() {
        return cientificName;
    }

    public void setCientificName(String cientificName) {
        this.cientificName = cientificName;
    }

    private float aquariumLiters;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }
}
