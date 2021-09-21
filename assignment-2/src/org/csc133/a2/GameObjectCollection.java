package org.csc133.a2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

/**
 * stores all objects in game world
 */
public class GameObjectCollection extends Vector<GameObject> {
    private Vector<GameObject> gameObjects;

    public GameObjectCollection() {
        gameObjects = new Vector<>();
    }


}