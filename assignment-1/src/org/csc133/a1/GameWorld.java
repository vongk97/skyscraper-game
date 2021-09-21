package org.csc133.a1;

import com.codename1.charts.util.ColorUtil;

import java.util.Random;
import java.util.Vector;

public class GameWorld {

    private Helicopter playerHelicopter;
    private int worldWidth = 1024;
    private int worldHeight = 768;
    private int lives = 3;
    private int clock = 0;
    private Vector<GameObject> gameObjects = new Vector<>(); // collection which aggregates objects of abstract type GameObject
    private Random r = new Random(); // for object location spawning
    private boolean isExitPrompt = false; // for exit confirmation
    private int skyscraperSize = 10;
    private int helicopterSize = 40;

    // initialize the game world and all game objects within it
    public void init() {
        gameObjects.add(new Skyscraper(skyscraperSize, 300, 300, 1));
        gameObjects.add(new Skyscraper(skyscraperSize, 400, 400, 2));
        gameObjects.add(new Skyscraper(skyscraperSize, 500, 500, 3));
        gameObjects.add(new Skyscraper(skyscraperSize, 600, 600, 4));
        gameObjects.add(playerHelicopter = new Helicopter(helicopterSize, 300, 300));
        gameObjects.add(new Bird(r.nextInt(21) + 10, r.nextInt(worldWidth), r.nextInt(worldHeight)));
        gameObjects.add(new Bird(r.nextInt(31) + 10, r.nextInt(worldWidth), r.nextInt(worldHeight)));
        gameObjects.add(new RefuelingBlimp(r.nextInt(51) + 10, r.nextInt(worldWidth), r.nextInt(worldHeight)));
        gameObjects.add(new RefuelingBlimp(r.nextInt(51) + 10, r.nextInt(worldWidth), r.nextInt(worldHeight)));
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

    public void heliCollision() {
        System.out.println("Collision with another helicopter has occurred");
        playerHelicopter.takeDamage(20);
        if(playerHelicopter.getDamageLevel() >= 100)
            updateLives();
    }

    public void birdCollision() {
        System.out.println("Bird has flown over");
        playerHelicopter.takeDamage(10);
        if(playerHelicopter.getDamageLevel() >= 100)
            updateLives();
    }

    public void updateLives() {
        lives--;
        // update location
        if (lives <= 0) {
            System.out.println("Game over, better luck next time!");
            System.exit(0);
        }
        else {
            System.out.println("You lost a life. You have " + lives + " lives remaining.");
            playerHelicopter.resetHelicopter();
            // Reposition helicopter at most recent checkpoint
            for (GameObject curObj : gameObjects) {
                if (curObj instanceof Skyscraper) {
                    if (playerHelicopter.getLastSkyScraperReached() == ((Skyscraper) curObj).getSequenceNumber()) {
                        playerHelicopter.setX(curObj.getX());
                        playerHelicopter.setY(curObj.getY());
                    }
                }
            }
        }
    }

    // Check for checkpoint update or win
    public void skyScraperCollision(int seqNum) {
        if (seqNum == playerHelicopter.getLastSkyScraperReached() + 1) {
            playerHelicopter.setLastSkyScraperReached(seqNum);
            System.out.println("Checkpoint reached at Sky Scraper # " + playerHelicopter.getLastSkyScraperReached());
        }
        if (playerHelicopter.getLastSkyScraperReached() == 4) {
            System.out.println("Game over, you win!");
            System.exit(0);
        }
    }

    // Search for the first non-empty refueling blimp in object collection to consume. Spawn a new blimp.
    public void blimpCollision() {
        System.out.println("Collision with a refueling blimp has occurred");
        for(GameObject curObj: gameObjects) {
            if (curObj instanceof RefuelingBlimp && ((RefuelingBlimp) curObj).getCapacity() != 0) {
                playerHelicopter.setFuelLevel(playerHelicopter.getFuelLevel() + ((RefuelingBlimp) curObj).getCapacity());
                ((RefuelingBlimp) curObj).setCapacity(0);
                curObj.setColor(ColorUtil.rgb(100, 255, 100)); // no type casting needed for parent class methods
                break; // a refueling blimp has been consumed. Stop searching through collection.
            }
        }
        gameObjects.add(new RefuelingBlimp(r.nextInt(51) + 10, r.nextInt(worldWidth), r.nextInt(worldHeight)));
    }

    // Update states for all MOVABLE objects
    public void clock_tick() {
        playerHelicopter.adjustHeading();
        playerHelicopter.setFuelLevel(playerHelicopter.getFuelLevel() - playerHelicopter.getFuelConsumptionRate());
        if (playerHelicopter.getFuelLevel() <= 0)
            updateLives();
        // move all MOVABLE object locations based on heading and speed
        for (GameObject curObj : gameObjects) {
            if (curObj instanceof MovableGO) {
                // Update bird headings by -5 to 5 degrees before moving
                if (curObj instanceof Bird)
                    ((Bird) curObj).adjustHeading(r.nextInt(10 + 1) - 5);
                ((MovableGO) curObj).move();
            }
        }
        System.out.println("Game clock has ticked");
        // Increase elapsed time by 1
        clock++;
    }

    public void display() {
        System.out.println("Lives Remaining: " + lives);
        System.out.println("Elapsed Time: " + clock);
        System.out.println("Last Skyscraper Reached: " + playerHelicopter.getLastSkyScraperReached());
        System.out.println("Helicopter Fuel Level: " + playerHelicopter.getFuelLevel());
        System.out.println("Helicopter Damage Level: " + playerHelicopter.getDamageLevel());
    }

    //
    public void map() {
        for (GameObject curObj : gameObjects) {
            System.out.println(curObj.toString());
        }
    }

    public void exitPrompt() {
        System.out.println("Are you sure you want to exit? Enter 'y' or 'n'");
        isExitPrompt = true;
    }
    public void confirmGameExit() {
        if (isExitPrompt) {
            System.out.println("Terminating game.");
            System.exit(0);
        }
    }
    public void denyGameExit() {
        if (isExitPrompt) {
            System.out.println("Continuing game.");
            isExitPrompt = false;
        }
    }
}
