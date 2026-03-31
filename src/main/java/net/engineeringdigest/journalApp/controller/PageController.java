package net.engineeringdigest.journalApp.controller;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/home")
    public String home(HttpSession session) {

        // ❌ Not logged in
        if (session.getAttribute("user") == null) {
            return "redirect:/";
        }

        return "index";   // index.jsp
    }
}