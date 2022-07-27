package lrcbuilder;

import java.io.Serializable;

/**
 * Setting class
 *
 * @author Aldo Kalid Hernández Camarena.
 */
public class Setting implements Serializable {

    public static final int THEME_LIGHT = 0;
    public static final int THEME_DARK = 1;
    public static final int WINDOW_NORMAL = javax.swing.JFrame.NORMAL;
    public static final int WINDOW_MAXED = javax.swing.JFrame.MAXIMIZED_BOTH;
    public static final String DEFAULT_DELAY = "00:00.00";
    public static final boolean SKYP_E_LINES_DEFAULT = true;
    public static final boolean SAVE_TAGS_DEFAULT = true;

    private int theme;
    private int windowState;
    private Time delay;
    private boolean addEmptyLines;
    private boolean saveTags;

    public Setting() {
        super();
        theme = THEME_LIGHT;
        windowState = WINDOW_NORMAL;
        delay = Time.splitTime(DEFAULT_DELAY);
        addEmptyLines = SKYP_E_LINES_DEFAULT;
        saveTags = SAVE_TAGS_DEFAULT;
    }

    public Setting(int theme, int windowState, String delay, boolean skypELines,
            boolean saveTags) {
        this.theme = theme < Global.NO_ID || theme > THEME_DARK ? THEME_LIGHT : theme;
        this.windowState = windowState != WINDOW_NORMAL || windowState != WINDOW_MAXED ? WINDOW_NORMAL : windowState;
        this.delay = Time.splitTime(delay);
        this.delay = delay == null ? Time.splitTime(DEFAULT_DELAY) : this.delay;
        this.addEmptyLines = skypELines;
        this.saveTags = saveTags;
    }

    public Setting(Setting s) {
        if (s != null) {
            theme = s.getTheme();
            windowState = s.getWindowState();
            delay = s.getDelay();
            addEmptyLines = s.getAddEmptyLines();
            saveTags = s.getSaveTags();
        } else {
            theme = THEME_LIGHT;
            windowState = WINDOW_NORMAL;
            delay = Time.splitTime(DEFAULT_DELAY);
            addEmptyLines = SKYP_E_LINES_DEFAULT;
            saveTags = SAVE_TAGS_DEFAULT;
        }
    }

    public int getTheme() {
        return theme;
    }

    public void setTheme(int theme) {
        this.theme = theme < Global.NO_ID || theme > THEME_DARK ? THEME_LIGHT : theme;
    }

    public int getWindowState() {
        return windowState;
    }

    public void setWindowState(int windowState) {
        this.windowState = windowState != WINDOW_NORMAL && windowState != WINDOW_MAXED ? WINDOW_NORMAL : windowState;
    }

    public Time getDelay() {
        return delay;
    }

    public void setDelay(Time delay) {
        this.delay = delay == null ? Time.splitTime(DEFAULT_DELAY) : delay;
    }

    public boolean getAddEmptyLines() {
        return addEmptyLines;
    }

    public void setAddEmptyLines(boolean skypELines) {
        this.addEmptyLines = skypELines;
    }

    public boolean getSaveTags() {
        return saveTags;
    }

    public void setSaveTags(boolean saveTags) {
        this.saveTags = saveTags;
    }
}
