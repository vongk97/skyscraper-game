package org.csc133.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Component;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.geom.Dimension;

import java.io.IOException;

/**
 * updates numerical values for damage in glass cockpit
 */
public class DamageDisplayComponent extends Component {
    private GameWorld gw;
    Image[] digitImages = new Image[10];
    Image[] digitDotImages = new Image[10];
    private static final int numDigitsShowing=2;
    Image[] clockDigits = new Image[numDigitsShowing];
    private int ledColor;
    private boolean isMediumDamage;
    private boolean isHighDamage;
    // load images into array

    /**
     * @param gw
     */
    public DamageDisplayComponent(GameWorld gw) {
        this.gw = gw;
        try {
            digitImages[0] = Image.createImage("/LED_digit_0.png");
            digitImages[1] = Image.createImage("/LED_digit_1.png");
            digitImages[2] = Image.createImage("/LED_digit_2.png");
            digitImages[3] = Image.createImage("/LED_digit_3.png");
            digitImages[4] = Image.createImage("/LED_digit_4.png");
            digitImages[5] = Image.createImage("/LED_digit_5.png");
            digitImages[6] = Image.createImage("/LED_digit_6.png");
            digitImages[7] = Image.createImage("/LED_digit_7.png");
            digitImages[8] = Image.createImage("/LED_digit_8.png");
            digitImages[9] = Image.createImage("/LED_digit_9.png");
        } catch ( IOException e) { e.printStackTrace(); }

        for(GameObject curObj : gw.gameObjectCollection) {
            if(curObj instanceof PlayerHelicopter) {
                clockDigits[0] = digitImages[((PlayerHelicopter) curObj).getDamageLevel() / 10];
                clockDigits[1] = digitImages[((PlayerHelicopter) curObj).getDamageLevel() % 10];
            }
        }

        ledColor = ColorUtil.GREEN;
    }

    public void setLedColor(int ledColor) {
        this.ledColor = ledColor;
    }

    public boolean animate() {
        for(GameObject curObj : gw.gameObjectCollection) {
            if(curObj instanceof PlayerHelicopter) {
                clockDigits[0] = digitImages[((PlayerHelicopter) curObj).getDamageLevel() / 10];
                clockDigits[1] = digitImages[((PlayerHelicopter) curObj).getDamageLevel() % 10];
                if(((PlayerHelicopter) curObj).getDamageLevel() >= 85) {
                    isHighDamage = true;
                    isMediumDamage = false;
                }
                else if(((PlayerHelicopter) curObj).getDamageLevel() >= 50) {
                    isMediumDamage = true;
                    isHighDamage = false;
                }
                else {
                    isHighDamage = false;
                    isMediumDamage = false;
                }
            }
        }
        return true;
    }

    public void start() {
        getComponentForm().registerAnimated(this);
    }

    public void stop() {
        getComponentForm().deregisterAnimated(this);
    }

    // After we placed the component into our form, we can call start
    public void laidOut() {
        this.start();
    }


    protected Dimension calcPreferredSize() {
        return new Dimension(digitImages[0].getWidth() * numDigitsShowing, digitImages[0].getHeight());
    }

    public void paint(Graphics g) {
        super.paint(g);
        final int COLOR_PAD = 1; // make sure the colored rectangle behind the LED doesn't bleed out

        // establish the width and height of each image by referencing the image we put in our array
        int digitWidth = clockDigits[0].getWidth();
        int digitHeight = clockDigits[0].getHeight();
        int clockWidth = numDigitsShowing * digitWidth;

        // preserves aspect ratio of digits to guarantee that our component fits into a given space
        float scaleFactor = Math.min(
                getInnerHeight() / (float) digitHeight,
                getInnerWidth() / (float) clockWidth);

        // formatted display depending on scale factor
        int displayDigitWidth = (int)(scaleFactor*digitWidth);
        int displayDigitHeight = (int)(scaleFactor*digitHeight);
        int displayClockWidth = displayDigitWidth*numDigitsShowing;

        // used for placing our clock at a location
        int displayX = getX() + (getWidth() - displayClockWidth)/2;
        int displayY = getY() + (getHeight() - displayDigitHeight)/2;

        // paints a black rectangle behind our component as a background
        g.setColor(ColorUtil.BLACK);
        g.fillRect(getX(), getY(), getWidth(), getHeight());
        if(isHighDamage)
            ledColor = ColorUtil.rgb(255, 0, 0);
        else if(isMediumDamage)
            ledColor = ColorUtil.rgb(255, 255, 0);
        else
            ledColor = ColorUtil.GREEN;
        // paint a smaller rectangle on top of the background to color the digits
        g.setColor(ledColor);
        g.fillRect(displayX+COLOR_PAD,
                displayY+COLOR_PAD,
                displayClockWidth - COLOR_PAD*2,
                displayDigitHeight - COLOR_PAD*2);

        // lay all images on top of this colored rectangle
        for (int digitIndex = 0; digitIndex < numDigitsShowing; digitIndex++){
            g.drawImage(clockDigits[digitIndex], displayX + digitIndex * displayDigitWidth, displayY, displayDigitWidth, displayDigitHeight);
        }
    }
}
