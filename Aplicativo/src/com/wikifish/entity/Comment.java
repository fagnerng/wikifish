
package com.wikifish.entity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class Comment implements Comparable<Comment>, ToJson, Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String owner;
    private String comment;
    private final int id;
    private final ArrayList<Integer> likes = new ArrayList<Integer>();
    // TODO
    private int commentLikes;

    public Comment(final JSONObject json) {
        try {
            owner = (String) getObjectByTag("email", json.getJSONObject("owner"), "");
        } catch (final JSONException e) {
            owner = "";
            e.printStackTrace();
        }
        comment = (String) getObjectByTag("comment", json, "");
        id = (Integer) getObjectByTag("id", json, -1);
        commentLikes = (Integer) getObjectByTag("commentLikes", json, 0);
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

    public int getId() {
        return id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(final String owner) {
        this.owner = owner;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(final String comment) {
        this.comment = comment;
    }

    public int getNumberOfLike() {

        return commentLikes;
    }

    public void like(final Integer id) {
        if (!isLiked(id)) {
            likes.add(id);
            commentLikes++;
        }
    }

    public void dislike(final Integer id) {
        if (isLiked(id)) {
            likes.remove(id);
            commentLikes--;
        }
    }

    @Override
    public int compareTo(final Comment another) {

        return another.getNumberOfLike() - getNumberOfLike();
    }

    public Boolean isLiked(final Integer id) {
        return likes.contains(id);
    }

    @Override
    public JSONObject toJson() {
        final JSONObject json = new JSONObject();
        try {
            json.put("comment", comment);
            json.put("id", id);
            json.put("likes", likes.toArray());

        } catch (final JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return json;
    }

}
