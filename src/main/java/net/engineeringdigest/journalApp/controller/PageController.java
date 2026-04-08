package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.Service.JournalEntryService;
import net.engineeringdigest.journalApp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class PageController {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping("/home")
    public String home(HttpSession session, Model model) {

        // ❌ Not logged in
        if (session.getAttribute("user") == null) {
            return "redirect:/";
        }

        User user = (User) session.getAttribute("user");
        List<JournalEntry> entries = journalEntryService.getJournalEntriesByUser(user.getId());
        model.addAttribute("entries", entries);

        return "index";   // index.jsp
    }

    @GetMapping("/profile")
    public String profile(HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/";
        }
        return "profile";
    }

    @PostMapping("/profile/update")
    public String updateProfile(@RequestParam String fullName,
                                @RequestParam String email,
                                @RequestParam String bio,
                                HttpSession session,
                                Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }

        try {
            User freshUser = userService.findById(user.getId());
            freshUser.setFullName(fullName);
            freshUser.setEmail(email);
            freshUser.setBio(bio);
            userService.updateUser(freshUser);
            session.setAttribute("user", freshUser);
            model.addAttribute("success", "Profile updated successfully!");
        } catch (Exception e) {
            model.addAttribute("error", "Failed to update profile: " + e.getMessage());
        }

        return "profile";
    }

    @PostMapping("/profile/password")
    public String changePassword(@RequestParam String currentPassword,
                                 @RequestParam String newPassword,
                                 @RequestParam String confirmPassword,
                                 HttpSession session,
                                 Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }

        if (!newPassword.equals(confirmPassword)) {
            model.addAttribute("error", "New passwords do not match!");
            return "profile";
        }

        try {
            User freshUser = userService.findById(user.getId());
            if (!freshUser.getPassword().equals(currentPassword)) {
                model.addAttribute("error", "Current password is incorrect!");
                return "profile";
            }

            freshUser.setPassword(newPassword);
            userService.updateUser(freshUser);
            model.addAttribute("success", "Password updated successfully!");
        } catch (Exception e) {
            model.addAttribute("error", "Failed to update password!");
        }

        return "profile";
    }

    @GetMapping("/settings")
    public String settings(HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/";
        }
        return "settings";
    }

    @PostMapping("/settings/theme")
    public String toggleTheme(@RequestParam(required = false) Boolean darkTheme,
                              HttpSession session,
                              Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }

        try {
            User freshUser = userService.findById(user.getId());
            freshUser.setDarkTheme(darkTheme != null && darkTheme);
            userService.updateUser(freshUser);
            session.setAttribute("user", freshUser);
        } catch (Exception e) {
            model.addAttribute("error", "Failed to update theme");
        }

        return "redirect:/settings";
    }

    @PostMapping("/settings/delete")
    public String deleteAccount(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }

        try {
            userService.deleteUser(user.getId());
            session.invalidate();
        } catch (Exception e) {
            // Handle error
        }

        return "redirect:/";
    }
}