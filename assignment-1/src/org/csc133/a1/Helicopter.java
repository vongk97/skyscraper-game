package org.csc133.a1;

import com.codename1.charts.util.ColorUtil;

public class Helicopter extends MovableGO implements ISteerable {

    private int stickAngle; // indicates a desired change in heading upon each clock tick
    private int maximumSpeed = 100;
    private int fuelLevel = 100;
    private int fuelConsumptionRate = 5;
    private int damageLevel = 0;
    private int lastSkyScraperReached = 1;

    public Helicopter(int size, double x, double y) {
        super(size, x, y);
        setColor(ColorUtil.rgb(55, 0, 0));
        adjustHeading(0);
        stickAngle = 0;
        setSpeed(10);
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
        setColor( ColorUtil.rgb( (ColorUtil.red(getColor()) )+ 50, 0, 0)); // increase red value by 50
        // helicopters with damage between zero and the maximum damage should be limited in speed to a corresponding percentage of their speed range
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
        if (fuelLevel > 100)
            this.fuelLevel = 100;
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

    public void setSpeed(int x) {
        super.setSpeed(x);
        if(getSpeed() > maximumSpeed) {
            System.out.println("You are at max speed.");
            super.setSpeed(maximumSpeed);
        }
        else if(getSpeed() < 0) {
            System.out.print("You are not moving.");
            super.setSpeed(0);
        }

    }

    // reset helicopter state and reposition at first skyscraper
    public void resetHelicopter() {
        damageLevel = 0;
        setColor(ColorUtil.rgb(55, 0, 0));
        setSpeed(0);
        maximumSpeed = 100;
        stickAngle = 0;
        fuelLevel = 100;
        setX(300);
        setY(300);
    }

    public String toString() {
        return "Helicopter: " + super.toString() +
                "maxSpeed=" + maximumSpeed + " stickAngle=" + stickAngle + " fuelLevel=" + fuelLevel + " damageLevel=" + damageLevel;
    }
}
