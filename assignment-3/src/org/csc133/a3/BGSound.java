package org.csc133.a3;

import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;

import java.io.InputStream;

/*This class creates a Media object which loops while playing the sound
*/
public class BGSound implements Runnable{
    private Media m;

    public BGSound(String fileName) {
        try {
            InputStream is = Display.getInstance().getResourceAsStream(getClass(), "/" + fileName);

            m = MediaManager.createMedia(is, "audio/wav", this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        public void pause () {
            m.pause();
        }
        public void play () {
            m.play();
        }

        //entered when media has finished playing
        public void run () {
            m.setTime(0);
            m.play();
        }
}
