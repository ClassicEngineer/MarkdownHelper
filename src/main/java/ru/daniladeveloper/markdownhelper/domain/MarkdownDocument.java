package ru.daniladeveloper.markdownhelper.domain;

import lombok.Getter;
import ru.daniladeveloper.markdownhelper.app.MarkdownFileContent;
import ru.daniladeveloper.markdownhelper.domain.reformat.TextLine;

import java.util.ArrayList;
import java.util.List;

@Getter
public class MarkdownDocument  {

    private List<Article> content;
    private String name;

    public MarkdownDocument(List<Article> content) {
        this.content = content;
        this.name = "something.md";
    }

    public List<Article> getContent() {
        return content;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Article article : content) {
            for (TextLine line : article) {
                sb.append(line.getValue());
            }
        }
        return sb.toString();
    }

    public MarkdownFileContent asLines() {
        List<String> lines = new ArrayList<>();
        for (Article article : content) {
            for (TextLine line : article) {
                lines.add(line.getValue());
            }
        }
        return new MarkdownFileContent(lines, "smth.md");
    }
}
