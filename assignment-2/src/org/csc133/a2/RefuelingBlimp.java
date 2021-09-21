package org.csc133.a2;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

import java.awt.*;

public class RefuelingBlimp extends FixedGO {

    private int capacity;
    /**
     * @param size
     * @param x
     * @param y
     */
    public RefuelingBlimp(int size, double x, double y) {
        super(size, x, y);
        setColor(ColorUtil.rgb(0, 255, 0));
        capacity = size; // initial capacity is proportional to its size
    }

    @Override
    public void draw(Graphics g, Point pointContainer) {
        int x = (int)getX() + (int)pointContainer.getX();
        int y = (int)getY() + (int)pointContainer.getY();
        if (capacity == 0)
            g.setColor(ColorUtil.rgb(150,255,150));
        else
            g.setColor(ColorUtil.GREEN);
        g.fillArc(x, y, 3*getSize(), 2*getSize(), 0, 360);
        g.setColor(ColorUtil.BLACK);
        g.drawString(Integer.toString(capacity), x + (getSize()), y + (getSize()));
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String toString() {
        return "RefuelingBlimp: " + super.toString() + " capacity=" + capacity + " ";
    }
}
