package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.Service.JournalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/api/journal")
@CrossOrigin(origins = {"http://localhost:8088", "http://localhost:3000", "http://localhost:8080"})
public class JournalApiController {

    @Autowired
    private JournalEntryService service;

    @GetMapping
    public List<JournalEntry> getAll(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            return service.getJournalEntriesByUser(user.getId());
        }
        return List.of();
    }

    @PostMapping
    public JournalEntry create(@RequestBody JournalEntry entry, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            entry.setUser(user);
        }
        return service.saveEntry(entry);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id, HttpSession session) {
        JournalEntry entry = service.getById(id);
        User user = (User) session.getAttribute("user");

        if (entry != null && user != null && entry.getUser() != null
                && entry.getUser().getId().equals(user.getId())) {
            service.delete(id);
            return "Deleted successfully";
        }
        return "Not authorized";
    }
}