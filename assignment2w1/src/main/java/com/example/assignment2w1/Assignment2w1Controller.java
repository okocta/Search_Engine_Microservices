package com.example.assignment2w1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class Assignment2w1Controller {
    @Autowired
    private WorkerCrawler crawler;

    @GetMapping("/search")
    public List<TextFile> search(@RequestParam String query) {
        String lowered = query.toLowerCase();

        return crawler.getIndexedFiles().stream()
                .filter(f -> f.getContent().toLowerCase().contains(lowered))
                .toList();
    }
}
