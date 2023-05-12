package com.example.todoo.repository;

import com.example.todoo.util.ConnectToDb;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserRepository {
    private final String GET_ALL_USERS = "select * from users left join todo t on users.user_id = t.user_id;";
    private final String GET_USER_BY_ID = "select * from (select * from users u where u.user_id = ? ) as u left join todo t on u.user_id = t.user_id;";
    private final String GET_USER_BY_EMAIL = "select * from users where email = ?;";
    private final String INSERT_USER = "INSERT INTO users(name, email) VALUES(?, ?) RETURNING user_id;";

    private final Connection connection;

    public UserRepository() {
        this.connection = ConnectToDb.getConnection();
    }

    public String insertOrGetUser(String name, String email) {
        try {
            PreparedStatement getUserPreparedStatement = connection.prepareStatement(GET_USER_BY_EMAIL);
            getUserPreparedStatement.setString(1, email);
            ResultSet getUserResultSet = getUserPreparedStatement.executeQuery();
            if (getUserResultSet.next()) {
                return "{ \"id\": \"" + getUserResultSet.getString("user_id")  + "\", \"name\": \"" + getUserResultSet.getString("name") + "\", \"email\": \"" + getUserResultSet.getString("email") + "\"}";
            } else {
                PreparedStatement insertUserPreparedStatement = connection.prepareStatement(INSERT_USER);
                insertUserPreparedStatement.setString(1, name);
                insertUserPreparedStatement.setString(2, email);
                ResultSet insertUserResultSet = insertUserPreparedStatement.executeQuery();
                if (insertUserResultSet.next()) {
                    return "{ \"id\": \"" + insertUserResultSet.getString("user_id")  + "\", \"name\": \"" + name + "\", \"email\": \"" + email + "\"}";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

