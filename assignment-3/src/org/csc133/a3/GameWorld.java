package org.csc133.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

import java.util.Random;

/**
 * add all objects to a collection and update display values on each clock tick
 */
public class GameWorld{
    
    private PlayerHelicopter playerHelicopter;
    private int worldWidth;
    private int worldHeight;
    private int clock = 0;
    private Random r = new Random(); // for object location spawning
    private boolean isExitPrompt = false; // for exit
    private boolean isSound = true;
    private boolean isPaused = false;
    private boolean isRefuelCollision = false;
    private boolean isCrash = false;
    private boolean isDeath = false;
    private Sound refuelSound;
    private Sound crashSound;
    private Sound deathSound;
    private Sound victorySound;

    private int skyscraperSize;
    private int helicopterSize;
    private int startX;
    private int startY;
    GameObjectCollection gameObjectCollection = new GameObjectCollection();
    GameObjectCollection objectCollisionCollection = new GameObjectCollection();

    public GameWorld() {
        worldWidth = 1024;
        worldHeight = 1536;
        skyscraperSize = 100;
        helicopterSize = 100;
    }

    /**
     * Initializes the game world and all game objects within it
     */
    public void init() {
        startX = r.nextInt(worldWidth);
        startY = r.nextInt(worldHeight);
        gameObjectCollection.add(new Skyscraper(skyscraperSize, startX, startY, 1, this));
        gameObjectCollection.add(new Skyscraper(skyscraperSize, r.nextInt(worldWidth), r.nextInt(worldHeight), 2, this));
        gameObjectCollection.add(new Skyscraper(skyscraperSize, r.nextInt(worldWidth), r.nextInt(worldHeight), 3, this));
        gameObjectCollection.add(new Skyscraper(skyscraperSize, r.nextInt(worldWidth), r.nextInt(worldHeight), 4, this));
        gameObjectCollection.add(playerHelicopter = new PlayerHelicopter(helicopterSize, startX, startY, this));
        gameObjectCollection.add(new Bird(r.nextInt(41) + 30, r.nextInt(worldWidth), r.nextInt(worldHeight), this));
        gameObjectCollection.add(new Bird(r.nextInt(41) + 30, r.nextInt(worldWidth), r.nextInt(worldHeight), this));
        gameObjectCollection.add(new Bird(r.nextInt(41) + 30, r.nextInt(worldWidth), r.nextInt(worldHeight), this));
        gameObjectCollection.add(new Bird(r.nextInt(41) + 30, r.nextInt(worldWidth), r.nextInt(worldHeight), this));
        gameObjectCollection.add(new RefuelingBlimp(r.nextInt(51) + 10, r.nextInt(worldWidth), r.nextInt(worldHeight), this));
        gameObjectCollection.add(new RefuelingBlimp(r.nextInt(51) + 10, r.nextInt(worldWidth), r.nextInt(worldHeight), this));
        gameObjectCollection.add(new NonPlayerHelicopter(helicopterSize, r.nextInt(worldWidth), r.nextInt(worldHeight), this));
        gameObjectCollection.add(new NonPlayerHelicopter(helicopterSize, r.nextInt(worldWidth), r.nextInt(worldHeight), this));
        gameObjectCollection.add(new NonPlayerHelicopter(helicopterSize, r.nextInt(worldWidth), r.nextInt(worldHeight), this));

        crashSound = new Sound("crash.wav");
        refuelSound = new Sound("heal.wav");
    }

    public void accelerate() {
        System.out.println("Accelerating");
        playerHelicopter.setSpeed(playerHelicopter.getSpeed() + 5);
    }

    public void brake() {
        System.out.println("Braking");
        playerHelicopter.setSpeed(playerHelicopter.getSpeed() - 5);
    }

    // Adjusts heli stick angle, but does NOT change heading until clock ticks
    public void turnLeft() {
        System.out.println("Turning left");
        playerHelicopter.setStickAngle(-5);
    }
    public void turnRight() {
        // change player helicopter heading
        System.out.println("Turning right");
        playerHelicopter.setStickAngle(5);
    }

    public void updateLives() {
        playerHelicopter.updateLives();
        // update location
        if (playerHelicopter.getLives() <= 0) {
            if(isSound) {
                deathSound.play();
            }
            Dialog.show("Game Over", "You Win!!!", new Command("OK") {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });
        }
        else {
            System.out.println("You lost a life. You have " + playerHelicopter.getLives() + " lives remaining.");
            playerHelicopter.resetHelicopter();
            // Reposition helicopter at most recent checkpoint
            for (GameObject curObj : gameObjectCollection) {
                if (curObj instanceof Skyscraper) {
                    if (playerHelicopter.getLastSkyScraperReached() == ((Skyscraper) curObj).getSequenceNumber()) {
                        playerHelicopter.setX(curObj.getX());
                        playerHelicopter.setY(curObj.getY());
                        playerHelicopter.setSpeed(0);
                        deathSound = new Sound("dying.wav");
                        if(isSound) {
                            deathSound.play();
                        }
                    }
                }
            }
            isDeath = true;
        }
    }

    public void activateAttackStrategy() {
        for (GameObject curObj : gameObjectCollection) {
            if (curObj instanceof NonPlayerHelicopter) {
                ((NonPlayerHelicopter) curObj).setStrategy("Attack", this);
            }
        }
    }
    public void activateDefendStrategy() {
        for (GameObject curObj : gameObjectCollection) {
            if (curObj instanceof NonPlayerHelicopter) {
                ((NonPlayerHelicopter) curObj).setStrategy("Defend", this);
            }
        }
    }
    
    // Update states for all MOVABLE objects
    public void tick(int elapsedTime, MapView mapView) {
        playerHelicopter.adjustHeading();

        playerHelicopter.setFuelLevel(playerHelicopter.getFuelLevel() - playerHelicopter.getFuelConsumptionRate());
        if (playerHelicopter.getFuelLevel() <= 0 || playerHelicopter.getDamageLevel() >= 100) {
            updateLives();
        }
        // move all MOVABLE object locations based on heading and speed
        for (GameObject curObj : gameObjectCollection) {
            if (curObj instanceof MovableGO) {
                // Update bird headings by -5 to 5 degrees before moving
                if (curObj instanceof Bird)
                    ((Bird) curObj).adjustHeading(r.nextInt(10 + 1) - 5);
                // pass an elapsed time to move method to compute a new location
                ((MovableGO) curObj).move(elapsedTime, mapView);
            }

            if(playerHelicopter.collidesWith(curObj)) {
                if(!objectCollisionCollection.contains(curObj)) {
                    objectCollisionCollection.add(curObj);
                    playerHelicopter.handleCollision(curObj);
                }
            }
            else {
                objectCollisionCollection.remove(curObj);
            }

        }
        //System.out.println("Game clock has ticked");
        // Increase elapsed time by 1
        clock++;
    }

    public PlayerHelicopter getPlayerHelicopter() {
        return playerHelicopter;
    }



    public void display() {
        System.out.println("Lives Remaining: " + playerHelicopter.getLives());
        System.out.println("Elapsed Time: " + clock);
        System.out.println("Last Skyscraper Reached: " + playerHelicopter.getLastSkyScraperReached());
        System.out.println("Helicopter Fuel Level: " + playerHelicopter.getFuelLevel());
        System.out.println("Helicopter Damage Level: " + playerHelicopter.getDamageLevel());
    }

    public void map() {
        for (GameObject curObj : gameObjectCollection) {
            System.out.println(curObj.toString());
        }
    }

    public void exitPrompt() {
        System.out.println("Are you sure you want to exit? Enter 'y' or 'n'");
        isExitPrompt = true;
    }
    public void confirmGameExit() {
        System.out.println("Terminating game.");
        System.exit(0);
    }
    public void denyGameExit() {
        System.out.println("Continuing game.");
        isExitPrompt = false;
    }
    
    public void setMapWidth(int width) {
        worldWidth = width;
    }
    public void setMapHeight(int height) {
        worldHeight = height;
    }

    public void turnSoundOn() {
        isSound = true;
    }
    public void turnSoundOff() {
        isSound = false;
    }

    public boolean getSoundStatus() {
        return isSound;
    }

    public boolean getPauseStatus() {
        return isPaused;
    }

    public void setPauseStatus(boolean isPaused) {
        this.isPaused = isPaused;
    }

    public void heliCollision() {
        playerHelicopter.takeDamage(20);
        if(isSound) {
            crashSound.play();
        }
        if(playerHelicopter.getDamageLevel() >= 100)
            updateLives();

    }

    public void birdCollision() {
        playerHelicopter.takeDamage(5);
        if(isSound) {
            crashSound.play();
        }
        if(playerHelicopter.getDamageLevel() >= 100)
            updateLives();
    }

    // Check for checkpoint update or win
    public void skyScraperCollision(GameObject otherObject) {

        if ( ((Skyscraper)otherObject).getSequenceNumber() == playerHelicopter.getLastSkyScraperReached() + 1) {
            playerHelicopter.setLastSkyScraperReached(((Skyscraper)otherObject).getSequenceNumber() );
            System.out.println("Checkpoint reached at Sky Scraper # " + playerHelicopter.getLastSkyScraperReached());
        }
        if (playerHelicopter.getLastSkyScraperReached() == 4) {
            System.out.println("Game over, you win!");
            victorySound = new Sound("victory.wav");
            if(isSound) {
                victorySound.play();
            }
            Dialog.show("Game Over", "You Win!!!", new Command("OK") {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });
        }

    }

    // Search for the first non-empty refueling blimp in object collection to consume. Spawn a new blimp.
    public void blimpCollision(GameObject otherObject) {
        if(((RefuelingBlimp) otherObject).getCapacity() != 0) {
            playerHelicopter.setFuelLevel(playerHelicopter.getFuelLevel() + ((RefuelingBlimp) otherObject).getCapacity());
            ((RefuelingBlimp) otherObject).setCapacity(0);
            if(isSound) {
                refuelSound.play();
            }
        }
        gameObjectCollection.add(new RefuelingBlimp(r.nextInt(51) + 10, r.nextInt(worldWidth), r.nextInt(worldHeight), this));
        isRefuelCollision = true;
    }

}
