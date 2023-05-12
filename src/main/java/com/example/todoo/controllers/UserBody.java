package com.example.todoo.controllers;

public class UserBody {
    public String name;
    public String email;

    public UserBody(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String toString() {
        return "n " + name + " e " + email;
    }
}
