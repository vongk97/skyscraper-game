package org.csc133.a1;

import com.codename1.charts.util.ColorUtil;

import java.awt.*;

public class MovableGO extends GameObject {

    private int heading; // compass angle (in degrees). 0 = north, 90 = east, etc.
    private int speed;

    public MovableGO(int size, double x, double y) {
        super(size, x, y);
        super.setColor(ColorUtil.rgb(50, 50, 50));
    }

    // update location based on heading and speed
    public void move() {
        setX( getX() + Math.cos( Math.toRadians(90 - heading) )*speed);
        setY( getY() + Math.sin( Math.toRadians(90 - heading) )*speed);

        checkOutOfBounds();
    }

    // Prevent any object from leaving the game world by turning around and moving again
    public void checkOutOfBounds() {
        if (getX() > 1024 || getX() < 0 || getY() > 768 || getY() < 0) {
            adjustHeading(180);
            move();
        }
    }

    public int getHeading() {
        return heading;
    }

    public void adjustHeading(int heading) {
        this.heading = (this.heading + heading) % 360;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String toString() {
        return super.toString() +
                " heading=" + heading + " speed=" + speed + " ";
    }
}
