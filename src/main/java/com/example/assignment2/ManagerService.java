package com.example.assignment2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ManagerService {

    @Autowired
    private RestTemplate restTemplate;

    private final List<String> workerUrls = List.of(
            "http://localhost:3001/api/search",
            "http://localhost:3002/api/search",
            "http://localhost:3003/api/search",
            "http://localhost:3004/api/search",
            "http://localhost:3005/api/search"

    );

    public List<TextFile> queryAllWorkers(String filename) {
        List<TextFile> results = new ArrayList<>();

        for (String url : workerUrls) {
            try {
                String fullUrl = url + "?filename=" + filename;
                TextFile[] response = restTemplate.getForObject(fullUrl, TextFile[].class);

                if (response != null) {
                    results.addAll(List.of(response));
                }
            } catch (Exception e) {
                System.err.println("Worker call failed: " + url);
            }
        }

        return results;
    }


}
