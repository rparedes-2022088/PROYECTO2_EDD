/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.Date;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.sql.PreparedStatement;

/**
 *
 * @author ruben
 */
public class Usuario {
    private int idUsuario;
    private String nombre;
    private String apellido;
    private String correo;
    private String pass;
    private String telefono;
    private String direccion;
    private Date fechaRegistro;
    private Rol rol;

    public Usuario() {
    }

    public Usuario(int idUsuario, String nombre, String apellido, String correo, String pass, String telefono, String direccion, Date fechaRegistro, Rol rol) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.pass = pass;
        this.telefono = telefono;
        this.direccion = direccion;
        this.fechaRegistro = fechaRegistro;
        this.rol = rol;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
    
    public List<Usuario> verUsuarios(){
        String consulta;
        ConexionBDD conexion = new ConexionBDD();
        consulta = "SELECT u.*, r.nombre_rol " +
           "FROM usuarios u " +
           "INNER JOIN rol r ON u.id_rol = r.id_rol";
        System.out.println(consulta);
        Connection con = conexion.conectar();
        try {
            List<Usuario> usuarios = new ArrayList<>();
            Statement cn = con.createStatement();
            try {
                ResultSet rs = cn.executeQuery(consulta);
                while(rs.next()){
                    Usuario usuario = new Usuario();
                    usuario.setIdUsuario(rs.getInt("id_usuario"));
                    usuario.setNombre(rs.getString("nombre"));
                    usuario.setApellido(rs.getString("apellido"));
                    usuario.setCorreo(rs.getString("correo"));
                    usuario.setPass(rs.getString("pass"));
                    usuario.setTelefono(rs.getString("telefono"));
                    usuario.setDireccion(rs.getString("direccion"));
                    usuario.setFechaRegistro(rs.getDate("fecha_registro"));

                    Rol rol = new Rol();
                    rol.setIdRol(rs.getInt("id_rol"));
                    rol.setNombreRol(rs.getString("nombre_rol"));
                    usuario.setRol(rol);
                    usuarios.add(usuario);
                }
                return usuarios;
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
    
    public List<Usuario> verRepartidores(){
        String consulta;
        ConexionBDD conexion = new ConexionBDD();
        consulta = "SELECT u.*, r.nombre_rol " +
            "FROM usuarios u " +
            "INNER JOIN rol r ON u.id_rol = r.id_rol " +
            "WHERE u.id_rol = 3";
        System.out.println(consulta);
        Connection con = conexion.conectar();
        try {
            List<Usuario> usuarios = new ArrayList<>();
            Statement cn = con.createStatement();
            try {
                ResultSet rs = cn.executeQuery(consulta);
                while(rs.next()){
                    Usuario usuario = new Usuario();
                    usuario.setIdUsuario(rs.getInt("id_usuario"));
                    usuario.setNombre(rs.getString("nombre"));
                    usuario.setApellido(rs.getString("apellido"));
                    usuario.setCorreo(rs.getString("correo"));
                    usuario.setPass(rs.getString("pass"));
                    usuario.setTelefono(rs.getString("telefono"));
                    usuario.setDireccion(rs.getString("direccion"));
                    usuario.setFechaRegistro(rs.getDate("fecha_registro"));

                    Rol rol = new Rol();
                    rol.setIdRol(rs.getInt("id_rol"));
                    rol.setNombreRol(rs.getString("nombre_rol"));
                    usuario.setRol(rol);
                    usuarios.add(usuario);
                }
                return usuarios;
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
    
    public Usuario login(String correo, String password){
        String consulta;
        ConexionBDD conexion = new ConexionBDD();
        Usuario usuario = new Usuario();
        usuario = null;
        
        consulta = "SELECT u.*, r.nombre_rol " +
           "FROM usuarios u " +
           "INNER JOIN rol r ON u.id_rol = r.id_rol " +
           "WHERE correo = '" + correo + "' " +
           "AND pass = '" + password + "'";
        System.out.println(consulta);
        Connection con = conexion.conectar();
        try {
            Statement cn = con.createStatement();
            ResultSet rs = cn.executeQuery(consulta);
            
            if(rs.next()){
                usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido(rs.getString("apellido"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setPass(rs.getString("pass"));
                usuario.setTelefono(rs.getString("telefono"));
                usuario.setDireccion(rs.getString("direccion"));
                usuario.setFechaRegistro(rs.getDate("fecha_registro"));
                
                Rol rol = new Rol();
                rol.setIdRol(rs.getInt("id_rol"));
                rol.setNombreRol(rs.getString("nombre_rol"));
                usuario.setRol(rol);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return usuario; //Devuelve el usuario lleno o null si no existe
    }
    
    public int registrarUsuario(Usuario usuario){
        String consulta;
        ConexionBDD conexion = new ConexionBDD();
        int idGenerado = 0;
        
        consulta = "INSERT INTO usuarios(nombre, apellido, correo, pass, telefono, direccion, fecha_registro, id_rol)"
                + " VALUES('"
                + usuario.getNombre() + "', '"
                + usuario.getApellido() + "', '"
                + usuario.getCorreo() + "', '"
                + usuario.getPass() + "', '"
                + usuario.getTelefono() + "', '"
                + usuario.getDireccion() + "', "
                + "SYSDATE, "
                + usuario.getRol().getIdRol() + ")";
        System.out.println(consulta);
        Connection con = conexion.conectar();
        try {
            Statement cn = con.createStatement();
            int filas = cn.executeUpdate(consulta);
            if(filas > 0){
                System.out.println("Usuario agregado exitosamente");
                idGenerado = obtenerUltimoId();
                
            } else {
                System.out.println("Usuario no agregado");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

        return idGenerado;
    }
    
    public int calcularHash(String correo){

        int suma = 0;

        for(int i = 0; i < correo.length(); i++){

            suma += correo.charAt(i);
        }

        return suma % 10;
    }
    
    public java.util.ArrayList<Usuario> obtenerTodos() {
        java.util.ArrayList<Usuario> lista = new java.util.ArrayList<>();
        String consulta = "SELECT u.*, r.nombre_rol " +
                          "FROM usuarios u " +
                          "INNER JOIN rol r ON u.id_rol = r.id_rol";

        ConexionBDD conexion = new ConexionBDD();
        Connection con = conexion.conectar();

        try {
            Statement cn = con.createStatement();
            ResultSet rs = cn.executeQuery(consulta);

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido(rs.getString("apellido"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setPass(rs.getString("pass"));
                usuario.setTelefono(rs.getString("telefono"));
                usuario.setDireccion(rs.getString("direccion"));
                usuario.setFechaRegistro(rs.getDate("fecha_registro"));

                // Reconstruimos el objeto Rol que lleva dentro el usuario
                Rol rol = new Rol();
                rol.setIdRol(rs.getInt("id_rol"));
                rol.setNombreRol(rs.getString("nombre_rol"));
                usuario.setRol(rol);

                // Lo agregamos a la lista temporal
                lista.add(usuario);
            }
            System.out.println("Se extrajeron " + lista.size() + " usuarios de Oracle.");

        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try { if (con != null) con.close(); } catch (SQLException e) {}
        }

        return lista;
    }
    
    public int obtenerUltimoId(){

        String consulta;

        ConexionBDD conexion = new ConexionBDD();

        consulta = "SELECT MAX(id_usuario) AS ultimo FROM usuarios";

        Connection con = conexion.conectar();

        try {

            Statement cn = con.createStatement();

            ResultSet rs = cn.executeQuery(consulta);

            if(rs.next()){

                return rs.getInt("ultimo");
            }

        } catch (SQLException ex) {

            Logger.getLogger(Usuario.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

        return 0;
    }
    
    public boolean eliminarUsuario(int idUsuario){

        String consulta =
                "DELETE FROM usuarios WHERE id_usuario = ?";

        ConexionBDD conexion = new ConexionBDD();

        Connection con = conexion.conectar();

        try{

            PreparedStatement ps =
                    con.prepareStatement(consulta);

            ps.setInt(1, idUsuario);

            int filas = ps.executeUpdate();

            return filas > 0;
        }
        catch(Exception e){

            e.printStackTrace();
        }
        finally{

            try{

                if(con != null){

                    con.close();
                }

            }
            catch(Exception e){

            }
        }

        return false;
    }
}
