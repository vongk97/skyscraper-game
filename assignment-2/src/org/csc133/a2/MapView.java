package org.csc133.a2;

import java.awt.*;
import java.util.Iterator;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.*;

import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.plaf.Border;

/**
 * paints all game objects on screen on each clock tick
 */
public class MapView extends Container {

    private GameWorld gw;
    /**
     * @param gw
     */
    public MapView(GameWorld gw) {
        this.gw = gw; // reference to the game world so that paint() has access to GameWorld
        this.getAllStyles().setBorder(Border.createLineBorder(4,
                ColorUtil.rgb(255,0,0)));
    }


    public void update() {
        this.repaint();
        gw.map();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int w = getWidth();
        int h = getHeight();
        Point containerOrigin = new Point(getX(), getY());



        // Iterate through game object collection to update object states on map

        for (GameObject curObj : gw.gameObjectCollection) {
            // System.out.println(curObj.toString());
            curObj.draw(g, containerOrigin);
        }

        g.setAntiAliased(true);
        update();
    }


    @Override
    public int getWidth() {
        return super.getWidth();
    }

    @Override
    public int getHeight() {
        return super.getHeight();
    }
}
