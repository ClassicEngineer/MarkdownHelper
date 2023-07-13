package ru.daniladeveloper.markdownhelper.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MarkdownHelperHomeController {

    @GetMapping("/")
    public String home() {
        return "index";
    }
}
