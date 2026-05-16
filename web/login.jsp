<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="css/estilos.css">
</head>
<body>

    <div class="container">

        <h2>Iniciar Sesión</h2>
        
        <% if(request.getParameter("registro") != null) { %>
        <div style="background-color: #d4edda; color: #155724; padding: 10px; border-radius: 5px; margin-bottom: 15px;">
            Usuario registrado
        </div>
    <% } %>
        
        <%-- Validar si el servlet nos mandó de regreso por datos incorrectos --%>
    <% if(request.getParameter("error") != null) { %>
        <p style="color: red;">Usuario o contraseña incorrectos.</p>
    <% } %>

        <form action="ControladorLogin" method="post">

            <label>Correo</label>
            <input type="text" name="correo" required>

            <label>Contraseña</label>
            <input type="password" name="password" required>

            <button type="submit">Ingresar</button>

        </form>

        <p>
            ¿No tienes cuenta?
            <a href="registro.jsp">Registrarse</a>
        </p>

    </div>

</body>
</html>
