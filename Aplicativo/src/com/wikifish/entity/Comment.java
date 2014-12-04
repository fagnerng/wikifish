
package com.wikifish.entity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Comment implements Comparable<Comment>, ToJson {
    private String owner;
    private String comment;
    private final int id;
    private final ArrayList<Integer> likes = new ArrayList<Integer>();

    public Comment(final JSONObject json) {
        owner = (String) getObjectByTag("owner", json, "");
        comment = (String) getObjectByTag("comment", json, "");
        id = (Integer) getObjectByTag("id", json, -1);
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

        return likes.size();
    }

    public void like(final Integer id) {
        if (!isLiked(id)) {
            likes.add(id);
        }
    }

    public void dislike(final Integer id) {
        if (isLiked(id)) {
            likes.remove(id);
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
