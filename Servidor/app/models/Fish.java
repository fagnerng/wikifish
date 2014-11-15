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

    private String usualName;
    private String cientificName;
    private float ph;
    private float dh;
    private float temperature;
    private float maximumLength;
    private float aquarium_liters;
    private boolean dry_package_food;
    private boolean live_worms;
    private boolean live_fish;
    private boolean vegetarian;
    private boolean peaceful;
    private boolean agressive;
    private boolean eggylayer;
    private boolean livebearer;
    private boolean mothbrooder;
    private boolean bottom;
    private boolean top;
    private boolean middle;
    private boolean no_special;
    private boolean densidly_plated;
    private boolean only_rocks;
    private boolean only_substract;
    private boolean complete_set;
    private boolean bright;
    private boolean medium;
    private boolean dark;

    @OneToMany(cascade = ALL, fetch = LAZY)
    private List<Comment> comments;

    @OneToOne(cascade = ALL)
    private Region region;

    public float getAquarium_liters() {
        return aquarium_liters;
    }

    public void setAquarium_liters(float aquarium_liters) {
        this.aquarium_liters = aquarium_liters;
    }

    public boolean isDry_package_food() {
        return dry_package_food;
    }

    public void setDry_package_food(boolean dry_package_food) {
        this.dry_package_food = dry_package_food;
    }

    public boolean isLive_worms() {
        return live_worms;
    }

    public void setLive_worms(boolean live_worms) {
        this.live_worms = live_worms;
    }

    public boolean isLive_fish() {
        return live_fish;
    }

    public void setLive_fish(boolean live_fish) {
        this.live_fish = live_fish;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public boolean isPeaceful() {
        return peaceful;
    }

    public void setPeaceful(boolean peaceful) {
        this.peaceful = peaceful;
    }

    public boolean isAgressive() {
        return agressive;
    }

    public void setAgressive(boolean agressive) {
        this.agressive = agressive;
    }

    public boolean isEggylayer() {
        return eggylayer;
    }

    public void setEggylayer(boolean eggylayer) {
        this.eggylayer = eggylayer;
    }

    public boolean isLivebearer() {
        return livebearer;
    }

    public void setLivebearer(boolean livebearer) {
        this.livebearer = livebearer;
    }

    public boolean isMothbrooder() {
        return mothbrooder;
    }

    public void setMothbrooder(boolean mothbrooder) {
        this.mothbrooder = mothbrooder;
    }

    public boolean isBottom() {
        return bottom;
    }

    public void setBottom(boolean bottom) {
        this.bottom = bottom;
    }

    public boolean isTop() {
        return top;
    }

    public void setTop(boolean top) {
        this.top = top;
    }

    public boolean isMiddle() {
        return middle;
    }

    public void setMiddle(boolean middle) {
        this.middle = middle;
    }

    public boolean isNo_special() {
        return no_special;
    }

    public void setNo_special(boolean no_special) {
        this.no_special = no_special;
    }

    public boolean isDensidly_plated() {
        return densidly_plated;
    }

    public void setDensidly_plated(boolean densidly_plated) {
        this.densidly_plated = densidly_plated;
    }

    public boolean isOnly_rocks() {
        return only_rocks;
    }

    public void setOnly_rocks(boolean only_rocks) {
        this.only_rocks = only_rocks;
    }

    public boolean isOnly_substract() {
        return only_substract;
    }

    public void setOnly_substract(boolean only_substract) {
        this.only_substract = only_substract;
    }

    public boolean isComplete_set() {
        return complete_set;
    }

    public void setComplete_set(boolean complete_set) {
        this.complete_set = complete_set;
    }

    public boolean isBright() {
        return bright;
    }

    public void setBright(boolean bright) {
        this.bright = bright;
    }

    public boolean isMedium() {
        return medium;
    }

    public void setMedium(boolean medium) {
        this.medium = medium;
    }

    public boolean isDark() {
        return dark;
    }

    public void setDark(boolean dark) {
        this.dark = dark;
    }

    public Fish() {
        comments = new ArrayList<>();
    }

    public static final Finder<Long, Fish> FINDER = new Finder(Long.class, Fish.class);

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsualName() {
        return usualName;
    }

    public void setUsualName(String usualName) {
        this.usualName = usualName;
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
