package org.csc133.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

import java.awt.*;

public class Bird extends MovableGO {

    /**
     * @param size
     * @param x
     * @param y
     * @param gw
     */
    public Bird(int size, double x, double y, GameWorld gw) {
        super(size, x, y, gw);
        setColor(ColorUtil.rgb(50, 50, 50));
        adjustHeading((int) (Math.random() * (360))); // Random size between 0 and 359 inclusive
        setSpeed((int) (Math.random() * (2) + 1));
    }

    @Override
    public void draw(Graphics g, Point containerOrigin) {
        int x = (int)getX() + (int)containerOrigin.getX();
        int y = (int)getY() + (int)containerOrigin.getY();

        g.setColor(ColorUtil.rgb(100,100,100));
        g.fillArc(x - getSize()/2, y - getSize()/2, getSize(), getSize(), 0,360);
        g.setColor(ColorUtil.BLACK);

        g.drawLine( x, y, x + (int)(Math.cos(Math.toRadians(90-getHeading())) * getSize()/2), y + (int)(Math.sin(Math.toRadians(90-getHeading())) * getSize()/2));
    }

    @Override
    public void setColor(int color) {
        // Cannot change value after creation
    }

    public String toString() {
        return "Bird: " + super.toString();
    }
}
