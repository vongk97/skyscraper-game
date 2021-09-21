package org.csc133.a2;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

import java.awt.*;
import java.util.Random;

public class NonPlayerHelicopter extends Helicopter{
    IStrategy currentStrategy;
    Random r = new Random();
    /**
     * @param size
     * @param x
     * @param y
     * @param gw
     */
    public NonPlayerHelicopter(int size, double x, double y, GameWorld gw) {
        super(size, x, y, gw);
        super.adjustHeading(0);
        super.setSpeed(5);
        currentStrategy = new AttackStrategy(gw.getPlayerHelicopter(), this); // default strategy
    }

    @Override
    public void draw(Graphics g, Point containerOrigin) {

        currentStrategy.invokeStrategy();
        int x = (int)getX() + (int)containerOrigin.getX();
        int y = (int)getY() + (int)containerOrigin.getY();

        g.setColor(ColorUtil.rgb(255,0,0));
        g.fillArc(x - getSize()/2, y - getSize()/2, getSize(), getSize(), 0,360);
        g.setColor(ColorUtil.BLACK);

        g.drawLine( x, y, x + (int)(Math.cos(Math.toRadians(90-getHeading())) * getSize()/2), y + (int)(Math.sin(Math.toRadians(90-getHeading())) * getSize()/2));
    }

    public void setStrategy(String strategyType, GameWorld gw) {
        if(strategyType == "Attack")
            currentStrategy = new AttackStrategy(gw.getPlayerHelicopter(), this);
        if(strategyType == "Defend")
            currentStrategy = new DefendStrategy(gw, this);
    }

    @Override
    public String toString() {
        return "Non-Player " + super.toString();
    }

}
