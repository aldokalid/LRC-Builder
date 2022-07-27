package lrcbuilder;

/**
 * Lyric class.
 *
 * @author Aldo Kalid Hernández Camarena.
 */
public class Lyric {

    private Time time;
    private String lyric;

    public Lyric() {
        time = new Time();
        lyric = "";
    }

    public Lyric(Time time, String lyric) {
        this.time = time == null ? new Time() : time;
        this.lyric = lyric == null ? "" : lyric;
    }

    public Lyric(Lyric l) {
        if (l != null) {
            time = l.getTime();
            lyric = l.getLyric();
        } else {
            time = new Time();
            lyric = "";
        }
    }

    public String getLyric() {
        return lyric;
    }

    public void setLyric(String lyric) {
        this.lyric = lyric == null ? "" : lyric;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time == null ? new Time() : time;
    }

    @Override
    public String toString() {
        return "[" + time.toString() + "]" + lyric;
    }

    public Object[] toRow() {
        return new Object[]{time.toString(), lyric};
    }
}
