package org.csc133.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class CmdSound extends Command {
    private GameWorld gw;

    /**
     * @param gw
     */
    public CmdSound(GameWorld gw) {
        super("Sound");
        this.gw = gw;
    }

    public void actionPerformed(ActionEvent ev) {
        Command on = new Command("Sound On");
        Command off = new Command("Sound Off");
        Command c = Dialog.show("", "Toggling Sound", on, off);

        if(c == on)
            gw.turnSoundOn();
        else if(c == off)
            gw.turnSoundOff();
    }
}
