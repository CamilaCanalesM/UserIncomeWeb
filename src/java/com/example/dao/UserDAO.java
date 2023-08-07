package com.example.dao;

import com.example.entities.UserEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAO {
    private final Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean createUser(UserEntity user) {
        String query = "INSERT INTO users (nombre, apellido, edad, correo) VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user.getNombre());
            preparedStatement.setString(2, user.getApellido());
            preparedStatement.setInt(3, user.getEdad());
            preparedStatement.setString(4, user.getCorreo());

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateUser(UserEntity user) {
        String query = "UPDATE users SET nombre = ?, apellido = ?, edad = ?, correo = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user.getNombre());
            preparedStatement.setString(2, user.getApellido());
            preparedStatement.setInt(3, user.getEdad());
            preparedStatement.setString(4, user.getCorreo());
            preparedStatement.setInt(5, user.getId());

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteUser(int userId) {
        String query = "DELETE FROM users WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
