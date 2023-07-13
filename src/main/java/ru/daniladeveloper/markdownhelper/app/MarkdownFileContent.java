package ru.daniladeveloper.markdownhelper.app;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MarkdownFileContent {

    private static final String DEFAULT_NAME = "default.md";

    private List<String> content;

    private String name;

}
