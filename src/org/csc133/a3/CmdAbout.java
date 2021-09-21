package org.csc133.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BoxLayout;

/**
 * displays game information and author
 */
public class CmdAbout extends Command {
    private GameWorld gw;

    /**
     * @param gw
     */
    public CmdAbout(GameWorld gw) {
        super("About");
        this.gw = gw;
    }

    public void actionPerformed(ActionEvent event) {
        Dialog about = new Dialog("About", new BoxLayout(BoxLayout.Y_AXIS));
        about.add(new Label("Kevin Vong"));
        about.add(new Label("CSC 133"));
        about.add(new Label("Version 0.2"));
        Command ok= new Command("OK");

        Command c = Dialog.show("", about, ok);
        return;
    }
}
