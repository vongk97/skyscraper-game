package org.csc133.a1;

import com.codename1.charts.util.ColorUtil;

public class GameObject {

    private int size;
    private double x;
    private double y;
    private int color;

    public GameObject(int size, double x, double y) {
        this.size = size;
        this.x = x;
        this.y = y;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        // object size cannot be changed after creation
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    // when a game object is printed, it's toString methods are called from parent class to child class
    public String toString() {
        String mapOutput = "loc=" + (Math.round(x * 10) / 10.0) + ", " + (Math.round(y * 10) / 10.0) +
                " color=[" + ColorUtil.red(color) + "," +  ColorUtil.green(color) + "," + ColorUtil.blue(color) + "] " +
                "size= " + size;
        return mapOutput;
    }
}
