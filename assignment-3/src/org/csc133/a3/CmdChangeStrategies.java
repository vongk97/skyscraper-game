package org.csc133.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

/**
 * change strategies for non-player helicopter
 */

public class CmdChangeStrategies extends Command {
    private GameWorld gw;

    /**
     * @param gw
     */
    public CmdChangeStrategies(GameWorld gw)
    {
        super("Change Strategies");
        this.gw = gw;
    }

    public void actionPerformed(ActionEvent event) {
        Command attack = new Command("Attack");
        Command defend = new Command("Defend");
        Command c = Dialog.show("", "Choose a strategy to activate.", attack, defend);

        if(c == attack)
            gw.activateAttackStrategy();
        else if(c == defend)
            gw.activateDefendStrategy();
    }
}
