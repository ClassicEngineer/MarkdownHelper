package ru.daniladeveloper.markdownhelper.domain;

import ru.daniladeveloper.markdownhelper.domain.reformat.TextLine;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;


public class Article extends ArrayList<TextLine> {

    public void add(String s) {
        super.add(new TextLine(s));
    }

    public List<Article> splitBy(Predicate<TextLine> condition) {
        List<Article> groupsOfLines = new ArrayList<>();
        Iterator<TextLine> lineIterator = this.iterator();
        Article article = new Article();
        while (lineIterator.hasNext()) {
            TextLine textLine = lineIterator.next();
            if (condition.test(textLine)) {
                groupsOfLines.add(article);
                article = new Article();
            }
            article.add(textLine);
        }
        groupsOfLines.add(article);
        return groupsOfLines;
    }


}
