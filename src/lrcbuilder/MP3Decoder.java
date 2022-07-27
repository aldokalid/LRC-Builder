package lrcbuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Its function is to get MP3 files metadata.
 *
 * @author Aldo Kalid Hernández Camarena.
 */
public class MP3Decoder {

    private final String path;
    private String name;
    private String album;
    private String artist;
    private double duration;

    public MP3Decoder(String path) {
        this.path = path;
        name = "";
        album = "";
        artist = "";
        duration = 0;
    }

    public void decode() {
        try {
            InputStream input = new FileInputStream(new File(path));

            ContentHandler handler = new DefaultHandler();
            Metadata metadata = new Metadata();
            Parser parser = new Mp3Parser();
            ParseContext parseCtx = new ParseContext();
            parser.parse(input, handler, metadata, parseCtx);
            input.close();

            // List all metadata
            /*String[] metadataNames = metadata.names();

            for (String name : metadataNames) {
                System.out.println(name + ": " + metadata.get(name));
            }*/
            // Retrieve the necessary info from metadata
            name = metadata.get("dc:title");
            album = metadata.get("xmpDM:album");
            artist = metadata.get("xmpDM:artist");
            duration = Double.parseDouble(metadata.get("xmpDM:duration"));
            duration = duration * 1000;
        } catch (IOException | SAXException | TikaException ex) {
            Logger.getLogger(MP3Decoder.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String getName() {
        return name;
    }

    public String getAlbum() {
        return album;
    }

    public String getArtist() {
        return artist;
    }

    public double getDuration() {
        return duration;
    }
}
