<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Form</title>
</head>
<body>
    <h2>User Form</h2>
    <form action="UserServlet" method="post">
        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="nombre" required><br>
        
        <label for="apellido">Apellido:</label>
        <input type="text" id="apellido" name="apellido" required><br>
        
        <label for="edad">Edad:</label>
        <input type="number" id="edad" name="edad" required><br>
        
        <label for="correo">Correo:</label>
        <input type="email" id="correo" name="correo" required><br>
        
        <input type="submit" value="Guardar">
    </form>
</body>
</html>
