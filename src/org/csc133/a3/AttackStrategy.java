package org.csc133.a3;

import com.codename1.util.MathUtil;

/**
 * attack strategy targets player helicopter location
 */
public class AttackStrategy implements IStrategy{
    private NonPlayerHelicopter nph;
    private PlayerHelicopter playerHelicopter;
    double idealHeading;

    public AttackStrategy(PlayerHelicopter playerHelicopter, NonPlayerHelicopter nph) {
        this.playerHelicopter = playerHelicopter;
        this.nph = nph;
    }

    public void setStrategy() {
        // distance between target location and current location
        double xDistance = playerHelicopter.getX() - nph.getX();
        double yDistance = playerHelicopter.getY() - nph.getY();

        // ideal heading will always track the location of player helicopter
        idealHeading = 90 - (int)Math.toDegrees(MathUtil.atan2(yDistance,xDistance));
        nph.setHeading((int) idealHeading);
    }

    @Override
    public void invokeStrategy() {
        setStrategy();
    }
}


