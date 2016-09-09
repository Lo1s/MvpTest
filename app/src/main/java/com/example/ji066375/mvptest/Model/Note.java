package com.example.ji066375.mvptest.Model;

/**
 * Created by ji066375 on 30.3.2016.
 */
public class Note {
    private String note;

    public Note(String text) {
        setNote(text);
    }

    public void setNote(String text) {
        this.note = text;
    }

    public String getNote() {
        return this.note;
    }
}
