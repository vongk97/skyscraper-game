package org.csc133.a3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * stores all objects in game world
 */
public class GameObjectCollection extends CopyOnWriteArrayList<GameObject> {
    private CopyOnWriteArrayList<GameObject> gameObjects;

    public GameObjectCollection() {
        gameObjects = new CopyOnWriteArrayList <>();
    }


}