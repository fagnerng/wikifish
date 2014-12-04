package models;

import com.avaje.ebean.validation.NotNull;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserFish extends Model {

    public static final Finder<String, UserFish> FINDER = new Finder(String.class, UserFish.class);

    @Id
    private String email;
    @NotNull
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
