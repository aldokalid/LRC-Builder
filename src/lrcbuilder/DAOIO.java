package lrcbuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * DAO Object reader.
 *
 * @author Aldo Kalid Hernández Camarena.
 * @param <T> Template.
 */
public class DAOIO<T> {

    private final String filePath;
    private String error;

    public DAOIO(String filePath) {
        this.filePath = filePath;
        error = "";
    }

    @SuppressWarnings("unchecked")
    public T read() {
        T o = null;
        ObjectInputStream reader = null;

        try {
            reader = new ObjectInputStream(new FileInputStream(filePath));
            o = (T) reader.readObject();
            reader.close();
            reader = null;
        } catch (FileNotFoundException | ClassNotFoundException ex) {
            Logger.getLogger(DAOIO.class.getName()).log(Level.SEVERE, null, ex);
            error = ex.getMessage();
        } catch (IOException ex) {
            Logger.getLogger(DAOIO.class.getName()).log(Level.SEVERE, null, ex);
            error = ex.getMessage();
        }

        if (reader != null) {
            try {
                reader.close();
            } catch (IOException ex) {
                Logger.getLogger(DAOIO.class.getName()).log(Level.SEVERE, null, ex);
                error = ex.getMessage();
            }
        }

        return o;
    }

    public boolean write(T object) {
        ObjectOutputStream writer = null;
        boolean result = false;

        try {
            try {
                writer = new ObjectOutputStream(new FileOutputStream(filePath));
                writer.writeObject(object);
                writer.close();
                writer = null;
                result = true;
            } catch (FileNotFoundException ex) {
                File f = new java.io.File(filePath);
                f.createNewFile();
                error = ex.getMessage();
            }
        } catch (IOException ex) {
            Logger.getLogger(DAOIO.class.getName()).log(Level.SEVERE, null, ex);
            error = ex.getMessage();
        }

        if (!result) {
            try {
                writer = new ObjectOutputStream(new FileOutputStream(filePath));
                writer.writeObject(object);
                writer.close();
                writer = null;
                result = true;
            } catch (FileNotFoundException ex) {
                Logger.getLogger(DAOIO.class.getName()).log(Level.SEVERE, null, ex);
                error = ex.getMessage();
            } catch (IOException ex) {
                Logger.getLogger(DAOIO.class.getName()).log(Level.SEVERE, null, ex);
                error = ex.getMessage();
            }
        }

        if (writer != null) {
            try {
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(DAOIO.class.getName()).log(Level.SEVERE, null, ex);
                error = ex.getMessage();
            }
        }

        return result;
    }

    /**
     * Gets the last generated error message by an exception when a function
     * were activated.
     *
     * @return
     */
    public String getError() {
        return error;
    }
}
