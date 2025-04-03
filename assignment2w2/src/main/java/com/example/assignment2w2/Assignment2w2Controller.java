package com.example.assignment2w2;

import com.example.assignment2w2.TextFile;
import com.example.assignment2w2.WorkerCrawler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class Assignment2w2Controller {
    @Autowired
    private WorkerCrawler crawler;

    @GetMapping("/search")
    public List<TextFile> searchByFilename(@RequestParam String filename) {
        return crawler.getIndexedFiles().stream()
                .filter(f -> f.getFilename().toLowerCase().contains(filename.toLowerCase()))
                .toList();
    }

}
