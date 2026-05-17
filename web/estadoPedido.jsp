<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="modelo.Pedidos"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Estado del Pedido</title>
    <link rel="stylesheet" href="css/estilos.css">
</head>
<body>

    <div class="contenedor">
        <h2>Estado de tu Envío</h2>

        <%
            // Capturamos el pedido que nos envía el Controlador
            java.util.List<modelo.Pedidos> lista = (java.util.List<modelo.Pedidos>) request.getAttribute("pedidoActual");

            if (lista != null && !lista.isEmpty()) {
                for(modelo.Pedidos p : lista){
        %>
                <div class="tarjeta">
                    <p><strong>Descripción:</strong> <%= p.getDescripcion() %></p>
                    <p><strong>Destino:</strong> <%= p.getDireccionEntrega() %></p>
                    <p><strong>Estado:</strong> <%= p.getEstado() %></p>
                </div>
        <%
                }
            } else {
        %>
                <p>No tienes ningún pedido en proceso actualmente.</p>
        <%
            }
        %>
            
            
       

        <br><br>
        <a href="clientesInicio.jsp"><button>Volver al Panel</button></a>
    </div>

</body>
</html>