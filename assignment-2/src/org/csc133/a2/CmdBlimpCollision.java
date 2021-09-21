package org.csc133.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * simulates a blimp collision
 */
public class CmdBlimpCollision extends Command {
    public GameWorld gw;

    /**
     * @param gw
     */
    public CmdBlimpCollision(GameWorld gw) {
        super("Blimp Collision");
        this.gw = gw;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        gw.blimpCollision();
    }
}
