package Helpers;

import java.io.IOException;

public interface Downloader {
	void downloadHtmlToFile(String url, String file) throws IOException;
}
