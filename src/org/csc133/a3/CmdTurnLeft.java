package org.csc133.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * turn helicopter
 */
public class CmdTurnLeft extends Command {
    private GameWorld gw;

    /**
     * @param gw
     */
    public CmdTurnLeft(GameWorld gw) {
        super("");
        this.gw = gw;
    }

    public void actionPerformed(ActionEvent event) {
        gw.turnLeft();
    }
}
