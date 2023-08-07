package com.example.servlets;

import com.example.dao.UserDAO;
import com.example.entities.UserEntity;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action"); // Agregar un campo oculto en el formulario JSP para indicar la acción

        if ("create".equals(action)) {
            createUser(request, response);
        } else if ("update".equals(action)) {
            updateUser(request, response);
        } else if ("delete".equals(action)) {
            deleteUser(request, response);
        } else {
            response.sendRedirect("index.jsp"); // Redirigir si la acción no está definida
        }
    }

    private void createUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener parámetros del formulario
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        int edad = Integer.parseInt(request.getParameter("edad"));
        String correo = request.getParameter("correo");

        // Crear objeto UserEntity y asignar valores
        UserEntity user = new UserEntity();
        user.setNombre(nombre);
        user.setApellido(apellido);
        user.setEdad(edad);
        user.setCorreo(correo);

        // Crear la conexión a la base de datos y realizar la inserción
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/user_income_db?zeroDateTimeBehavior=convertToNull";
            String username = "root";
            String password = "";
            Connection connection = DriverManager.getConnection(url, username, password);

            // Crear el objeto UserDAO y realizar la inserción
            UserDAO userDAO = new UserDAO(connection);
            boolean result = userDAO.createUser(user);

            // Cerrar la conexión a la base de datos
            connection.close();

            // Manejo de redirección según el resultado
            if (result) {
                response.sendRedirect("index.jsp?success=true");
            } else {
                response.sendRedirect("index.jsp?success=false");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.sendRedirect("index.jsp?success=false");
        }
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener parámetros del formulario
        int userId = Integer.parseInt(request.getParameter("userId")); // Supongamos que tienes un campo oculto con el ID del usuario
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        int edad = Integer.parseInt(request.getParameter("edad"));
        String correo = request.getParameter("correo");

        // Crear objeto UserEntity y asignar valores
        UserEntity user = new UserEntity();
        user.setId(userId);
        user.setNombre(nombre);
        user.setApellido(apellido);
        user.setEdad(edad);
        user.setCorreo(correo);

        // Crear la conexión a la base de datos y realizar la actualización
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/user_income_db?zeroDateTimeBehavior=convertToNull";
            String username = "root";
            String password = "";
            Connection connection = DriverManager.getConnection(url, username, password);

            // Crear el objeto UserDAO y realizar la actualización
            UserDAO userDAO = new UserDAO(connection);
            boolean result = userDAO.updateUser(user);

            // Cerrar la conexión a la base de datos
            connection.close();

            // Manejo de redirección según el resultado
            if (result) {
                response.sendRedirect("index.jsp?success=true");
            } else {
                response.sendRedirect("index.jsp?success=false");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.sendRedirect("index.jsp?success=false");
        }
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener parámetros del formulario
        int userId = Integer.parseInt(request.getParameter("userId")); // Supongamos que tienes un campo oculto con el ID del usuario

        // Crear la conexión a la base de datos y realizar la eliminación
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/user_income_db?zeroDateTimeBehavior=convertToNull";
            String username = "root";
            String password = "";
            Connection connection = DriverManager.getConnection(url, username, password);

            // Crear el objeto UserDAO y realizar la eliminación
            UserDAO userDAO = new UserDAO(connection);
            boolean result = userDAO.deleteUser(userId);

            // Cerrar la conexión a la base de datos
            connection.close();

            // Manejo de redirección según el resultado
            if (result) {
                response.sendRedirect("index.jsp?success=true");
            } else {
                response.sendRedirect("index.jsp?success=false");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.sendRedirect("index.jsp?success=false");
        }
    }
}
