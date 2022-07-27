package lrcbuilder;

import java.io.Serializable;

/**
 * Time class.
 *
 * @author Aldo Kalid Hernández Camarena.
 */
public class Time implements Serializable {

    private int id;
    private String min;
    private String sec;
    private String mil;

    public Time() {
        id = Global.NO_ID;
        min = "00";
        sec = "00";
        mil = "00";
    }

    public Time(String min, String sec, String mil) {
        this.min = min == null || !isValid(min) ? "00" : normalizeFactor(min);
        this.sec = sec == null || !isValid(sec) ? "00" : normalizeFactor(sec);
        this.mil = mil == null || !isValid(mil) ? "00" : normalizeFactor(mil);
    }

    public Time(String time) {
        id = Global.NO_ID;
        Time t = Time.splitTime(time);

        if (t == null) {
            min = "00";
            sec = "00";
            mil = "00";
        } else {
            min = t.getMinutes();
            sec = t.getSeconds();
            mil = t.getMiliseconds();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMinutes() {
        return min;
    }

    public void setMinutes(String min) {
        this.min = min != null && isValid(min) ? normalizeFactor(min) : this.min;
    }

    public String getSeconds() {
        return sec;
    }

    public void setSeconds(String sec) {
        this.sec = sec != null && isValid(sec) ? normalizeFactor(sec) : this.sec;
    }

    public String getMiliseconds() {
        return mil;
    }

    public void setMiliseconds(String mil) {
        this.mil = mil != null && isValid(mil) ? normalizeFactor(mil) : this.mil;
    }

    @Override
    public String toString() {
        String min = this.min.isEmpty() ? "00" : this.min;
        String sec = this.sec.isEmpty() ? "00" : this.sec;
        String mil = this.mil.isEmpty() ? "00" : this.mil;
        return min.concat(":").concat(sec).concat(".").concat(mil);
    }

    public static boolean isValid(String timer) {
        return timer != null && Global.isNumber(timer);
    }

    public static Time splitTime(String value) {
        // Checks if value is at '00:00.00' form.
        if (value == null || value.length() != 8 || value.charAt(2) != ':' || value.charAt(5) != '.') {
            return null;
        } else {
            String[] vals = value.split("\\:");
            String min = vals[0];
            vals = vals[1].split("\\.");
            String sec = vals[0];
            String mil = vals[1];

            if (!Global.isNumber(min) || !Global.isNumber(sec) || !Global.isNumber(mil)) {
                return null;
            }

            return new Time(min, sec, mil);
        }
    }

    public static String normalizeFactor(String factor) {
        return factor.length() == 1 ? "0".concat(factor) : (factor.length() > 2
                ? factor.substring(0, 2) : factor);
    }
}
