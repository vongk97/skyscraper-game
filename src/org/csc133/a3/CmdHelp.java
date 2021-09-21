package org.csc133.a3;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BoxLayout;


/**
 * display list of commands for game
 */
public class CmdHelp extends Command{
    private GameWorld gw;

    /**
     * @param gw
     */
    public CmdHelp(GameWorld gw) {
        super("Help");
        this.gw = gw;
    }

    public void actionPerformed(ActionEvent event) {
        Dialog help = new Dialog("Command List", new BoxLayout(BoxLayout.Y_AXIS));
        help.add(new Label("Accelerate: a"));
        help.add(new Label("Brake: b"));
        help.add(new Label("Left Turn: l"));
        help.add(new Label("Right Turn: r"));
        help.add(new Label("Exit: x"));

        Command ok = new Command("OK");
        Command c = Dialog.show("", help, ok);
        return;

    }
}
