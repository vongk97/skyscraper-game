package org.csc133.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * accelerates player helicopter in game world
 */
public class CmdAccelerate extends Command {
    private GameWorld gw;

    /**
     * @param gw
     */
    public CmdAccelerate(GameWorld gw) {
        super("");
        this.gw = gw;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        gw.accelerate();
    }
}
