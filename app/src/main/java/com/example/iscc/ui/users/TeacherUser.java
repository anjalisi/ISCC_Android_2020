package com.example.iscc.ui.users;

public class TeacherUser {
    private String id;
    private String username;

    public String getUseris() {
        return useris;
    }

    public void setUseris(String useris) {
        this.useris = useris;
    }

    private String imageURL;

    public TeacherUser(String id, String username, String imageURL, String useris) {
        this.id = id;
        this.username = username;
        this.imageURL = imageURL;
        this.useris = useris;
    }

    private String useris;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
    public TeacherUser(){}
}
