package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    // 👉 LOGIN PAGE
    @GetMapping("/")
    public String loginPage(HttpSession session) {

        return "index";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password) {

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        userService.register(user);

        return "redirect:/";
    }

    // 👉 LOGIN
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session) {

        User user = userService.login(username, password);

        if (user != null) {
            session.setAttribute("user", user);  // ✅ save session
            return "redirect:/home";
        } else {
            return "login";
        }
    }

    // 👉 LOGOUT
    @GetMapping("/logout")
    public String logout(HttpSession session) {

        session.invalidate();   // ✅ destroy session

        return "redirect:/";
    }
}