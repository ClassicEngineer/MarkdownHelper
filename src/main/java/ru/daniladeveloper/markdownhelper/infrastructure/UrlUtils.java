package ru.daniladeveloper.markdownhelper.infrastructure;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public final class UrlUtils {

    public static URL asURL(String url) throws MalformedURLException {
        return URI.create(url).toURL();
    }

    public static URL getFullSource(String src, URL url) throws MalformedURLException, URISyntaxException {
        URI imageURI = URI.create(src);
        if (!imageURI.isAbsolute()) {
            if (imageURI.toString().startsWith("//")) {
                imageURI = URI.create(url.getProtocol() + ":" + imageURI);
            } else {
                imageURI = url.toURI().resolve(imageURI);
            }
        }
        return imageURI.toURL();
    }

    public static boolean isRelative(String href) {
        return href.startsWith("/");
    }
}
