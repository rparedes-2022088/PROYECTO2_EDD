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

        <form action="LoginServlet" method="post">

            <label>Usuario</label>
            <input type="text" name="usuario" required>

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
