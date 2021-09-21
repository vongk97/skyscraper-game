package org.csc133.a3;

import com.codename1.charts.util.ColorUtil;


/**
 * updates positions of game objects and handles out-of-bounds corrections
 */
public class MovableGO extends GameObject {

    private int heading; // compass angle (in degrees). 0 = north, 90 = east, etc.
    private int speed;

    public MovableGO(int size, double x, double y, GameWorld gw) {
        super(size, x, y, gw);
        super.setColor(ColorUtil.rgb(50, 50, 50));
    }

    // update location based on heading and speed
    // adjust the speed based on the elapsed time
    public void move(int elapsedTime, MapView mapView) {
        setX( getX() + Math.cos( Math.toRadians(90 - heading) )*speed/elapsedTime*10);
        setY( getY() + Math.sin( Math.toRadians(90 - heading) )*speed/elapsedTime*10);

        checkOutOfBounds(elapsedTime, mapView);
    }

    // Prevent any object from leaving the game world by turning around and moving again
    public void checkOutOfBounds(int elapsedTime, MapView mapView) {
        if (getX() > mapView.getWidth() || getX() < 0 || getY() > mapView.getHeight() || getY() < 0) {
            adjustHeading(91);
            move(elapsedTime, mapView);
        }
    }

    public int getHeading() {
        return heading;
    }

    public void adjustHeading(int heading) {
        this.heading = (this.heading + heading) % 360;
        if (this.heading < 0) {
            this.heading = (this.heading % 360) + 360;
        }
    }

    public void setHeading(int heading) {
        this.heading = heading;
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
