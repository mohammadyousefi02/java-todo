package com.example.todoo.services;

import com.example.todoo.domain.Todo;
import com.example.todoo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;

@Service
public class TodoService {
    private TodoRepository todoRepository;

    private ArrayList<Todo> todos;

    @Autowired
    public void TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public ArrayList<Todo> getTodos() throws SQLException {
        todos = todoRepository.getTodos();
        return todos;
    }

    public ArrayList<Todo> getUserTodos(Long id) {
        return todoRepository.getUserTodos(id);
    }

    public Long addTodo(String title, Long id) {
        return todoRepository.insertTodo(title, id);
    }

    public String updateTodo(String title, String status, Long id) {
        return todoRepository.updateTodo(title, status, id);
    }

    public String deleteTodo(Long id) {
        return todoRepository.deleteTodo(id);
    }

}
