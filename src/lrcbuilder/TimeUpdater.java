package lrcbuilder;

import javax.swing.JSlider;
import java.util.Timer;
import java.util.TimerTask;

/**
 * This agent will update the time JSlider while a song is playing.
 *
 * @author Aldo Kalid Hernández Camarena
 */
public class TimeUpdater {

    public static final int TIME_SKIP = 10; // Milliseconds
    private final JSlider slider;
    private Timer timer;
    private boolean running;

    public TimeUpdater(JSlider slider) {
        this.slider = slider;
        timer = new Timer();
        running = false;
    }

    public void startTimer() {
        TimerTask tT = new TimerTask() {
            @Override
            public void run() {
                if (slider != null && !slider.getValueIsAdjusting() && slider.getValue()
                        < slider.getMaximum()) {
                    slider.setValue(slider.getValue() + TIME_SKIP);
                } else {
                    stopTimer();
                }
            }
        };

        timer.scheduleAtFixedRate(tT, 0, TIME_SKIP);
        running = true;
    }

    public void stopTimer() {
        if (running) {
            running = false;
            timer.cancel();
            timer = new Timer();
        }
    }

    public boolean isRunning() {
        return running;
    }
}
