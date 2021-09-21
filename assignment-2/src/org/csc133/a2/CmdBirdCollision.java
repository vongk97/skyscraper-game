package org.csc133.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * simulates a bird collision
 */
public class CmdBirdCollision extends Command {
    public GameWorld gw;

    /**
     * @param gw
     */
    public CmdBirdCollision(GameWorld gw) {
        super("Bird Collision");
        this.gw = gw;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        gw.birdCollision();
    }
}