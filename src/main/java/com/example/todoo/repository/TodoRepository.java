package com.example.todoo.repository;

import com.example.todoo.domain.Todo;
import com.example.todoo.domain.enumeration.TodoStatus;
import com.example.todoo.exceptionHandlers.AppNotFoundExceptionHandler;
import com.example.todoo.util.ConnectToDb;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
public class TodoRepository {
    private static final String GET_ALL_TODOS = "SELECT * FROM todo;";
    private static final String GET_TODO_BY_ID = "SELECT * FROM todo WHERE todo_id = ?;";

    private static final String GET_TODO_BY_USER_ID = "select * from todo where user_id = ?";
    private static final String INSERT_TODO = "INSERT INTO TODO(title, status, user_id ) VALUES(?, 'todo', ?) RETURNING todo_id;";
    private static final String UPDATE_TODO = "UPDATE todo SET title = ?, status = ? WHERE todo_id = ? RETURNING todo_id;";
    private static final String DELETE_TODO = "DELETE FROM todo WHERE todo_id = ? RETURNING todo_id;";

    private Connection connection;

    public TodoRepository() {
        this.connection = ConnectToDb.getConnection();
    }

    public ArrayList<Todo> getTodos() {
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(GET_ALL_TODOS);
            ResultSet resultSet = preparedStatement.executeQuery();
            return getTodos(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Todo> getUserTodos(Long userId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_TODO_BY_USER_ID);
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return getTodos(resultSet);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ArrayList<Todo> getTodos(ResultSet resultSet) throws SQLException {
        ArrayList<Todo> todos = new ArrayList<>();
        while (resultSet.next()) {
            Todo newTodo = new Todo();
            newTodo.setId(resultSet.getLong("todo_id"));
            newTodo.setTitle(resultSet.getString("title"));
            newTodo.setStatus(TodoStatus.valueOf(resultSet.getString("status").toUpperCase()));
            todos.add(newTodo);
        }
        return todos;
    }

    public Long insertTodo(String title, Long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TODO);
            preparedStatement.setString(1, title);
            preparedStatement.setLong(2, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getLong("todo_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String updateTodo(String title, String status, Long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TODO);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, status);
            preparedStatement.setLong(3, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return "Updated successfully";
            }
        } catch (SQLException e) {
            throw new AppNotFoundExceptionHandler();
        }
        return null;
    }

    public String deleteTodo(Long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_TODO);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return "Deleted successfully";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
