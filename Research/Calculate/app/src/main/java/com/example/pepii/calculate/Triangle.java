package com.example.pepii.calculate;

/**
 * Created by pepii on 19-May-18.
 */

public class Triangle {

    public Triangle(){

    }
    public Triangle(double side, double height, double surface){
        this.side=side;
        this.height=height;
        this.surface=surface;
    }
    private double side;
    private double height;
    private double surface;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;

    public double getSide() {
        return side;
    }

    public void setSide(double side) {
        this.side = side;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getSurface() {
        return surface;
    }

    public void setSurface(double surface) {
        this.surface = surface;
    }
}
