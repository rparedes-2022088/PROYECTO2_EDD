<%@page import="java.util.ArrayList"%>
<%@page import="modelo.Usuario"%>

<!DOCTYPE html>

<html>

<head>

    <meta charset="UTF-8">

    <title>Administrar Usuarios</title>

</head>

<body>

    <h1>Administración de Usuarios</h1>

    <hr>

    <h2>Registrar Nuevo Usuario</h2>

    <form action="ControladorRegistroAdmin" method="POST">

        <input type="text"
               name="nombre"
               placeholder="Nombre"
               required>

        <br><br>

        <input type="text"
               name="apellido"
               placeholder="Apellido"
               required>

        <br><br>

        <input type="email"
               name="correo"
               placeholder="Correo"
               required>

        <br><br>

        <input type="password"
               name="password"
               placeholder="Contraseña"
               required>

        <br><br>

        <input type="text"
               name="direccion"
               placeholder="Dirección"
               required>

        <br><br>

        <input type="text"
               name="telefono"
               placeholder="Teléfono"
               required>

        <br><br>

        <select name="select">

            <option value="1">
                Administrador
            </option>

            <option value="2">
                Cliente
            </option>

            <option value="3">
                Repartidor
            </option>

        </select>

        <br><br>

        <button type="submit">
            Registrar Usuario
        </button>

    </form>

    <hr>

    <h2>Lista de Usuarios</h2>

    <table border="1" cellpadding="10">

        <tr>

            <th>ID</th>
            <th>Nombre</th>
            <th>Correo</th>
            <th>Rol</th>
            <th>Acción</th>

        </tr>

        <%

            ArrayList<Usuario> listaUsuarios =
                    (ArrayList<Usuario>)
                    request.getAttribute(
                            "listaUsuarios");

            if(listaUsuarios != null){

                for(Usuario u : listaUsuarios){

        %>

        <tr>

            <td>

                <%= u.getIdUsuario() %>

            </td>

            <td>

                <%= u.getNombre() %>
                <%= u.getApellido() %>

            </td>

            <td>

                <%= u.getCorreo() %>

            </td>

            <td>

                <%= u.getRol().getNombreRol() %>

            </td>

            <td>

                <a href="ControladorEliminarUsuarios?id=<%= u.getIdUsuario() %>">

                    Eliminar

                </a>

            </td>

        </tr>

        <%

                }

            }

        %>

    </table>

    <br><br>

    <a href="adminInicio.jsp">

        <button>
            Regresar
        </button>

    </a>

</body>

</html>
