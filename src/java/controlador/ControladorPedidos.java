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
import modelo.Pedidos;

/**
 *
 * @author chris
 */
@WebServlet(name = "ControladorPedidos", urlPatterns = {"/ControladorPedidos"})
public class ControladorPedidos extends HttpServlet {

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
            out.println("<title>Servlet ControladorPedidos</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorPedidos at " + request.getContextPath() + "</h1>");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        modelo.Usuario usuarioLogueado = (modelo.Usuario) request.getSession().getAttribute("usuarioSesion");

        if (action == null || usuarioLogueado == null){
            response.sendRedirect("clientesInicio.jsp");
            return;
        }
        
        if(action.equals("solicitar")){
            response.sendRedirect("CrearPedidos.jsp");
        }
        else if(action.equals("estado")){
            // Instanciamos tu modelo para interactuar con Oracle
        Pedidos manejador = new Pedidos();
        
        
        java.util.List<Pedidos> listaActivos = manejador.verEstadoPedidos(usuarioLogueado.getIdUsuario());
        
        // Almacenamos la lista en los atributos de la petición con el nombre que espera tu JSP
        request.setAttribute("pedidoActual", listaActivos);
        
        // Usamos forward en lugar de sendRedirect para mantener los datos vivos
        request.getRequestDispatcher("estadoPedido.jsp").forward(request, response);
        }
        else if(action.equals("Historial")){
            Pedidos manejador = new Pedidos();
        
            // Ejecutamos método de historial
            java.util.List<Pedidos> listaHistorial = manejador.verHistorialPedidos(usuarioLogueado.getIdUsuario());

            request.setAttribute("pedidoActual", listaHistorial);

            // Despachamos al JSP de historial usando forward
            request.getRequestDispatcher("historialPedido.jsp").forward(request, response);
        }
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
        String direccion = request.getParameter("direccion");
        String descripcion = request.getParameter("descripcion");
        String prioridad = request.getParameter("prioridad");

        modelo.Usuario clienteActual = (modelo.Usuario) request.getSession().getAttribute("usuarioSesion");
        
        if(clienteActual != null){
            
            Pedidos nuevo = new Pedidos();
            nuevo.setCliente(clienteActual);
            nuevo.setDescripcion(descripcion);
            nuevo.setDireccionEntrega(direccion);
            nuevo.setPrioridad(prioridad);
            nuevo.setEstado("PENDIENTE");
            
            /*
            nuevo.setFechaPedido(new java.util.Date());
            */
            
            
            nuevo.nuevoPedido(nuevo);
            
            System.out.println("Pedido guardado");
            
            response.sendRedirect("ControladorPedidos?action=estado");
        }
        else{
            response.sendRedirect("login.jsp");
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
