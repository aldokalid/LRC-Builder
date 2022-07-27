package lrcbuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Clase para la creación de hojas de cálculo CSV.
 *
 * @author Aldo Kalid Hernández Camarena
 */
public class LRCArchive {

    private final File file;
    private FileWriter writer;

    public LRCArchive(File file) {
        this.file = file;
        writer = null;
    }

    /**
     * Closes and saves the document.
     *
     * @throws IOException
     */
    public void close() throws IOException {
        if (isOpen()) {
            writer.close();
            writer = null;
        }
    }

    /**
     * Checks if the document is open.
     *
     * @return
     */
    public boolean isOpen() {
        return writer != null;
    }

    /**
     * Opens the document.
     *
     * @throws IOException
     */
    public void open() throws IOException {
        writer = new FileWriter(file);
    }

    /**
     * Writes a line with no format. Jumps to the next line after writting.
     *
     * @param line String to be written
     * @throws IOException
     */
    public void writeLine(String line) throws IOException {
        if (!isOpen()) {
            return;
        }

        writer.append(line.concat("\n"));
        writer.flush();
    }

    /**
     * Writes a lyric. Jumps to the next line after writting.
     *
     * @param lyric Item that will be written.
     * @throws IOException
     */
    public void writeLyric(Lyric lyric) throws IOException {
        if (!isOpen()) {
            return;
        }

        writer.append(lyric.toString().concat("\n"));
        writer.flush();
    }
}
