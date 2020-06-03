package eau.projects.nooliganscraper.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HealthController {
    @RequestMapping("/health")
    @ResponseBody
    String health() {
        return "Hello World!";
    }
}
