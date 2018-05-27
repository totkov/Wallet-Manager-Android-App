package com.example.virus_000.moviesapp;

/**
 * Created by virus_000 on 12/1/2016.
 */

public class Movie {
    private int id;
    private String mname;
    private String genre;
    private String desc;
    private String cast;
    private String picture;

    public Movie() {

    }

    public Movie(String mname, String genre, String desc, String cast, String picture) {
        this.mname = mname;
        this.genre = genre;
        this.desc = desc;
        this.cast = cast;
        this.picture = picture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
