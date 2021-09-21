package org.csc133.a1;

import com.codename1.charts.util.ColorUtil;

public class RefuelingBlimp extends FixedGO {

    private int capacity;

    public RefuelingBlimp(int size, double x, double y) {
        super(size, x, y);
        setColor(ColorUtil.rgb(0, 255, 0));
        capacity = size; // initial capacity is proportional to its size
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
