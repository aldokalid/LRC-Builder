package lrcbuilder;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Global {

    public Global() {

    }

    public static final String APP_VER = "1.0";
    public static final int NO_INDEX = -1;
    public static final int NO_ID = 0;
    public static final int RUN_FILE = 0;
    public static final int SETTING_FILE = 1;
    public static final int DEFAULT_DELAY = 8;
    public static final String NO_FILE_DEFAULT_TEXT = "NOT LOADED";
    public static final String ICON_ADD = "/resources/icon/add.png";
    public static final String ICON_PAUSE = "/resources/icon/pause.png";
    public static final String ICON_PLAY = "/resources/icon/play.png";
    public static final String ICON_SET = "/resources/icon/set.png";
    public static final String ICON_STOP = "/resources/icon/stop.png";
    public static final String ICON_VOL_DOWN = "/resources/icon/vol_down.png";
    public static final String ICON_VOL_UP = "/resources/icon/vol_up.png";
    public static final String ICON_QUEST = "/resources/icon/quest.png";
    public static final String ICON_APP = "/resources/icon/icon_app.png";

    public static boolean isNumber(char value) {
        return value >= 48 && value <= 57;
    }

    public static boolean isNumber(String value) {
        if (value != null && !value.isBlank()) {
            for (int i = 0; i < value.length(); i++) {
                if (!isNumber(value.charAt(i))) {
                    return false;
                }
            }

            return true;
        }

        return false;
    }

    public static String getNextFile(String path, String extention) {
        String result = path;
        int count = 1;
        java.io.File f = new java.io.File(result);

        while (f.exists()) {
            result = path.substring(0, path.length() - 4);
            result = result.concat(" (" + count + ")" + extention);
            f = new java.io.File(result);
            count++;
        }

        return result;
    }

    public static String openFile(java.awt.Frame father, String extention, String format) {
        javax.swing.JFileChooser chooser = new javax.swing.JFileChooser();
        chooser.setCurrentDirectory(new java.io.File(System.getProperty("user.home")));
        chooser.setAcceptAllFileFilterUsed(false);

        chooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
            @Override
            public boolean accept(java.io.File file) {
                return file.getName().toLowerCase().endsWith(extention)
                        || file.isDirectory();
            }

            @Override
            public String getDescription() {
                return format;
            }
        });

        int response = chooser.showOpenDialog(father);

        if (response != javax.swing.JFileChooser.APPROVE_OPTION) {
            return null;
        }

        String fileDir = chooser.getSelectedFile() + "";
        fileDir = fileDir.concat(fileDir.substring(fileDir.length() - 4,
                fileDir.length()).equals(extention) ? "" : extention);

        // Checks if file exists.
        java.io.File f = new java.io.File(fileDir);

        if (!f.exists()) {
            return null;
        }

        return fileDir.replace('\\', '/');
    }

    public static String saveFile(java.awt.Frame padre, String extention, String format,
            String fileName) {
        javax.swing.JFileChooser chooser = new javax.swing.JFileChooser();
        chooser.setCurrentDirectory(new java.io.File(System.getProperty("user.home")));
        chooser.setAcceptAllFileFilterUsed(false);

        chooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
            @Override
            public boolean accept(java.io.File archivo) {
                return archivo.getName().toLowerCase().endsWith(extention)
                        || archivo.isDirectory();
            }

            @Override
            public String getDescription() {
                return format;
            }
        });

        chooser.setSelectedFile(new java.io.File(fileName));
        int response = chooser.showSaveDialog(null);

        if (response != javax.swing.JFileChooser.APPROVE_OPTION) {
            return null;
        }

        String fileDir = chooser.getSelectedFile() + "";
        fileDir = fileDir.concat(fileDir.substring(fileDir.length() - 4,
                fileDir.length()).equals(extention) ? "" : extention);
        java.io.File f = new java.io.File(fileDir);

        if (f.exists()) {
            int seleccion = JOptionPane.showOptionDialog(padre, "A file with the"
                    + " same name already exists in this directory. What do you "
                    + "want to do?", "Exportando...",
                    0, JOptionPane.QUESTION_MESSAGE, null, new Object[]{
                        "Rename it",
                        "Reeplace it",
                        "Cancel"
                    }, 0);

            switch (seleccion) {
                case 0: {
                    // Renombrar.
                    fileDir = Global.getNextFile(f.getPath(), extention);
                    break;
                }
                case 1: {
                    try {
                        // Reemplazar.
                        f.createNewFile();
                    } catch (IOException ex) {
                        Logger.getLogger(Global.class.getName()).log(Level.SEVERE, null, ex);
                        return null;
                    }
                    break;
                }
                default: {
                    return null;
                }
            }
        }

        return fileDir;
    }

    public static void sleep(long miliseconds) {
        try {
            Thread.sleep(miliseconds);
        } catch (InterruptedException ex) {
            Logger.getLogger(Global.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String hexToString(String cadena) {
        String resultado = cadena;

        if (!cadena.isBlank()) {
            resultado = resultado.replace("%c3%80", "Á");
            resultado = resultado.replace("%c3%89", "É");
            resultado = resultado.replace("%c3%8d", "Í");
            resultado = resultado.replace("%c3%93", "Ó");
            resultado = resultado.replace("%c3%9a", "Ú");
            resultado = resultado.replace("%c3%a1", "á");
            resultado = resultado.replace("%c3%a9", "é");
            resultado = resultado.replace("%c3%ad", "í");
            resultado = resultado.replace("%c3%b3", "ó");
            resultado = resultado.replace("%c3%ba", "ú");
            resultado = resultado.replace("%c3%91", "Ñ");
            resultado = resultado.replace("%c3%b1", "ñ");
            resultado = resultado.replace("%20", " ");
        }

        return resultado;
    }

    public String getFilePath(int file) {
        if (file != RUN_FILE && file != SETTING_FILE) {
            return "";
        }

        String resource = '/' + this.getClass().getName().replace(".", "/") + ".class";
        java.net.URL dir = this.getClass().getResource(resource);
        String sourcePath = dir.getPath();
        String[] s = sourcePath.replace("file:", "").replace("!" + resource, "").split("lrcbuilder/");
        s[0] = hexToString(s[0]);

        if (s.length == 1) {
            s = s[0].split("lrcbuilder.ja");
            return s[0].concat(file == RUN_FILE ? "run" : "setting.lb");
        } else {
            return s[0].concat("lrcbuilder/" + (file == RUN_FILE ? "run" : "setting.lb"));
        }
    }

    public static String[] getJTableHeader(javax.swing.JTable jTbl) {
        String[] result = null;

        if (jTbl != null && jTbl.getModel() != null && jTbl.getModel().getColumnCount() > 0) {
            result = new String[jTbl.getModel().getColumnCount()];

            for (int i = 0; i < jTbl.getModel().getColumnCount(); i++) {
                result[i] = jTbl.getModel().getColumnName(i);
            }
        }

        return result;
    }

    public static Time toTime(double mili) {
        Time time = new Time();
        double minu = 0, seco = 0, deci = 0;

        if (mili >= 10) {
            // First, converts miliseconds to seconds.
            seco = mili / 1000;
            // Separates decimal value.
            deci = (seco - (int) seco) * 1000;
            // removes decimal value from seconds.
            seco = (int) seco;

            // Checking if secons can be converted to minutes.
            if ((int) (seco / 60) > 0) {
                minu = (int) (seco / 60);
                seco = seco - minu * 60;
            }

            // Setting time.
            time.setMinutes(String.valueOf((int) minu));
            time.setSeconds(String.valueOf((int) seco));
            time.setMiliseconds(String.valueOf((int) deci));
        }

        return time;
    }
}
