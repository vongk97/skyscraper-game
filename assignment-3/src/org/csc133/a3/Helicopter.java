package org.csc133.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.util.MathUtil;

import java.awt.*;


public class Helicopter extends MovableGO implements ISteerable {

    private int stickAngle; // indicates a desired change in heading upon each clock tick
    private int maximumSpeed;
    private int fuelLevel;
    private int fuelConsumptionRate;
    private int damageLevel;
    private int lives;
    private int lastSkyScraperReached;

    /**
     * @param size
     * @param x
     * @param y
     * @param gw
     */
    protected Helicopter(int size, double x, double y, GameWorld gw) {
        super(size, x, y, gw);
        adjustHeading(0);
        stickAngle = 0;
        maximumSpeed = 40;
        fuelLevel = 1000;
        fuelConsumptionRate = 1;
        damageLevel = 0;
        lives = 3;
    }


    @Override
    public void draw(Graphics g, Point pointContainer) {

    }

    public void setStickAngle(int turnValue) {
        stickAngle += turnValue;
        if (stickAngle > 40)
            stickAngle = 40;
        else if (stickAngle < -40)
            stickAngle = -40;
    }

    public void takeDamage(int damage) {
        damageLevel += damage;
        // helicopters with damage b/w zero and max damage should be limited in speed to corresponding percentage of their speed range
        maximumSpeed -= damage;
    }

    public void adjustHeading() {
        if(stickAngle > 0) {
            super.adjustHeading(5);
            setStickAngle(-5);
        }
        else if(stickAngle < 0) {
            super.adjustHeading(-5);
            setStickAngle(5);
        }
    }

    public int getFuelLevel() {
        return fuelLevel;
    }

    public void setFuelLevel(int fuelLevel) {
        this.fuelLevel = fuelLevel;
        if (fuelLevel >= 10000)
            this.fuelLevel = 9999;
    }

    public int getFuelConsumptionRate() {
        return fuelConsumptionRate;
    }

    public int getDamageLevel() {
        return damageLevel;
    }

    public int getLastSkyScraperReached() {
        return lastSkyScraperReached;
    }

    public void setLastSkyScraperReached(int lastSkyScraperReached) {
        this.lastSkyScraperReached = lastSkyScraperReached;
    }

    public int getLives() { return lives; }

    public void updateLives() {
        lives--;
    }

    public void setSpeed(int x) {
        super.setSpeed(x);
        if(getSpeed() > maximumSpeed) {
            System.out.println("You are at max speed.");
            super.setSpeed(maximumSpeed);
        }
        else if(getSpeed() < 0) {
            System.out.println("You are not moving.");
            super.setSpeed(0);
        }

    }

    public void resetHelicopter() {
        damageLevel = 0;
        setSpeed(0);
        maximumSpeed = 100;
        stickAngle = 0;
        fuelLevel = 1000;
    }

    public String toString() {
        return "Helicopter: " + super.toString() +
                "maxSpeed=" + maximumSpeed + " stickAngle=" + stickAngle + " fuelLevel=" + fuelLevel + " damageLevel=" + damageLevel;
    }

}
