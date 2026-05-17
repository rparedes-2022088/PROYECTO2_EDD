/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import modelo.Pedidos;
import modelo.Usuario;

/**
 *
 * @author ruben
 */
@WebServlet(name = "ControladorAsignarRepartidores", urlPatterns = {"/ControladorAsignarRepartidores"})
public class ControladorAsignarRepartidores extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControladorAsignarRepartidores</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorAsignarRepartidores at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession sesion = request.getSession();

        Usuario usuarioActivo = (Usuario) sesion.getAttribute("usuarioSesion");

        if(usuarioActivo == null){

            response.sendRedirect("login.jsp");

            return;
        }

        Pedidos modeloPedidos = new Pedidos();
        Usuario usuarios = new Usuario();

        List<Usuario> usuariosEncontrados = usuarios.verRepartidores();
        List<Pedidos> listaPedidos = modeloPedidos.verPedidosSinAsignar();
        request.setAttribute("pedidos", listaPedidos);
        request.setAttribute("usuarios", usuariosEncontrados);
        request.getRequestDispatcher(
                "asignarRepartidores.jsp")
                .forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            int idPedido = Integer.parseInt(request.getParameter("selectPedido"));
            int idRepartidor = Integer.parseInt(request.getParameter("selectRepartidor"));
            Pedidos pedidoManejador = new Pedidos();
            pedidoManejador.asignarRepartidor(idPedido, idRepartidor);
            response.sendRedirect("ControladorAsignarRepartidores?asignado=ok");
        } catch(Exception e){
            response.sendRedirect("ControladorAsignarRepartidores?error=1");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
