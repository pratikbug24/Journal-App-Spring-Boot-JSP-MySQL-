package net.engineeringdigest.journalApp.Service;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository repository;

    public JournalEntry saveEntry(JournalEntry entry) {
        entry.setDate(LocalDateTime.now());
        return repository.save(entry);
    }

    public List<JournalEntry> getAll() {
        return repository.findAll();
    }

    public JournalEntry getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}