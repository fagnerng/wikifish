package com.wikifish.entity;

public class Comment implements Comparable<Comment> {
    private String owner;
    private String comment;
    private int id;
    private int numberOfLike;
    private Boolean liked;

    public Comment(int id, String owner, String comment, int likes,
            Boolean liked) {
        super();
        this.id = id;
        this.owner = owner;
        this.comment = comment;
        this.numberOfLike = likes;
        this.liked = liked;
    }

    public int getId() {
        return id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getNumberOfLike() {
        return numberOfLike;
    }

    public void like() {
        if (!liked) {
            liked = true;
            numberOfLike++;
        }
    }

    public void dislike() {
        if (liked) {
            numberOfLike--;
            liked = false;
        }
    }

    @Override
    public int compareTo(Comment another) {

        return another.getNumberOfLike() - this.getNumberOfLike();
    }

    public Boolean isLiked() {
        return liked;
    }

}
