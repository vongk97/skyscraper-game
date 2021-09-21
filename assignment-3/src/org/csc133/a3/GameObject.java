package org.csc133.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

import java.awt.*;


public class GameObject implements IDrawable {

    private int size;
    private double x;
    private double y;
    private int color;
    private GameWorld gw;
    private boolean collisionStatus;

    /**
     * @param size
     * @param x
     * @param y
     */
    public GameObject(int size, double x, double y, GameWorld gw) {
        this.size = size;
        this.x = x;
        this.y = y;
        this.gw = gw;
        collisionStatus = false;
    }

    @Override
    public void draw(Graphics g, Point containerOrigin) {

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
                " color=[" + ColorUtil.red(color) + "," + ColorUtil.green(color) + "," + ColorUtil.blue(color) + "] " +
                "size= " + size;
        return mapOutput;
    }

    public double getTopBound() {
        return (getY() - (size / 2));
    }

    public double getBottomBound() {
        return (getY() + (size / 2));
    }

    public double getRightBound() {
        return (getX() + (size / 2));
    }

    public double getLeftBound() {
        return (getX() - (size / 2));
    }

    public boolean getCollisionStatus() {
        return collisionStatus;
    }

    public void setCollisionStatus(boolean collisionStatus) {
        this.collisionStatus = collisionStatus;
    }

    public boolean collidesWith(GameObject otherObject) {
        if (getRightBound() < otherObject.getLeftBound() || getLeftBound() > otherObject.getRightBound())
            return false;

        if (otherObject.getTopBound() > getBottomBound() || getTopBound() > otherObject.getBottomBound())
            return false;

        return true;
    }

    public void handleCollision(GameObject otherObject) {
        if (this instanceof PlayerHelicopter) {
            if (otherObject instanceof NonPlayerHelicopter) {
                gw.heliCollision();
            }
            if (otherObject instanceof Skyscraper) {
                gw.skyScraperCollision(otherObject);
            }
            if (otherObject instanceof Bird) {
                gw.birdCollision();
            }
            if (otherObject instanceof RefuelingBlimp) {
                gw.blimpCollision(otherObject);
            }
        }
    }
}
