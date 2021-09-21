package org.csc133.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * simulate helicopter collision
 */
public class CmdHeliCollision extends Command {
    public GameWorld gw;

    /**
     * @param gw
     */
    public CmdHeliCollision(GameWorld gw) {
        super("Heli Collision");
        this.gw = gw;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        gw.heliCollision();
    }
}