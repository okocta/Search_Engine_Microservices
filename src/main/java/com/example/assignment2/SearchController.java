package com.example.assignment2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/files")
@CrossOrigin
public class SearchController {

    @Autowired
    private ManagerService manager;


    @GetMapping("/all")
    public List<TextFile> getAllFiles() {
        return manager.queryAllWorkers("");
    }

    @GetMapping("/search")
    public List<TextFile> search(@RequestParam(name = "filename") String fileName) {
        return manager.queryAllWorkers(fileName);
    }
}
