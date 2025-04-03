package com.example.assignment2w1;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class WorkerCrawler {

    @Value("${worker.folder.path}")
    private String folderPath;

    private final List<TextFile> indexedFiles = new ArrayList<>();

    private int totalFilesProcessed = 0;
    private int totalFilesSkipped = 0;
    private int totalErrors = 0;

    public List<TextFile> getIndexedFiles() {
        return indexedFiles;
    }

    @PostConstruct
    public void crawlAndStore() {
        indexedFiles.clear();

        try {
            Files.walkFileTree(Paths.get(folderPath).toAbsolutePath(), new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                    if (file.toString().endsWith(".txt")) {
                        try {
                            String content = Files.readString(file);
                            String firstThreeLines = content.lines().limit(3).reduce("", (a, b) -> a + "\n" + b);
                            ZonedDateTime modified = ZonedDateTime.ofInstant(
                                    Files.getLastModifiedTime(file).toInstant(),
                                    java.time.ZoneId.systemDefault()
                            );

                            indexedFiles.add(new TextFile(
                                    file.toAbsolutePath().toString(),
                                    file.getFileName().toString(),
                                    "txt",
                                    firstThreeLines.trim(),
                                    modified,
                                    content,
                                    "worker-1"
                            ));
                            totalFilesProcessed++;

                        } catch (IOException e) {
                            totalErrors++;
                            System.err.println("Error reading file: " + file + " - " + e.getMessage());
                        }
                    } else {
                        totalFilesSkipped++;
                    }
                    return FileVisitResult.CONTINUE;
                }
            });

            generateReport();

        } catch (IOException e) {
            totalErrors++;
            e.printStackTrace();
        }
    }


    private void generateReport() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("index_report_worker.txt"))) {
            writer.write("Indexing Summary\n");
            writer.write("Total Files Processed: " + totalFilesProcessed + "\n");
            writer.write("Total Files Skipped: " + totalFilesSkipped + "\n");
            writer.write("Total Errors: " + totalErrors + "\n");
            System.out.println("📄 Indexing report generated: index_report_worker.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
