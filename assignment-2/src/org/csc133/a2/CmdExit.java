package org.csc133.a2;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;

/**
 * prompt user for quitting game
 */
public class CmdExit extends Command {
    private GameWorld gw;

    /**
     * @param gw
     */
    public CmdExit(GameWorld gw) {
        super("Exit");
        this.gw =gw;
    }

    public void actionPerformed(ActionEvent event) {
        Command yes = new Command("Yes");
        Command no = new Command("No");
        Label emptyLabel = new Label("");
        Command c = Dialog.show("Are you sure you want to quit?", emptyLabel, yes, no);

        if(c == yes)
            gw.confirmGameExit();
        else if(c == no)
            gw.denyGameExit();
    }
}
