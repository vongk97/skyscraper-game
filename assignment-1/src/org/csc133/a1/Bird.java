package org.csc133.a1;

import com.codename1.charts.util.ColorUtil;

public class Bird extends MovableGO {


    public Bird(int size, double x, double y) {
        super(size, x, y);
        setColor(ColorUtil.rgb(50, 50, 50));
        adjustHeading((int) (Math.random() * (360))); // Random size between 0 and 359 inclusive
        setSpeed((int) (Math.random() * (6) + 5));
    }

    @Override
    public void setColor(int color) {
        // Cannot change value after creation
    }

    public String toString() {
        return "Bird: " + super.toString();
    }
}
