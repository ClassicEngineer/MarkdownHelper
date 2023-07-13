package ru.daniladeveloper.markdownhelper.api.body;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CreateFromHtmlRequestBody {

    private String url;

}
