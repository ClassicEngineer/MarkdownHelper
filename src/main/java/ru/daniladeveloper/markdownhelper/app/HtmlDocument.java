package ru.daniladeveloper.markdownhelper.app;

import lombok.Getter;
import lombok.extern.java.Log;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import ru.daniladeveloper.markdownhelper.domain.download.HtmlTag;
import ru.daniladeveloper.markdownhelper.domain.Article;
import ru.daniladeveloper.markdownhelper.domain.MarkdownDocument;
import ru.daniladeveloper.markdownhelper.infrastructure.UrlUtils;

import java.util.List;

@Getter
@Log
public class HtmlDocument {

    private Document document;

    private String url;
    public HtmlDocument(Document document) {
        this.document = document;
        this.url = document.baseUri();
    }

    public MarkdownDocument convertToMarkdown() {
        Article article = new Article();

        for (Element element : document.getAllElements()) {
            String tagName = element.tagName();
            if (tagName.equals("footer")) {
                break;
            } else if (tagName.equals("head") || tagName.equals("nav")) {
                continue;
            }

            if (HtmlTag.isAppropriateTag(tagName)) {
                HtmlTag tag = HtmlTag.valueOf(tagName.toUpperCase());
                switch (tag) {
                    case A -> article.add(parseAElement(element));
                    case IMG -> article.add(parseImgElement(element));
                    case P -> article.add(parseParagraphElement(element));
                    case H1, H2, H3, H4, H5, H6 -> article.add(parseHeaderElement(element));
                    case LI -> article.add(parseListElement(element));
                    case SPAN -> article.add(parseSpanElement(element));
                    case PRE -> article.add(parsePreElement(element));
                    case CODE -> article.add(parseCodeElement(element));
                }
            }
        }
        return new MarkdownDocument(List.of(article));
    }

    private String parsePreElement(Element element) {
        return element.ownText();
    }

    private String parseCodeElement(Element element) {
        return "```\n" + element.ownText() + "\n```";
    }

    private String parseSpanElement(Element element) {
        return element.ownText();
    }

    private String parseListElement(Element element) {
        return "- " + element.ownText();
    }

    private String parseHeaderElement(Element element) {
        String header = element.ownText();
        return switch (element.tagName()) {
            case "h1" -> "# " + header;
            case "h2" -> "## " + header;
            case "h3" -> "### " + header;
            case "h4" -> "#### " + header;
            case "h5" -> "##### " + header;
            default -> header;
        };
    }

    private String parseParagraphElement(Element element) {
        return element.ownText();
    }

    private String parseImgElement(Element element) {
        String src = element.attr("src");
        if (UrlUtils.isRelative(src)) {
            src = url + src;
        }
        return "![" + element.attr("alt") + "]" + "(" + src + ")";
    }

    // Example IN: <a href="https://kubernetes.io/docs/setup/minikube/">Minikube</a>
    // OUT (Minikube)[https://kubernetes.io/docs/setup/minikube/]
    private String parseAElement(Element element) {
        String href = element.attr("href");
        if (UrlUtils.isRelative(href)) {
            href = url + href;
        }
        return "[" + element.ownText() + "]" + "(" + href + ")";
    }
}
