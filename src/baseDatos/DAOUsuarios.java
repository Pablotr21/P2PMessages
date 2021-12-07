/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baseDatos;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author alumnogreibd
 */
public class DAOUsuarios extends AbstractDAO{
    
    public DAOUsuarios(Connection conexion) {
        super.setConexion(conexion);
    }
    
    public ArrayList<String> listaUsuarios(){
        ArrayList<String> resultado = new ArrayList<String>();
        Connection con;
        PreparedStatement stmUser = null;
        ResultSet rsUsuario;

        con = this.getConexion();

        try {
            stmUser = con.prepareStatement("SELECT nombre "
                    + "FROM cliente");
            rsUsuario = stmUser.executeQuery();
            while (rsUsuario.next()) {
                resultado.add(rsUsuario.getString("nombre"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                stmUser.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }
        return resultado;
    }
    
    public ArrayList<String> amigosUsuario(String u){
        Connection con;
        PreparedStatement stmUser = null;
        ResultSet rsUsuario;
        ArrayList<String> listAmig = new ArrayList<>();
        
        con = this.getConexion();

        try {
            stmUser = con.prepareStatement("SELECT *\n" +
            "FROM ((SELECT usuario2 as amigo\n" +
            "	FROM amistad\n" +
            "	WHERE usuario1 = ? AND aceptado = true)\n" +
            "	UNION\n" +
            "	(SELECT usuario1 as  amigo\n" +
            "	FROM amistad\n" +
            "	WHERE usuario2 = ? AND aceptado = true)) as amigo");
            stmUser.setString(1, u);
            stmUser.setString(2, u);
            
            rsUsuario = stmUser.executeQuery();
            while (rsUsuario.next()) {
                listAmig.add(rsUsuario.getString("amigo"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                stmUser.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }
        return listAmig;
    }
    
    public void nuevoUsuario(String nombre, String clave){
        PreparedStatement stmUsuario = null;
        Connection con;
        
        con = super.getConexion();

        try {
            stmUsuario = con.prepareStatement("insert into cliente(nombre, clave) "
                    + "values (?,?)");
            stmUsuario.setString(1, nombre);
            stmUsuario.setString(2, clave);
            stmUsuario.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                stmUsuario.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }
    }
    
    public void solicitarAmistad(String u1, String u2){
        PreparedStatement stmUsuario = null;
        Connection con;
        
        con = super.getConexion();

        try {
            stmUsuario = con.prepareStatement("insert into amistad(usuario1, usuario2, aceptado) "
                    + "values (?,?,false)");
            stmUsuario.setString(1, u1);
            stmUsuario.setString(2, u2);
            stmUsuario.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                stmUsuario.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }
    }
    
    public void aceptarAmistad(String u1, String u2){
        PreparedStatement stmUsuario = null;
        Connection con;
        
        con = super.getConexion();

        try {
            stmUsuario = con.prepareStatement("update amistad "
                    + "set aceptado = true "
                    + "where usuario1 = ? and usuario2 = ? ");
            stmUsuario.setString(1, u1);
            stmUsuario.setString(2, u2);
            stmUsuario.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                stmUsuario.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }
    }
    
    public boolean comprobarSesion(String nombre, String clave){
        boolean resultado = false;
        PreparedStatement stmUsuario = null;
        Connection con;
        ResultSet rsUsuario;
        
        con = super.getConexion();

        try {
            stmUsuario = con.prepareStatement("select * "
                    + "from cliente "
                    + "where nombre = ? and clave = ? ");
            stmUsuario.setString(1, nombre);
            stmUsuario.setString(2, clave);
            rsUsuario = stmUsuario.executeQuery();
            
            if (rsUsuario.next()) {
                resultado = true;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                stmUsuario.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }
        
        return resultado;
    }
}
