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
//@JsonInclude(NON_EMPTY)
public class Fish extends Model {

    public static final Finder<Long, Fish> FINDER = new Finder(Long.class, Fish.class);
    private enum ReproductionEnum {
        EGGYLAYER, LIVEBEARER, MOUTHBROODER
    }

    private enum AquariumLightEnum {
        BRIGHT, MEDIUM, DARK
    }

    private enum TemperamentEnum {
        PEACEFUL, AGRESSIVE
    }

    private enum AquariumSetUpEnum {
        DENSIDLY_PLANTED, ONLY_ROCKS, ONLY_SUBSTRACT, COMPLETED_SET
    }

    private enum SwimmingEnum {
        BOTTOM, TOP, MIDDLE, NO_SPECIAL
    }


    @Id
    private long id;

    private String usualName;
    private String cientificName;
    private float ph;
    private int dh;
    private float temperature;
    private float maximumLength;
    private float aquarium_liters;
    private boolean dry_package_food;
    private boolean live_worms;
    private boolean live_fish;
    private boolean vegetarian;
    private ReproductionEnum reproduction;
    private AquariumLightEnum aquariumLight;
    private TemperamentEnum temperament;
    private AquariumSetUpEnum aquariumSetUp;
    private SwimmingEnum swimming;

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

    public Fish(String usualName, String cientificName) {
        this.usualName = usualName;
        this.cientificName = cientificName;
        this.ph = 7.0f;
        this.dh = 160;
        this.temperature = 37.0f;
        this.maximumLength = 15.0f;
        this.aquarium_liters = 100.0f;
        this.dry_package_food = true;
        this.live_worms = false;
        this.live_fish = false;
        this.vegetarian = false;
        this.reproduction = ReproductionEnum.EGGYLAYER;
        this.aquariumLight = AquariumLightEnum.MEDIUM;
        this.temperament = TemperamentEnum.PEACEFUL;
        this.aquariumSetUp = AquariumSetUpEnum.COMPLETED_SET;
        this.swimming = SwimmingEnum.NO_SPECIAL;
        comments = new ArrayList<>();
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
