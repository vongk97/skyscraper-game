package org.csc133.a1;

public class FixedGO extends GameObject{

    public FixedGO(int size, double x, double y) {
        super(size, x, y);
    }

    // All fixed game objects are not allowed to change location once they are created
    @Override
    public void setX(double x) { }
    @Override
    public void setY(double y) { }
}
