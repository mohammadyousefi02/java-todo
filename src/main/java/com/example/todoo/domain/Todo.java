package com.example.todoo.domain;

import com.example.todoo.base.domain.Entity;
import com.example.todoo.domain.enumeration.TodoStatus;

public class Todo extends Entity {
    private String title;

    private TodoStatus status;

    public Todo() {
    }

    public Todo(Long id, String title, TodoStatus status) {
        super(id);
        this.title = title;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TodoStatus getStatus() {
        return status;
    }

    public void setStatus(TodoStatus status) {
        this.status = status;
    }

    public String toString() {
        return "Todo{" +
                "id='" + getId() + '\'' +
                "title='" + title + '\'' +
                "status='" + String.valueOf(status).toLowerCase() + '\'' +
                "}";
    }
}
