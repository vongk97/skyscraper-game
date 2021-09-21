package org.csc133.a3;

import com.codename1.util.MathUtil;


/**
 * defend strategy targets player last skyscraper location
 */
public class DefendStrategy implements IStrategy {
    private PlayerHelicopter playerHelicopter;
    private GameWorld gw;
    private NonPlayerHelicopter nph;
    private String strategyType;
    private Skyscraper targetSkyscraper;
    double xDistance;
    double yDistance;
    double idealHeading;

    /**
     * @param gw
     * @param nph
     */
    public DefendStrategy(GameWorld gw, NonPlayerHelicopter nph) {
        this.gw = gw;
        this.playerHelicopter = gw.getPlayerHelicopter();
        this.nph = nph;
        strategyType = "Defend Strategy";
    }

    public void setStrategy() {
        for (GameObject curObj : gw.gameObjectCollection) {
            if (curObj instanceof Skyscraper) {
                if (((Skyscraper) curObj).getSequenceNumber() == playerHelicopter.getLastSkyScraperReached()) {
                    targetSkyscraper = (Skyscraper) curObj;
                    xDistance = targetSkyscraper.getX() - nph.getX();
                    yDistance = targetSkyscraper.getY() - nph.getY();
                    break;
                }
            }
        }
        idealHeading = 90 - (int)Math.toDegrees(MathUtil.atan2(yDistance,xDistance));
        nph.setHeading((int) idealHeading);
    }

    @Override
    public void invokeStrategy() {
        setStrategy();
    }
}
