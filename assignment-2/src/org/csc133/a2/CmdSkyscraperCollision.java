package org.csc133.a2;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;


/**
 * simulate skyscraper collision
 */
public class CmdSkyscraperCollision extends Command {
    private GameWorld gw;

    /**
     * @param gw
     */
    public CmdSkyscraperCollision(GameWorld gw) {
        super("Skyscraper Reached");
        this.gw = gw;
    }

    public void actionPerformed(ActionEvent event) {
        int checkpoint;
        Command enter = new Command("Confirm");
        TextField myTextField = new TextField();
        Dialog.show("Enter a skyscraper: ", myTextField, enter);

        if(!(myTextField.getText() == "")) {
            checkpoint = Integer.parseInt(myTextField.getText());
            if(checkpoint <= 0) {
                Dialog.show("", "Invalid Input. Enter a positive integer.", enter);
            }
            else if(gw.getPlayerHelicopter().getLastSkyScraperReached() >= checkpoint) {
                Dialog.show("This checkpoint has already been reached.", "", enter);
            }
            else if(gw.getPlayerHelicopter().getLastSkyScraperReached() > 4) {
                Dialog.show("Skyscraper does not exist.", "", enter);
            }
            else {
                gw.getPlayerHelicopter().setLastSkyScraperReached(checkpoint);
            }
        }
        else
            Dialog.show("Invalid input.", "", enter);

    }
}
