package ru.daniladeveloper.markdownhelper.api;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.daniladeveloper.markdownhelper.api.body.MarkdownRequestBody;
import ru.daniladeveloper.markdownhelper.app.MarkdownHelperApplicationService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/markdown/transform/")
public class MarkdownHelperOrderController {

    private final MarkdownHelperApplicationService markdownService;

    @PostMapping("reorder")
    public String reorder(@RequestBody MarkdownRequestBody body) {
        return markdownService.reorder(body.getContent(), false);
    }
}
