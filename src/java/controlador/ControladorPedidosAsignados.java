package controlador;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import modelo.Pedidos;
import modelo.Usuario;

@WebServlet(name = "ControladorPedidosAsignados",
        urlPatterns = {"/ControladorPedidosAsignados"})

public class ControladorPedidosAsignados extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession sesion = request.getSession();

        Usuario usuarioActivo =
                (Usuario) sesion.getAttribute("usuarioSesion");

        if(usuarioActivo == null){

            response.sendRedirect("login.jsp");

            return;
        }

        Pedidos modeloPedidos = new Pedidos();

        List<Pedidos> listaPedidos =
                modeloPedidos.verPedidosAsignados(
                        usuarioActivo.getIdUsuario());
        request.setAttribute("pedidos", listaPedidos);
        request.getRequestDispatcher(
                "pedidosAsignados.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        try{
            int idPedido = Integer.parseInt(request.getParameter("idPedido"));
            Pedidos pedidos = new Pedidos();
            pedidos.marcarEntrega(idPedido);
            response.sendRedirect("ControladorPedidosAsignados?entregado=ok");
        } catch(Exception e){
            response.sendRedirect("ControladorPedidosAsignados?error=1");
        }
    }

    @Override
    public String getServletInfo() {

        return "Controlador de pedidos asignados";
    }
}