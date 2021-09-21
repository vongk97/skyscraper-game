package org.csc133.a2;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

import java.awt.*;

public class PlayerHelicopter extends Helicopter implements ISteerable {
    /**
     * @param size
     * @param x
     * @param y
     */
    public PlayerHelicopter(int size, double x, double y, GameWorld gw) {
        super(size, x, y, gw);
        adjustHeading(0);
        super.setStickAngle(0);
        setSpeed(10);
        super.setLastSkyScraperReached(1);
    }


    @Override
    public void draw(Graphics g, Point containerOrigin) {
        int x = (int)getX() + (int)containerOrigin.getX();
        int y = (int)getY() + (int)containerOrigin.getY();

        g.setColor(ColorUtil.MAGENTA);
        g.fillArc(x - getSize()/2, y - getSize()/2, getSize(), getSize(), 0,360);
        g.setColor(ColorUtil.BLACK);

        g.drawLine( x, y, x + (int)(Math.cos(Math.toRadians(90-getHeading())) * getSize()/2), y + (int)(Math.sin(Math.toRadians(90-getHeading())) * getSize()/2));


    }

    public String toString() {
        return super.toString();
    }
}
