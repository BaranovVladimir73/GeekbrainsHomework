package com.geekbrains.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class AuthService {

    public Optional<Entry> findUserByLoginAndPassword(String login, String password){


        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM users WHERE user_name = ? AND password = ?");
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return Optional.of (
                        new Entry(resultSet.getString("user_name"),
                                resultSet.getString("password"))
                );
            }
            else return Optional.empty();
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }

    }
}
