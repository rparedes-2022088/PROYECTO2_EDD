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

        <form action="RegistroServlet" method="post">

            <label>Nombre</label>
            <input type="text" name="nombre" required>

            <label>Usuario</label>
            <input type="text" name="usuario" required>

            <label>Contraseña</label>
            <input type="password" name="password" required>

            <label>Dirección</label>
            <input type="text" name="direccion">

            <label>Teléfono</label>
            <input type="text" name="telefono">

            <button type="submit">Registrarse</button>

        </form>

    </div>

</body>
</html>