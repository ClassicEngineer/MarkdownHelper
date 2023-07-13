package ru.daniladeveloper.markdownhelper.domain.download;

import lombok.extern.java.Log;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;
import ru.daniladeveloper.markdownhelper.app.HtmlDocument;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;

import static ru.daniladeveloper.markdownhelper.infrastructure.UrlUtils.asURL;
import static ru.daniladeveloper.markdownhelper.infrastructure.UrlUtils.getFullSource;

@Log
@Service
public class WebCrawler {

    private URL url;

    public WebCrawler() {
    }

    public WebCrawler(URL url) {
        this.url = url;
    }

    public WebCrawler(String url) throws MalformedURLException {
        this.url = asURL(url);
    }

    public static HtmlDocument getHtmlDocument(String url) {
    try {
        Document html = Jsoup.connect(url).get();
        return new HtmlDocument(html);
        } catch (IOException e) {
            log.log(Level.SEVERE, e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public  String downloadImage(String imageSrc, Path pathToSave) throws ImageDownloadException {
        try {
            URL src = getFullSource(imageSrc, url);
            return downloadImage(src, pathToSave);
        } catch (Exception e) {
            throw new ImageDownloadException(e);
        }
    }

    private static String downloadImage(URL src, Path pathToSave) throws IOException {
        try (InputStream in = src.openStream()) {
            String imageFile = Paths.get(src.getFile()).getFileName().toString();
            Files.copy(in, pathToSave.resolve(imageFile));
            return imageFile;
        }
    }

}
