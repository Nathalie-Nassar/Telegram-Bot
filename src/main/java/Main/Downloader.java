package Main;
import java.io.*;
import java.nio.channels.Channels;
import java.net.URL;

public class Downloader implements Helpers.Downloader{
    // requires: a String URL file, that is the url of the aub dynammic schedule
    // effects: downloads the html file into the "String file"
    @Override
    public void downloadHtmlToFile(String url, String file) throws IOException {
        new FileOutputStream(file).getChannel().transferFrom(Channels.newChannel(new URL(url).openStream()) , 0 , Long.MAX_VALUE);
    }
}
