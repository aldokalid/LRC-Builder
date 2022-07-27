package lrcbuilder;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author aldok
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Extends tooltiptext delay (do not remove).
        javax.swing.ToolTipManager.sharedInstance().setDismissDelay(60000);
        
        // <editor-fold defaultstate="collapsed" desc="Starting settings">
        Setting s = new DAOIO<Setting>(new Global().getFilePath(Global.SETTING_FILE)).read();

        if (s == null) {
            s = new Setting();
            new DAOIO<Setting>(new Global().getFilePath(Global.SETTING_FILE)).write(s);
        }
        try {
            switch (s.getTheme()) {
                case Setting.THEME_LIGHT: {
                    javax.swing.UIManager.setLookAndFeel(new FlatLightLaf());
                    break;
                }
                case Setting.THEME_DARK: {
                    javax.swing.UIManager.setLookAndFeel(new FlatDarkLaf());
                    break;
                }
            }
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            new Error("An unexpected error has occurred. " + ex, "Exeption error.").show(null);
            return;
        }
        // </editor-fold>
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Checking program execution">
        java.io.File f = new java.io.File(new Global().getFilePath(Global.RUN_FILE));

        if (f.exists()) {
            // Another instance is executing
            int selection = JOptionPane.showOptionDialog(null, "Another instance"
                    + " is executin or last instance finished with an error.",
                    "Programa en ejecución.", 0, JOptionPane.WARNING_MESSAGE, null,
                    new Object[]{"Exit", "Force startup"}, 0);

            if (selection != 1) {
                // Closing program.
                System.exit(0);
            }

            // Closing instance.
            if (!f.delete()) {
                new Error("Couldn't force startup", "Exiting...").show(null);
                System.exit(-1);
            }

            // Waits a bit before initialize agent.
            Global.sleep(RunAgent.TIME_SLEEP * 3);
        }
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Run agent">
        try {
            f.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            new Error("An error has occurred while starting run agent up. " + ex.getMessage(),
                    "Exeption error").show(null);
            System.exit(-1);
        }

        RunAgent agenteE = new RunAgent(f.getPath());
        agenteE.start();
        // </editor-fold>

        new UI(s.getWindowState()).setVisible(true);
    }

}
