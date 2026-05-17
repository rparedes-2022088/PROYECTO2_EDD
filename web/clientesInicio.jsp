<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cliente</title>
    <link rel="stylesheet" href="css/estilos.css">
</head>
<body>

    <div class="menu">

        <h1>Panel Cliente</h1>

        <a href="ControladorPedidos?action=solicitar">
            <button>Crear Pedido</button>
        </a>

        <a href="ControladorPedidos?action=estado">
            <button>Ver Estado</button>
        </a>

        <a href="ControladorPedidos?action=historial">
            <button>Historial</button>
        </a>

        <a href="ControladorLogOut">
            <button>Cerrar Sesión</button>
        </a>

    </div>

</body>
</html>