package lrcbuilder;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/**
 * Media player.
 *
 * @author Aldo Kalid Hernández Camarena.
 */
public class SoundPlayer {

    public static final int DELAY_ADITION = 20000;

    private String path;
    private FileInputStream fIS;
    private BufferedInputStream bIS;
    private Player player;
    private int stopLocation, songSize;
    private boolean playing;

    public SoundPlayer() {
        super();
        path = "";
        fIS = null;
        bIS = null;
        player = null;
        stopLocation = 0;
        songSize = 0;
        playing = false;
    }

    public String getPath() {
        return path;
    }

    public int getStopLocation() {
        return stopLocation;
    }

    public int getSongSize() {
        return songSize;
    }

    public int getSongRemaining() {
        if (isPlaying()) {
            try {
                return fIS.available() - DELAY_ADITION;
            } catch (IOException ex) {
                Logger.getLogger(SoundPlayer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return 0;
    }

    public void setStopLocation(int stopLocation) {
        this.stopLocation = stopLocation;
    }

    public void stop() {
        if (isOpen() && player != null) {
            player.close();
            player = null;
            stopLocation = 0;

            try {
                bIS.close();
                fIS.close();
            } catch (IOException ex) {
                Logger.getLogger(SoundPlayer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void pause() {
        if (isOpen() && player != null) {
            try {
                stopLocation = songSize - fIS.available() - DELAY_ADITION;
                player.close();
                player = null;
                bIS.close();
                fIS.close();
            } catch (IOException ex) {
                Logger.getLogger(SoundPlayer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void play(int position) {
        if (isOpen()) {
            while (isPlaying()) {
                // Sleeps to avoid delays.
                Global.sleep(20);
            }

            try {
                fIS = new FileInputStream(path);
                bIS = new BufferedInputStream(fIS);
                fIS.skip(position > 0 && position < songSize ? position
                        : (position > songSize ? songSize : 0));
                player = new Player(bIS);
                startThread();
            } catch (JavaLayerException | IOException ex) {
                Logger.getLogger(SoundPlayer.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            if (!isOpen()) {
                System.out.println("It's not open");
            } else if (isPlaying()) {
                System.out.println("Still playing");
            }
        }
    }

    private void startThread() {
        if (isOpen()) {
            new Thread() {
                @Override
                public void run() {
                    playing = true;

                    try {
                        player.play();
                    } catch (JavaLayerException ex) {
                        Logger.getLogger(SoundPlayer.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    playing = false;
                }
            }.start();
        }
    }

    public void close() {
        if (isOpen()) {
            stop();
            path = "";
        }
    }

    public void open(String path) {
        close();
        java.io.File f = new java.io.File(path);

        if (f.exists()) {
            this.path = path;

            try {
                fIS = new FileInputStream(path);
                songSize = fIS.available();
                fIS.close();
            } catch (IOException ex) {
                Logger.getLogger(SoundPlayer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean isOpen() {
        return !path.isEmpty();
    }

    public boolean isPlaying() {
        return playing;
    }
}
