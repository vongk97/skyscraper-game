package org.csc133.a3;

public class FixedGO extends GameObject{

    /**
     * @param size
     * @param x
     * @param y
     */
    public FixedGO(int size, double x, double y, GameWorld gw) {
        super(size, x, y, gw);
    }

    // All fixed game objects are not allowed to change location once they are created
    @Override
    public void setX(double x) { }
    @Override
    public void setY(double y) { }
}
