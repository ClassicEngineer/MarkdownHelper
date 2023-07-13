package ru.daniladeveloper.markdownhelper.app;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import ru.daniladeveloper.markdownhelper.domain.creation.MarkdownCreatorService;
import ru.daniladeveloper.markdownhelper.domain.download.WebCrawler;
import ru.daniladeveloper.markdownhelper.domain.reformat.MarkdownNumericListReorderService;

@Log
@Service
@RequiredArgsConstructor
public class MarkdownHelperApplicationService {

    private final MarkdownNumericListReorderService reorderService;

    private final MarkdownCreatorService creatorService;


    public MarkdownFileContent createFromHtmlByUrl(String url) {
        HtmlDocument document = WebCrawler.getHtmlDocument(url);
        return creatorService.createFromHtml(document);
    }

    public String reorder(String content, boolean isFull) {
        if (isFull) {
            return reorderService.makeWholeReorder(content);
        }
        return reorderService.makeReorderByArticle(content);
    }
}
