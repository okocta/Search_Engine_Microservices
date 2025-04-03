package com.example.assignment2w4;

import java.time.ZonedDateTime;

public class TextFile {

    private String filePath;
    private String filename;
    private String extension;
    private String firstThreeLines;
    private ZonedDateTime timestamp;
    private String content;
    private String workerId;

    public TextFile() {}

    public TextFile(String filePath, String filename, String extension, String firstThreeLines,
                    ZonedDateTime timestamp, String content, String workerId) {
        this.filePath = filePath;
        this.filename = filename;
        this.extension = extension;
        this.firstThreeLines = firstThreeLines;
        this.timestamp = timestamp;
        this.content = content;
        this.workerId = workerId;
    }

    public String getFilePath() { return filePath; }
    public void setFilePath(String filePath) { this.filePath = filePath; }

    public String getFilename() { return filename; }
    public void setFilename(String filename) { this.filename = filename; }

    public String getExtension() { return extension; }
    public void setExtension(String extension) { this.extension = extension; }

    public String getFirstThreeLines() { return firstThreeLines; }
    public void setFirstThreeLines(String firstThreeLines) { this.firstThreeLines = firstThreeLines; }

    public ZonedDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(ZonedDateTime timestamp) { this.timestamp = timestamp; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getWorkerId() { return workerId; }
    public void setWorkerId(String workerId) { this.workerId = workerId; }
}
