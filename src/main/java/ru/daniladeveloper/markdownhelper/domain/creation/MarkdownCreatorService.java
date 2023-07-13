package ru.daniladeveloper.markdownhelper.domain.creation;

import org.springframework.stereotype.Service;
import ru.daniladeveloper.markdownhelper.app.MarkdownFileContent;
import ru.daniladeveloper.markdownhelper.app.HtmlDocument;
import ru.daniladeveloper.markdownhelper.domain.MarkdownDocument;

@Service
public class MarkdownCreatorService {
    public MarkdownFileContent createFromHtml(HtmlDocument document) {
        MarkdownDocument md = document.convertToMarkdown();
        return md.asLines();
    }
}
