package org.csc133.a1;

import com.codename1.charts.util.ColorUtil;

public class Skyscraper extends FixedGO {

    private int sequenceNumber; // waypoint for helicopter flight path

    public Skyscraper(int size, double x, double y, int sequenceNumber) {
        super(size, x, y);
        this.sequenceNumber = sequenceNumber;
        super.setColor(ColorUtil.rgb(0, 0, 255)); // super calls superclass method, not in this class
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
