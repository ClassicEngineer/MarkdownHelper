package ru.daniladeveloper.markdownhelper.domain.download;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public enum HtmlTag {
    A,
    IMG,
    P,
    SPAN,
    LI,
    PRE,
    CODE,
    H1,
    H2,
    H3,
    H4,
    H5,
    H6;

    private static final Set<String> names = Arrays.stream(HtmlTag.values()).map(tag -> tag.name().toLowerCase()).collect(Collectors.toSet());

    public static boolean isAppropriateTag(String tag) {
        return names.contains(tag);
    }
}
