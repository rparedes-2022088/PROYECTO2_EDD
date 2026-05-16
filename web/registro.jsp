<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registro</title>
    <link rel="stylesheet" href="css/estilos.css">
</head>
<body>

    <div class="container">

        <h2>Registro de Usuario</h2>
        
        <% 
        // 1. Validar si el Servlet nos mandó de regreso por un error en la base de datos
        if (request.getParameter("error") != null) { 
        %>
            <div style="background-color: #f8d7da; color: #721c24; padding: 10px; border-radius: 5px; margin-bottom: 15px;">
                <strong>Error:</strong> No se pudo completar el registro. Por favor, intente de nuevo o verifique si el correo ya existe.
            </div>
        <% 
            } 
        %>
        <form action="ControladorRegistro" method="post">

            <label>Nombre</label>
            <input type="text" name="nombre" required>
            
            <label>Apellido</label>
            <input type="text" name="apellido" required>

            <label>Usuario</label>
            <input type="text" name="usuario" required>

            <label>Contraseña</label>
            <input type="password" name="password" required>

            <label>Dirección</label>
            <input type="text" name="direccion">

            <label>Teléfono</label>
            <input type="text" name="telefono">
            
            <<label>Correo</label>
            <input type="text" name="correo" required>

            <button type="submit">Registrarse</button>

        </form>

    </div>

</body>
</html>