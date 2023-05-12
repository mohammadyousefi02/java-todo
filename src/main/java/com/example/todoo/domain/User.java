package com.example.todoo.domain;


import com.example.todoo.base.domain.Entity;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class User extends Entity {
    private String name;
    private String email;

    public User() {
    }

    public User(Long id, String name, String email) {
        super(id);
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toString() {
        return "User{ " +
                "id='" + this.getId() + "', " +
                "name='" + getName() + "', " +
                "email='" + getEmail() + "'}";
    }
}
