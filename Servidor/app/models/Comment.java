package models;

import com.fasterxml.jackson.annotation.JsonInclude;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Entity
@JsonInclude(NON_EMPTY)
public class Comment extends Model {

    @Id
    private long id;

    private String userName;
    private String comment;
    private int commentLikes;

    public static final Finder<Long, Comment> FINDER = new Finder<Long, Comment>(Long.class, Comment.class);

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
}
