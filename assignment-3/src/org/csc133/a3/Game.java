package org.csc133.a3;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.Image;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.UITimer;
import java.io.IOException;

/**
 * sets up on-screen layout for game
 */
public class Game extends Form implements Runnable{
    private GameWorld gw;
    private MapView mapView;
    private GlassCockpit glassCockpit;
    private Button pauseBtn;
    Image arrowUp;
    Image arrowRight;
    Image arrowDown;
    Image arrowLeft;
    private BGSound bgSound = new BGSound("song.mp3");


    public Game() {
        gw = new GameWorld();
        gw.init();
        UITimer timer = new UITimer(this);
        timer.schedule(20, true, this);
        mapView = new MapView(gw);
        glassCockpit = new GlassCockpit(gw);
        this.setLayout(new BorderLayout()); // set GUI layout for top-level container

        // Command objects for each command from A1 (except "display" and "map")
        Command accelerate = new CmdAccelerate(gw);
        Command brake = new CmdBrake(gw);
        Command turnLeft = new CmdTurnLeft(gw);
        Command turnRight = new CmdTurnRight(gw);
        Command exit = new CmdExit(gw);
        Command pauseGame = new CmdPause(gw);

        // New Commands for A2
        Command changeStrategies = new CmdChangeStrategies(gw);
        Command about = new CmdAbout(gw);
        Command help = new CmdHelp(gw);
        // New Command for A3
        Command soundToggle = new CmdSound(gw);
        // Commands invoked by Key Binding
        addKeyListener('a', accelerate);
        addKeyListener('b', brake);
        addKeyListener('l', turnLeft);
        addKeyListener('r', turnRight);
        addKeyListener('x', exit);

        // Tool bar
        Toolbar toolbar = new Toolbar();
        this.setToolbar(toolbar);
        toolbar.setTitle("SkyMail 3000");

        // Add About, Exit, and Exit to side menu
        Command emptyCommand = new Command("");
        toolbar.addCommandToSideMenu(emptyCommand);
        toolbar.addCommandToSideMenu(about);
        toolbar.addCommandToSideMenu(help);
        toolbar.addCommandToSideMenu(exit);
        toolbar.addCommandToSideMenu(changeStrategies);
        toolbar.addCommandToSideMenu(soundToggle);

        try {
            arrowUp= Image.createImage("/arrow-up.png");
            arrowRight= Image.createImage("/arrow-right.png");
            arrowDown= Image.createImage("/arrow-down.png");
            arrowLeft= Image.createImage("/arrow-left.png");
        } catch (IOException e) { e.printStackTrace(); }

        // Commands invoked by Button
        Button accelerateBtn = new Button(arrowUp);
        accelerateBtn.setCommand(accelerate);

        Button brakeBtn = new Button(arrowDown);
        brakeBtn.setCommand(brake);

        Button leftBtn = new Button(arrowLeft);
        leftBtn.setCommand(turnLeft);

        Button rightBtn = new Button(arrowRight);
        rightBtn.setCommand(turnRight);

        pauseBtn = new Button("Unpaused");
        pauseBtn.setCommand(pauseGame);

        // Buttons are placed in south region of the game
        Container southRegion = new Container();
        southRegion.setLayout(new TableLayout(1, 4));
        southRegion.getAllStyles().setPadding(10,10,10,10);
        southRegion.addComponent(accelerateBtn);
        southRegion.addComponent(brakeBtn);
        southRegion.addComponent(leftBtn);
        southRegion.addComponent(rightBtn);
        southRegion.addComponent(pauseBtn);
        southRegion.getAllStyles().setBorder(Border.createLineBorder(5, ColorUtil.BLACK));
        southRegion.getAllStyles().setBgTransparency(120);
        southRegion.getAllStyles().setBgColor(ColorUtil.GREEN);

        // Add all containers to respective part of screen
        this.add(BorderLayout.NORTH, glassCockpit);
        this.add(BorderLayout.SOUTH, southRegion);
        this.add(BorderLayout.CENTER, mapView);

        // set game world size based on map view size
        gw.setMapHeight(mapView.getComponentForm().getHeight());
        gw.setMapWidth(mapView.getComponentForm().getWidth());

        this.show();
    }

    public void updateSound() {
        // check if sound is turned on
        if(gw.getSoundStatus() == true) {
            // pause background music if game is paused.
            // otherwise, play background music
            if(gw.getPauseStatus() == true) {
                bgSound.pause();
            }
            else {
                bgSound.play();
            }
        }
        else {
            bgSound.pause();
        }

    }

    @Override
    public void run() {
        if(gw.getPauseStatus() == false) {
            gw.tick(20, mapView);
            updateSound();
            pauseBtn.setText("Pause");
        }
        else {
            bgSound.pause();
            pauseBtn.setText("Play");
        }
    }
}
