/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ruben
 */
public class Rol {
    private int idRol;
    private String nombreRol;

    public Rol() {
    }

    public Rol(int idRol, String nombreRol) {
        this.idRol = idRol;
        this.nombreRol = nombreRol;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }
    
    public List<Rol> verRoles(){
        String consulta;
        ConexionBDD conexion = new ConexionBDD();
        consulta = "select * from rol";
        System.out.println(consulta);
        Connection con = conexion.conectar();
        try {
            List<Rol> roles = new ArrayList<>();
            Statement cn = con.createStatement();
            try {
                ResultSet rs = cn.executeQuery(consulta);
                while(rs.next()){
                    Rol rol = new Rol();
                    rol.setIdRol(rs.getInt("id_rol"));
                    rol.setNombreRol(rs.getString("nombre_rol"));
                    roles.add(rol);
                }
                return roles;
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
}
