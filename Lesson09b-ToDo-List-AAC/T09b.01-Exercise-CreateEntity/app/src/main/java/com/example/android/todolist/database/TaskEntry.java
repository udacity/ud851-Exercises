package com.example.android.todolist.database;

import java.util.Date;

// TODO (2) Annotate the class with Entity. Use "task" for the table name
public class TaskEntry {

    // TODO (3) Annotate the id as PrimaryKey. Set autoGenerate to true.
    private int id;
    private String description;
    private int priority;
    private Date updatedAt;

    // TODO (4) Use the Ignore annotation so Room knows that it has to use the other constructor instead
    public TaskEntry(String description, int priority, Date updatedAt) {
        this.description = description;
        this.priority = priority;
        this.updatedAt = updatedAt;
    }

    public TaskEntry(int id, String description, int priority, Date updatedAt) {
        this.id = id;
        this.description = description;
        this.priority = priority;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
