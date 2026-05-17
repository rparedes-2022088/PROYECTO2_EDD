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
import java.util.logging.Logger;
import java.util.logging.Level;

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
    
    public boolean registrarUsuario(Usuario usuario){
        String consulta;
        ConexionBDD conexion = new ConexionBDD();
        boolean exito = false;
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
                int idGenerado = obtenerUltimoId();
                usuario.setIdUsuario(idGenerado);
                int posicionHash = calcularHash(usuario.getCorreo());
                insertarHash(posicionHash, idGenerado);
                insertarArbol(idGenerado, idGenerado);
                exito = true;
            } else {
                System.out.println("Usuario no agregado");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

        return exito;
    }
    
    public int calcularHash(String correo){

        int suma = 0;

        for(int i = 0; i < correo.length(); i++){

            suma += correo.charAt(i);
        }

        return suma % 10;
    }
    
    public void insertarHash(int posicionHash, int idUsuario){

        String consulta;

        ConexionBDD conexion = new ConexionBDD();

        consulta = "INSERT INTO hash_table(posicion_hash, id_usuario) "
                + "VALUES(" + posicionHash + ", " + idUsuario + ")";

        Connection con = conexion.conectar();

        try {

            Statement cn = con.createStatement();

            cn.executeUpdate(consulta);

            System.out.println("Insertado en hash");

        } catch (SQLException ex) {

            Logger.getLogger(Usuario.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertarArbol(int claveArbol, int idUsuario){

        String consulta;

        ConexionBDD conexion = new ConexionBDD();

        consulta = "INSERT INTO arbol_binario(clave_arbol, id_usuario) "
                + "VALUES(" + claveArbol + ", " + idUsuario + ")";

        Connection con = conexion.conectar();

        try {

            Statement cn = con.createStatement();

            cn.executeUpdate(consulta);

            System.out.println("Insertado en árbol");

        } catch (SQLException ex) {

            Logger.getLogger(Usuario.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
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
}
