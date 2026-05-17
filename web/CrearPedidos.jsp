<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="modelo.Usuario"%>
<!DOCTYPE html>
<html>
<head>
    <title>Crear Nuevo Pedido</title>
</head>
<body>
    <%
        // 1. Validar que el usuario de verdad inició sesión antes de dejarlo entrar aquí
        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioSesion");
        if (usuarioLogueado == null) {
            // Si no hay sesión, lo mandamos de patitas a la calle (al login)
            response.sendRedirect("login.jsp");
            return; // Detiene la carga del resto de la página
        }
    %>

    <h2>Solicitar Nuevo Envío</h2>
    <p>Bienvenido, <%= usuarioLogueado.getNombre() %> </p>

    <form action="../ControladorPedidos" method="POST">
        
        <label>Descripción del Pedido (¿Qué pediste?):</label><br>
        <textarea name="descripcion" rows="4" cols="40" required placeholder="Ej. Comida rápida, documentos importantes, etc."></textarea>
        <br><br>

        <label>Dirección de Entrega Destino:</label><br>
        <input type="text" name="direccion" size="40" required placeholder="Ej. 7a Avenida 10-23, Zona 10">
        <br><br>

        <label>Prioridad de Urgencia:</label><br>
        <select name="prioridad" required>
            <option value="BAJA">Baja (Envío Normal)</option>
            <option value="NORMAL">Media (Moderadamente Urgente)</option>
            <option value="ALTA">Alta (Envío Prioritario)</option>
        </select>
        <br><br>

        <button type="submit">Confirmar y Crear Pedido</button>
    </form>
    
    <br>
    <a href="clientesInicio.jsp">Volver al Panel</a>
</body>
</html>