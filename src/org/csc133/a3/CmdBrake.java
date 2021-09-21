package org.csc133.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * slows down player helicopter in game world
 */
public class CmdBrake extends Command{
    private GameWorld gw;

    /**
     * @param gw
     */
    public CmdBrake(GameWorld gw)
    {
        super("");
        this.gw = gw;
    }

    public void actionPerformed(ActionEvent event) {
        gw.brake();
    }
}
