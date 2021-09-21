package org.csc133.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CmdPause extends Command {
    private GameWorld gw;

    /**
     * @param gw
     */
    public CmdPause(GameWorld gw) {
        super("Pause");
        this.gw = gw;
    }

    public void actionPerformed(ActionEvent ev) {
        if(gw.getPauseStatus() == true)
            gw.setPauseStatus(false);
        else {
            gw.setPauseStatus(true);
        }
    }
}
