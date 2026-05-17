<%-- 
    Document   : historial
    Created on : 17/05/2026, 12:19:00 p. m.
    Author     : ruben
--%>

<%@page import="modelo.Pedidos"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <table border="1">
                <tr>
                    <th>ID</th>
                    <th>Descripcion</th>
                    <th>Dirección</th>
                    <th>Prioridad</th>
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
                        <td><%=pedido.getPrioridad()%></td>
                    </tr>
                    <%
                            }
                        }
                        %>
            </table>
        </div>
    </body>
</html>
