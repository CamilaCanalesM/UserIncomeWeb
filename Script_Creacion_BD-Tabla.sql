--
-- Base de datos: `user_income_db`
--

CREATE DATABASE user_income_db;

USE user_income_db;

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    edad INT NOT NULL,
    correo VARCHAR(100) NOT NULL
);
