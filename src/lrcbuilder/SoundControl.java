package lrcbuilder;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Port;

/**
 *
 * @author aldok
 */
public class SoundControl {

    public static void setVolume(float vol) {
        float gain = vol < 0.0f ? 0.0f : (vol > 1.0f ? 1.0f : vol);
        changeVolume(gain, Port.Info.SPEAKER);
        changeVolume(gain, Port.Info.HEADPHONE);
    }

    public static void restoreVolume() {
        setVolume(1.0f);
    }

    private static void changeVolume(float vol, Port.Info source) {
        if (AudioSystem.isLineSupported(source)) {
            try {
                Port outline = (Port) AudioSystem.getLine(source);
                outline.open();
                FloatControl volControl = (FloatControl) outline.getControl(FloatControl.Type.VOLUME);
                volControl.setValue(vol);
            } catch (LineUnavailableException ex) {
                Logger.getLogger(SoundControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
