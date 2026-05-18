<%-- 
    Document   : asignarRepartidores
    Created on : 17/05/2026, 4:32:16 p. m.
    Author     : ruben
--%>

<%@page import="modelo.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="modelo.Pedidos"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Asignar Repartidores</title>
    </head>
    <body>
        <h2>Asignar Pedidos</h2>
        <% if(request.getParameter("asignado") != null) { %>
            <div style="background-color: #d4edda; color: #155724; padding: 10px; border-radius: 5px; margin-bottom: 15px;">
                Pedido asignado correctamente
            </div>
        <% } %>
        <% if(request.getParameter("error") != null) { %>
            <p style="color: red;">
                Pedido no asignado
            </p>
        <% } %>
        <form action="ControladorAsignarRepartidores" method="post">
            <div class="container">
            <label>Pedido</label>
            <select name="selectPedido">
                <%
                List<Pedidos> pedidos = (List<Pedidos>) request.getAttribute("pedidos");
                if(pedidos != null){
                    for(Pedidos pedido:pedidos){
                %>
                    <option value="<%=pedido.getId()%>"><%=pedido.getDescripcion()%></option>
                <%
                        }
                    }
                    %>
            </select>
        </div>
            <div class="container">
                <label>Repartidor</label>
                <select name="selectRepartidor">
                <%
                List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");
                if(usuarios != null){
                    for(Usuario usuario:usuarios){
                %>
                    <option value="<%=usuario.getIdUsuario()%>"><%=usuario.getNombre()%> <%=usuario.getApellido()%></option>
                <%
                        }
                    }
                    %>
            </select>
            </div>
            <button type="submit">Asignar</button>
        </form>
    </body>
</html>
