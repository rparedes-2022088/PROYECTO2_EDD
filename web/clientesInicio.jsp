<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%-- 1. Importamos la clase Usuario para poder leer los datos de la sesión --%>
<%@page import="modelo.Usuario"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cliente</title>
    <link rel="stylesheet" href="css/estilos.css">
</head>
<body>

    <%
        // 2. Control de acceso obligatorio por roles
        // Recuperamos el objeto que guardamos en el ControladorLogin
        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioSesion");
        
        // Si nadie ha iniciado sesión o el que intenta entrar no es un cliente, lo rebotamos
        if (usuarioLogueado == null) {
            response.sendRedirect("login.jsp");
            return; // Detiene por completo la carga del resto del HTML
        }
    %>

    <div class="menu">

        <%-- Mostramos un saludo personalizado usando el método de tu clase Usuario --%>
        <h1>Panel Cliente - Bienvenido, <%= usuarioLogueado.getNombre() %></h1>

        <%-- 3. Cambiamos las rutas directas por peticiones al Controlador --%>
        <a href="ControladorPedidos?action=solicitar">
            <button>Crear Pedido</button>
        </a>

        <a href="ControladorPedidos?action=estado">
            <button>Ver Estado</button>
        </a>

        <a href="ControladorPedidos?action=historial">
            <button>Historial</button>
        </a>

        <%-- 4. Apuntamos al Servlet de LogOut en lugar de la página directa --%>
        <a href="ControladorLogOut">
            <button>Cerrar Sesión</button>
        </a>

    </div>

</body>
</html>