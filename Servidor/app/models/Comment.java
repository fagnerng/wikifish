package models;

import com.avaje.ebean.validation.NotNull;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;

@Entity
public class Comment extends Model {

    @Id
    private long id;

    private String comment;
    private int commentLikes;

    @ManyToOne(cascade = ALL, fetch = EAGER, optional = false)
    @NotNull
    private User owner;

    public static final Finder<Long, Comment> FINDER = new Finder(Long.class, Comment.class);

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getCommentLikes() {
        return commentLikes;
    }

    public void setCommentLikes(int commentLikes) {
        this.commentLikes = commentLikes;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
