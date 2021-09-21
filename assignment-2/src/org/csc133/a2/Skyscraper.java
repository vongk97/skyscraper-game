package org.csc133.a2;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

import javax.swing.plaf.synth.ColorType;
import java.awt.*;

public class Skyscraper extends FixedGO {
    private int sequenceNumber; // waypoint for helicopter flight path

    /**
     * @param size
     * @param x
     * @param y
     * @param sequenceNumber
     */
    public Skyscraper(int size, double x, double y, int sequenceNumber) {
        super(size, x, y);
        this.sequenceNumber = sequenceNumber;
        super.setColor(ColorUtil.rgb(0, 0, 255)); // super calls superclass method, not in this class
    }

    @Override
    public void draw(Graphics g, Point pointContainer) {
        int x = (int)getX() + (int)pointContainer.getX();
        int y = (int)getY() + (int)pointContainer.getY();
        g.setColor(ColorUtil.LTGRAY);
        g.fillRect(x, y, getSize(), getSize());
        g.setColor(ColorUtil.BLACK);
        g.drawString(Integer.toString(sequenceNumber), x + (getSize()/4), y + (getSize()/4));

    }

    @Override
    public void setColor(int color) {
        // Skyscraper color cannot be changed after creation
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }
    public String toString() {
        return "Skyscraper: " + super.toString() + " seqNum=" + sequenceNumber + " ";
    }
}
