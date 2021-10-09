package java2_Homework_8.server;

import java.sql.*;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class AuthService {

    public Optional<Entry> findUserByLoginAndPassword(String login, String password){


        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM users WHERE login = ? AND password = ?");
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return Optional.of (
                        new Entry(resultSet.getString("name"),
                                resultSet.getString("login"),
                                resultSet.getString("password"))
                );
            }
            else return Optional.empty();
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }

    }

}
