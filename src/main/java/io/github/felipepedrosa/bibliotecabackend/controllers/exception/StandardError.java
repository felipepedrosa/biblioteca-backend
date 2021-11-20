package io.github.felipepedrosa.bibliotecabackend.controllers.exception;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

public class StandardError implements Serializable {
    private Instant timestamp;
    private Integer httpStatus;
    private String path;
    private String description;
    private List<String> messages;

    public StandardError() {
    }

    public StandardError(Integer httpStatus, String path, String description, List<String> messages) {
        this.timestamp = Instant.now();
        this.httpStatus = httpStatus;
        this.path = path;
        this.description = description;
        this.messages = messages;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(Integer httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
}
