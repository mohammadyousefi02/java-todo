package com.example.todoo.controllers;

import com.example.todoo.domain.Todo;
import com.example.todoo.exceptionHandlers.AppNotFoundExceptionHandler;
import com.example.todoo.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;


/**
 * this is the controller for todo requests.
 *
 * @author  Mamad Yousefi
 * @version 1.0
 * @since   2023-05-06
 */
@RestController
@RequestMapping("api/v1/todos")
public class TodoController {
    private TodoService todoService;

    @Autowired
    public void TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public ArrayList<Todo> getTodos() throws SQLException {
        return todoService.getTodos();
    }

    @GetMapping("/{id}")
    public ArrayList<Todo> getUserTodos(@PathVariable Long id) {
        return todoService.getUserTodos(id);
    }

    @PostMapping
    public String addTodo(@RequestBody TodoBody todoBody) {
        todoService.addTodo(todoBody.title, todoBody.id);
        return "success";
    }

    @PutMapping("/{id}")
    public String updateTodo(@PathVariable Long id, @RequestBody UpdateTodoBody updateTodoBody) {
        return todoService.updateTodo(updateTodoBody.title, updateTodoBody.status, id);
    }

    @GetMapping("/testexception")
    public String testException() {
        throw new AppNotFoundExceptionHandler();
    }

    @DeleteMapping("/{id}")
    public String deleteTodo(@PathVariable Long id) {
        return todoService.deleteTodo(id);
    }

}
