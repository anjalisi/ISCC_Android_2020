package com.example.iscc.ui.todolist;

public class ToDoMain {
    String title, description, dateDone, keydoes;

    public String getKeydoes() {
        return keydoes;
    }

    public void setKeydoes(String keydoes) {
        this.keydoes = keydoes;
    }

    public ToDoMain(){

    }

    public ToDoMain(String title, String description, String dateDone, String keydoes) {
        this.title = title;
        this.description = description;
        this.dateDone = dateDone;
        this.keydoes = keydoes;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateDone() {
        return dateDone;
    }

    public void setDateDone(String dateDone) {
        this.dateDone = dateDone;
    }
}
