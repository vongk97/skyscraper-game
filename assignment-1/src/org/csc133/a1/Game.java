package org.csc133.a1;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

import java.lang.String;

public class Game extends Form {
    private GameWorld gw;

    public Game() {
        gw = new GameWorld();
        gw.init();
        play();
    }

    private void play() {
        // accept and execute user commands that operate on the game world
        Label myLabel = new Label("Enter a Command:");
        this.addComponent(myLabel);
        final TextField myTextField = new TextField();
        this.addComponent(myTextField);
        this.show();

        myTextField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                String sCommand = myTextField.getText().toString();
                myTextField.clear();
                switch (sCommand.charAt(0)){
                    case 'x':
                        gw.exitPrompt();
                        break;
                    case 'a':
                        gw.accelerate();
                        break;
                    case 'b':
                        gw.brake();
                        break;
                    case 'l':
                        gw.turnLeft();
                        break;
                    case 'r':
                        gw.turnRight();
                        break;
                    case 'c':
                        gw.heliCollision();
                        break;
                    case '1':
                        gw.skyScraperCollision(1);
                        break;
                    case '2':
                        gw.skyScraperCollision(2);
                        break;
                    case '3':
                        gw.skyScraperCollision(3);
                        break;
                    case '4':
                        gw.skyScraperCollision(4);
                        break;
                    case '5':
                        gw.skyScraperCollision(5);
                        break;
                    case '6':
                        gw.skyScraperCollision(6);
                        break;
                    case '7':
                        gw.skyScraperCollision(7);
                        break;
                    case '8':
                        gw.skyScraperCollision(8);
                        break;
                    case '9':
                        gw.skyScraperCollision(9);
                        break;
                    case 'e':
                        // playerHelicopter collided with a refueling blimp
                        gw.blimpCollision();
                        break;
                    case 'g':
                        gw.birdCollision();
                        break;
                    case 't':
                        gw.clock_tick();
                        break;
                    case 'd':
                        gw.display();
                        break;
                    case 'm':
                        gw.map();
                        break;
                    case 'y':
                        gw.confirmGameExit();
                        break;
                    case 'n':
                        gw.denyGameExit();
                        break;
                }
            }
        });

    }
}
