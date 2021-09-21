package org.csc133.a2;

import com.codename1.ui.Graphics;

import java.awt.*;

/**
 * Provides objects with ability to draw on map view
 */
public interface IDrawable {

    public void draw(Graphics g, Point containerOrigin);
}
