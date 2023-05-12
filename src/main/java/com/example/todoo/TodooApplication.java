package com.example.todoo;

import com.example.todoo.controllers.UserController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TodooApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodooApplication.class, args);
	}

}
