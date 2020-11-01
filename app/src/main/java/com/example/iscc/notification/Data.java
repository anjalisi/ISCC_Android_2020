package com.example.iscc.notification;

public class Data {
    public Data(){}
    private String user, body, title, sender;
    private int icon;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public Data(String user, String body, String title, String sender, int icon) {
        this.user = user;
        this.body = body;
        this.title = title;
        this.sender = sender;
        this.icon = icon;
    }
}
