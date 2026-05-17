<%-- 
    Document   : pedidosAdmin
    Created on : 17/05/2026, 12:44:38 p. m.
    Author     : ruben
--%>

<%@page import="modelo.Pedidos"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ver todos los pedidos</title>
    </head>
    <body>
        <div class="container">
            <table border="1">
                <tr>
                    <th>ID</th>
                    <th>Descripcion</th>
                    <th>Dirección</th>
                    <th>Fecha</th>
                    <th>Estado</th>
                    <th>Prioridad</th>
                    <th>Cliente</th>
                    <th>Repartidor</th>
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
                            <%=pedido.getRepartidor().getNombre()%>
                            <%=pedido.getRepartidor().getApellido()%>
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
