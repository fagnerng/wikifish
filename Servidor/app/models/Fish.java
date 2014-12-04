package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

@Entity
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
        DENSILY_PLANTED, ONLY_ROCKS, ONLY_SUBSTRACT, COMPLETED_SET
    }

    private enum SwimmingEnum {
        BOTTOM, TOP, MIDDLE, NO_SPECIAL
    }

    private enum AlimentationEnum {
        DRY_PACKAGE_FOOD, LIVE_WORMS, LIVE_FISH, VEGETARIAN
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
    private AlimentationEnum alimentation;
    private ReproductionEnum reproduction;
    private AquariumLightEnum aquariumLight;
    private TemperamentEnum temperament;
    private AquariumSetUpEnum aquariumSetUp;
    private SwimmingEnum swimming;
    private String urlPicture = "";

    @OneToMany(cascade = ALL, fetch = LAZY)
    private List<Comment> comments;

    @OneToOne(cascade = ALL)
    private Region region;


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

    public String getCientificName() {
        return cientificName;
    }

    public void setCientificName(String cientificName) {
        this.cientificName = cientificName;
    }

    public float getPh() {
        return ph;
    }

    public void setPh(float ph) {
        this.ph = ph;
    }

    public int getDh() {
        return dh;
    }

    public void setDh(int dh) {
        this.dh = dh;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getMaximumLength() {
        return maximumLength;
    }

    public void setMaximumLength(float maximumLength) {
        this.maximumLength = maximumLength;
    }

    public float getAquarium_liters() {
        return aquarium_liters;
    }

    public void setAquarium_liters(float aquarium_liters) {
        this.aquarium_liters = aquarium_liters;
    }

    public ReproductionEnum getReproduction() {
        return reproduction;
    }

    public void setReproduction(ReproductionEnum reproduction) {
        this.reproduction = reproduction;
    }

    public AquariumLightEnum getAquariumLight() {
        return aquariumLight;
    }

    public void setAquariumLight(AquariumLightEnum aquariumLight) {
        this.aquariumLight = aquariumLight;
    }

    public TemperamentEnum getTemperament() {
        return temperament;
    }

    public void setTemperament(TemperamentEnum temperament) {
        this.temperament = temperament;
    }

    public AquariumSetUpEnum getAquariumSetUp() {
        return aquariumSetUp;
    }

    public void setAquariumSetUp(AquariumSetUpEnum aquariumSetUp) {
        this.aquariumSetUp = aquariumSetUp;
    }

    public SwimmingEnum getSwimming() {
        return swimming;
    }

    public void setSwimming(SwimmingEnum swimming) {
        this.swimming = swimming;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void addComment(Comment comment){
        this.comments.add(comment);
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public AlimentationEnum getAlimentation() {
        return alimentation;
    }

    public void setAlimentation(AlimentationEnum alimentation) {
        this.alimentation = alimentation;
    }

    public String getUrlPicture() {
        return urlPicture;
    }

    public void setUrlPicture(String urlPicture) {
        this.urlPicture = urlPicture;
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
        this.reproduction = ReproductionEnum.EGGYLAYER;
        this.aquariumLight = AquariumLightEnum.MEDIUM;
        this.temperament = TemperamentEnum.PEACEFUL;
        this.aquariumSetUp = AquariumSetUpEnum.COMPLETED_SET;
        this.swimming = SwimmingEnum.NO_SPECIAL;
        comments = new ArrayList<>();
    }
}
