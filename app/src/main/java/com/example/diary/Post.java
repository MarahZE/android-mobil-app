package com.example.diary;

public class Post {

    private String time;
    private String title;
    private String post;
    private String category;

    public Post() {

    }

    public String getTime() {
        return time;
    }

    public String getTitle() {
        return title;
    }

    public String getPost() {
        return post;
    }

    public String getCategory() {
        return category;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
