/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ruben
 */

/*
    listado TODO
    Admin: Ver todos los pedidos, asignar repartidores.
        Listos: Ver todos los pedidos, asignar repartidores.


    Cliente: Crear pedidos, consultar estados de los pedidos.
        Listos: Crear pedidos, consultar estados de los pedidos
No entiendo el ver historial

    Repartidor: Ver pedidos asignados, marcar pedidos como entregados.
        Listos: , marcar como entregados
*/


public class Pedidos {
    private int id;
    private String descripcion;
    private String direccionEntrega;
    private Date fechaPedido;
    private String estado; //PENDIENTE = sin asignar, ASIGNADO = asignado, ENTREGADO = entregado
    private String prioridad;
    private Usuario cliente;
    private Usuario repartidor;

    public Pedidos() {
    }

    public Pedidos(int id, String descripcion, String direccionEntrega, Date fechaPedido, String estado, String prioridad, Usuario cliente) {
        this.id = id;
        this.descripcion = descripcion;
        this.direccionEntrega = direccionEntrega;
        this.fechaPedido = fechaPedido;
        this.estado = estado;
        this.prioridad = prioridad;
        this.cliente = cliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public void setDireccionEntrega(String direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public Usuario getRepartidor() {
        return repartidor;
    }

    public void setRepartidor(Usuario repartidor) {
        this.repartidor = repartidor;
    }
    
    public void nuevoPedido(Pedidos pedido){
        String consulta;
        ConexionBDD conexion = new ConexionBDD();
        consulta = "insert into pedidos(descripcion, direccion_entrega, fecha_pedido, estado, prioridad, id_cliente)"
                + " values('" + pedido.getDescripcion() + "', '"
                + pedido.getDireccionEntrega() + "', "
                + pedido.getFechaPedido() + ", "
                + pedido.getEstado() + ", "
                + pedido.getPrioridad() + ", "
                + pedido.getCliente().getIdUsuario() + ")";
        System.out.println(consulta);
        Connection con = conexion.conectar();
        try {
            Statement cn = con.createStatement();
            int filas = cn.executeUpdate(consulta);
            if(filas > 0){
                System.out.println("Pedido agregado exitosamente");
            } else {
                System.out.println("Pedido no agregado");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Admins
    public void asignarRepartidor(int idPedido, int idRepartidor){
        String consulta;
        ConexionBDD conexion = new ConexionBDD();
        consulta = "update pedidos set estado = 'ASIGNADO', id_repartidor = " + idRepartidor
                + " where id_pedido = " + idPedido;
        System.out.println(consulta);
        Connection con = conexion.conectar();
        try {
            Statement cn = con.createStatement();
            int filas = cn.executeUpdate(consulta);
            if(filas > 0){
                System.out.println("Repartidor asignado exitosamente");
            } else {
                System.out.println("Repartidor no asignado");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void marcarEntrega(int idPedido){
        String consulta;
        ConexionBDD conexion = new ConexionBDD();
        consulta = "update pedidos set estado = 'ENTREGADO' where id_pedido = " + idPedido;
        System.out.println(consulta);
        Connection con = conexion.conectar();
        try {
            Statement cn = con.createStatement();
            int filas = cn.executeUpdate(consulta);
            if(filas > 0){
                System.out.println("Entregado exitosamente");
            } else {
                System.out.println("No marcado como entregado");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Admin
    public List<Pedidos> verPedidos(){
        String consulta;
        ConexionBDD conexion = new ConexionBDD();
        consulta = "SELECT p.*, " +
                "c.nombre AS nombre_cliente, " +
                "c.apellido AS apellido_cliente, " +
                "r.nombre AS nombre_repartidor, " +
                "r.apellido AS apellido_repartidor " +
                "FROM pedidos p " +
                "INNER JOIN usuarios c ON p.id_cliente = c.id_usuario " +
                "LEFT JOIN usuarios r ON p.id_repartidor = r.id_usuario";
        System.out.println(consulta);
        Connection con = conexion.conectar();
        try {
            List<Pedidos> pedidos = new ArrayList<>();
            Statement cn = con.createStatement();
            try {
                ResultSet rs = cn.executeQuery(consulta);
                while(rs.next()){
                    Pedidos pedido = new Pedidos();
                    Usuario cliente = new Usuario();
                    Usuario repartidor = new Usuario();
                    pedido.setId(rs.getInt("id_pedido"));
                    pedido.setDescripcion(rs.getString("descripcion"));
                    pedido.setDireccionEntrega(rs.getString("direccion_entrega"));
                    pedido.setFechaPedido(rs.getDate("fecha_pedido"));
                    pedido.setEstado(rs.getString("estado"));
                    pedido.setPrioridad(rs.getString("prioridad"));

                    cliente.setNombre(rs.getString("nombre_cliente"));
                    cliente.setApellido(rs.getString("apellido_cliente"));
                    pedido.setCliente(cliente);

                    if(rs.getString("nombre_repartidor") != null){
                        repartidor.setNombre(rs.getString("nombre_repartidor"));
                        repartidor.setApellido(rs.getString("apellido_repartidor"));
                        pedido.setRepartidor(repartidor);
                    } else {
                        repartidor.setNombre("Sin asignar");
                        repartidor.setApellido("Sin asignar");
                        pedido.setRepartidor(repartidor);
                    }
                    pedidos.add(pedido);
                }
                return pedidos;
            } catch (SQLException ex) {
                Logger.getLogger(Pedidos.class.getName())
                        .log(Level.SEVERE, null, ex);
            }

        } catch (SQLException ex) {

            Logger.getLogger(Pedidos.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

        return null;
    }
    
    //Cliente
    public List<Pedidos> verEstadoPedidos(int idUsuarioActivo){
        String consulta;
        ConexionBDD conexion = new ConexionBDD();
        consulta = "select * from pedidos where id_cliente = " + idUsuarioActivo
                + "and estado in ('PENDIENTE' ,'ASIGNADO') order by id_pedido desc";
        System.out.println(consulta);
        Connection con = conexion.conectar();
        try {
            List<Pedidos> pedidos = new ArrayList<>();
            Statement cn = con.createStatement();
            try {
                ResultSet rs = cn.executeQuery(consulta);
                while(rs.next()){
                    Pedidos pedido = new Pedidos();
                    pedido.setId(rs.getInt("id_pedido"));
                    pedido.setDescripcion(rs.getString("descripcion"));
                    pedido.setDireccionEntrega(rs.getString("direccion_entrega"));
                    pedido.setFechaPedido(rs.getDate("fecha_pedido"));
                    pedido.setEstado(rs.getString("estado"));
                    pedido.setPrioridad(rs.getString("prioridad"));
                    pedidos.add(pedido);
                }
                return pedidos;
            } catch (SQLException ex) {
                Logger.getLogger(Pedidos.class.getName())
                        .log(Level.SEVERE, null, ex);
            }

        } catch (SQLException ex) {

            Logger.getLogger(Pedidos.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

        return null;
    }
    
    //Repartidor
    public List<Pedidos> verPedidosAsignados(int idRepartidorActivo){
        String consulta;
        ConexionBDD conexion = new ConexionBDD();
        consulta = "SELECT p.*, " +
                "c.nombre AS nombre_cliente, " +
                "c.apellido AS apellido_cliente " +
                "FROM pedidos p " +
                "INNER JOIN usuarios c " +
                "ON p.id_cliente = c.id_usuario " +
                "WHERE p.id_repartidor = "
                + idRepartidorActivo +
                " AND p.estado = 'ASIGNADO'";
        System.out.println(consulta);
        Connection con = conexion.conectar();
        try {
            List<Pedidos> pedidos = new ArrayList<>();
            Statement cn = con.createStatement();
            ResultSet rs = cn.executeQuery(consulta);
            while(rs.next()){
                Pedidos pedido = new Pedidos();
                Usuario cliente = new Usuario();
                pedido.setId(rs.getInt("id_pedido"));
                pedido.setDescripcion(rs.getString("descripcion"));
                pedido.setDireccionEntrega(rs.getString("direccion_entrega"));
                pedido.setFechaPedido(rs.getDate("fecha_pedido"));
                pedido.setEstado(rs.getString("estado"));
                pedido.setPrioridad(rs.getString("prioridad"));
                cliente.setNombre(rs.getString("nombre_cliente"));
                cliente.setApellido(rs.getString("apellido_cliente"));
                pedido.setCliente(cliente);
                pedidos.add(pedido);
            }
            return pedidos;
        } catch (SQLException ex) {
            Logger.getLogger(Pedidos.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public List<Pedidos> verHistorialPedidos(int idUsuarioActivo){
        String consulta;
        ConexionBDD conexion = new ConexionBDD();
        
        // El historial solo busca los registros pasados que ya fueron 'ENTREGADO' [cite: 14]
        consulta = "select * from pedidos where id_cliente = " + idUsuarioActivo 
                 + " and estado = 'ENTREGADO' order by id_pedido desc";
                 
        System.out.println(consulta);
        Connection con = conexion.conectar();
        try {
            List<Pedidos> pedidos = new ArrayList<>();
            Statement cn = con.createStatement();
            ResultSet rs = cn.executeQuery(consulta);
            while(rs.next()){
                Pedidos pedido = new Pedidos();
                pedido.setId(rs.getInt("id_pedido"));
                pedido.setDescripcion(rs.getString("descripcion"));
                pedido.setDireccionEntrega(rs.getString("direccion_entrega"));
                pedido.setFechaPedido(rs.getDate("fecha_pedido"));
                pedido.setEstado(rs.getString("estado"));
                pedido.setPrioridad(rs.getString("prioridad"));
                pedidos.add(pedido);
            }
            return pedidos;
        } catch (SQLException ex) {
            Logger.getLogger(Pedidos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try { if(con != null) con.close(); } catch(SQLException e){}
        }
        return null;
    }
}
