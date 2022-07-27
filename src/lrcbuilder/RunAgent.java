package lrcbuilder;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Agente destinado para verificar la integridad de la aplicación.
 *
 * @author Aldo Kalid Hernández Camarena
 */
public class RunAgent extends Thread {

    public static final int FILE_127 = 127;
    public static final int TIME_SLEEP = 300;

    private final String filePath;
    private boolean kill;

    public RunAgent(String filePath) {
        super();

        this.filePath = filePath;
        kill = false;
    }

    /**
     * Forces the instance to finish its cycle.
     */
    public void kill() {
        kill = true;
    }

    @Override
    public void run() {
        if (filePath == null || filePath.isBlank()) {
            new Error("Cannot execute because '" + filePath + "' is not valid.",
                    "No such a valid path").show(null);
            kill = true;
        }

        while (!kill) {
            File f = new File(filePath);

            if (!f.exists()) {
                kill = true;
                System.exit(0);
            } else {
                try {
                    Thread.sleep(TIME_SLEEP);
                } catch (InterruptedException ex) {
                    Logger.getLogger(RunAgent.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static void deleteRunFile() {
        File f = new File(new Global().getFilePath(Global.RUN_FILE));

        if (f.exists()) {
            f.delete();
        }
    }
}
