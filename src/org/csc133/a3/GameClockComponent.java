package org.csc133.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Component;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.geom.Dimension;
import java.io.IOException;

/**
 * updates numerical values for in-game clock in glass cockpit
 */
public class GameClockComponent extends Component {
    Image[] digitImages = new Image[10];
    Image[] digitDotImages = new Image[10];
    Image colonImage;
    private long startTime;
    private int ledColor;
    private boolean isTenMinutes;
    private static int HM_COLON_IDX=2;
    private static int MS_COLON_IDX=2;
    private static final int numDigitsShowing=6;
    Image[] clockDigits = new Image[numDigitsShowing];

    public GameClockComponent() {

        startElapsedTime();
        // load images into array
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

            digitDotImages[0] = Image.createImage("/LED_digit_0_with_dot.png");
            digitDotImages[1] = Image.createImage("/LED_digit_1_with_dot.png");
            digitDotImages[2] = Image.createImage("/LED_digit_2_with_dot.png");
            digitDotImages[3] = Image.createImage("/LED_digit_3_with_dot.png");
            digitDotImages[4] = Image.createImage("/LED_digit_4_with_dot.png");
            digitDotImages[5] = Image.createImage("/LED_digit_5_with_dot.png");
            digitDotImages[6] = Image.createImage("/LED_digit_6_with_dot.png");
            digitDotImages[7] = Image.createImage("/LED_digit_7_with_dot.png");
            digitDotImages[8] = Image.createImage("/LED_digit_8_with_dot.png");
            digitDotImages[9] = Image.createImage("/LED_digit_9_with_dot.png");


            colonImage = Image.createImage("/LED_colon.png");

        } catch (IOException e) { e.printStackTrace(); }

        // Set all digits on the clock to 0 when the clock starts
        for(int i = 0; i < numDigitsShowing; i++) {
            clockDigits[i] = digitImages[0];
        }

        // add colons to the 2nd digit and 5th digit on the display
        //clockDigits[HM_COLON_IDX] = colonImage;
        clockDigits[MS_COLON_IDX] = colonImage;

        // set the clock display to CYAN color
        ledColor = ColorUtil.CYAN;

    }

    // If hour is 12, h/10 = 1, and h%10 = 2, giving us 12 in the hour digits
    private void setTime(int m, int s, int ms) {
       // int minutes = (int)(elapsedMilliseconds / 60000);
        //int seconds = (int)((elapsedMilliseconds % 60000) / 1000);
        //int tenthsOfSeconds = (int)((elapsedMilliseconds % 1000) / 100);

        clockDigits[0] = digitImages[m/10];
        clockDigits[1] = digitImages[m%10];
        clockDigits[3] = digitImages[s/10];
        clockDigits[4] = digitDotImages[s%10];
        clockDigits[5] = digitImages[ms%10];

        if(m == 10) { isTenMinutes = true; }
    }

    private void setCurrentTime() {
        /*
        Calendar rightNow = Calendar.getInstance();
        int hour = rightNow.get(Calendar.HOUR);
        setTime(0, 0, rightNow.get(Calendar.SECOND));
        */
        long time = getElapsedTime();
        double seconds = ((double)time/1000);
        int minutes = (int)((time/60000)%100);
        double tenths = (seconds*10)%10;

        setTime(minutes, (int)seconds%60, (int)tenths);
    }

    public boolean animate() {
        setCurrentTime();
        return true;
    }

    void resetElapsedTime() {
        startTime = System.currentTimeMillis();
    }

    void startElapsedTime() {
        startTime = System.currentTimeMillis();
    }

    public void stopElapsedTime() {
        startTime = 0;
    }

    public long getElapsedTime() {
        return System.currentTimeMillis() - startTime;
    }

    public void setLedColor(int ledColor) {
        this.ledColor = ledColor;
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


    // lets us set the size of the clock display
    //This gives us 8 digits of width, and the height of each image

    @Override
    protected Dimension calcPreferredSize() {
        return new Dimension(colonImage.getWidth() * numDigitsShowing, colonImage.getHeight());
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        final int COLOR_PAD = 1; // make sure the colored rectangle behind the LED doesn't bleed out
        int clockColor;

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

        // Clock will turn red after ten minutes of play time
        if(isTenMinutes) {
            clockColor = ColorUtil.rgb(150, 0, 0);
            ledColor = ColorUtil.rgb(255, 0, 0);
        }
        else {
            clockColor = ColorUtil.BLUE;
        }

        // paint a smaller rectangle on top of the background to color the digits. tenths digit is darker
        g.setColor(clockColor);
        g.fillRect(displayX+COLOR_PAD,
                displayY+COLOR_PAD,
                displayClockWidth - COLOR_PAD*2,
                displayDigitHeight - COLOR_PAD*2);


        g.setColor(ledColor);
        g.fillRect(displayX+COLOR_PAD,
                displayY+COLOR_PAD,
                displayDigitWidth*(numDigitsShowing-1),
                displayDigitHeight - COLOR_PAD*2);



        // lay all images on top of this colored rectangle
        for (int digitIndex = 0; digitIndex < numDigitsShowing; digitIndex++){
            g.drawImage(clockDigits[digitIndex], displayX + digitIndex * displayDigitWidth, displayY, displayDigitWidth, displayDigitHeight);
        }
    }


}
