package ru.daniladeveloper.markdownhelper.api;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.daniladeveloper.markdownhelper.api.body.CreateFromHtmlRequestBody;
import ru.daniladeveloper.markdownhelper.app.MarkdownHelperApplicationService;

@RestController
@RequestMapping("/api/v1/markdown/create")
@RequiredArgsConstructor
public class MarkdownCreatorRestController {

    private final MarkdownHelperApplicationService applicationService;
    @PostMapping("/fromHtml")
    public ResponseEntity<?> createFromHtmlByUrl(@RequestBody CreateFromHtmlRequestBody body) {
        var result = applicationService.createFromHtmlByUrl(body.getUrl());
        return ResponseEntity.ok(result);
    }
}
