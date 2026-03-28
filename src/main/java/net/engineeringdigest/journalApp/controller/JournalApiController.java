package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.Service.JournalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/journal")
@CrossOrigin(origins = "*")
public class JournalApiController {

    @Autowired
    private JournalEntryService service;

    @GetMapping
    public List<JournalEntry> getAll() {
        return service.getAll();
    }

    @PostMapping
    public JournalEntry create(@RequestBody JournalEntry entry) {
        return service.saveEntry(entry);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}