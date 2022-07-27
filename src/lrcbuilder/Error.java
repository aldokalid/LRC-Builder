package lrcbuilder;

import javax.swing.JOptionPane;

/**
 * Clase utilizada para mostrar mensajes de error, junto con rastreo de
 * registros.
 *
 * @author Aldo Kalid Hernández Camarena
 */
public class Error {

    private final String msg, ttl;

    public Error(String msg, String ttl) {
        this.msg = msg == null || msg.isBlank() ? "An unexpected error has occurred." : msg;
        this.ttl = ttl == null || ttl.isBlank() ? "Generic error." : ttl;
    }

    /**
     * Show an error message dialog (A {@code JOptionPane} instance).
     *
     * @param c Componente padre.
     */
    public void show(java.awt.Component c) {
        // Obteniendo el tipo de error.
        // Cargando botones.
        JOptionPane.showMessageDialog(c, msg, ttl, JOptionPane.ERROR_MESSAGE);
    }
}
