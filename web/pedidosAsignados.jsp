<%@page import="java.util.List"%>
<%@page import="modelo.Pedidos"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Pedidos asignados</title>
</head>
    <body>
    <div class="container">
        <h2>Pedidos asignados</h2>
        <% if(request.getParameter("entregado") != null) { %>
            <div style="background-color: #d4edda; color: #155724; padding: 10px; border-radius: 5px; margin-bottom: 15px;">
                Pedido marcado como entregado
            </div>
        <% } %>
        <% if(request.getParameter("error") != null) { %>
            <p style="color: red;">
                Pedido no marcado como entregado
            </p>
        <% } %>
        <table border="1">
            <tr>
                <th>ID</th>
                <th>Descripcion</th>
                <th>Dirección</th>
                <th>Fecha</th>
                <th>Estado</th>
                <th>Prioridad</th>
                <th>Cliente</th>
                <th>Acción</th>
            </tr>
            <%
                List<Pedidos> pedidos = (List<Pedidos>) request.getAttribute("pedidos");
                if(pedidos != null){
                    for(Pedidos pedido:pedidos){
                %>
                <tr>
                    <td><%=pedido.getId()%></td>
                    <td><%=pedido.getDescripcion()%></td>
                    <td><%=pedido.getDireccionEntrega()%></td>
                    <td><%=pedido.getFechaPedido()%></td>
                    <td><%=pedido.getEstado()%></td>
                    <td><%=pedido.getPrioridad()%></td>
                    <td>
                        <%=pedido.getCliente().getNombre()%>
                        <%=pedido.getCliente().getApellido()%>
                    </td>
                    <td>
                        <form action="ControladorPedidosAsignados"
                            method="post">
                            <input type="hidden" name="idPedido" value="<%= pedido.getId() %>">
                            <button type="submit">Marcar entregado</button>
                        </form>
                    </td>
                </tr>
                <%
                        }
                    }
                    %>
        </table>

    </div>
    </body>
</html>