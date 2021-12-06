/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baseDatos;

import aplicacion.Usuario;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author alumnogreibd
 */
public class DAOUsuarios extends AbstractDAO{
    
    public DAOUsuarios(Connection conexion, aplicacion.FachadaAplicacion fa) {
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }
    
    public ArrayList<Usuario> listaUsuarios(){
        ArrayList<Usuario> resultado = new ArrayList<Usuario>();
        Usuario u;
        Connection con;
        PreparedStatement stmUser = null;
        ResultSet rsUsuario;

        con = this.getConexion();

        try {
            stmUser = con.prepareStatement("SELECT nombre, clave "
                    + "FROM cliente");
            rsUsuario = stmUser.executeQuery();
            while (rsUsuario.next()) {
                u = new Usuario(rsUsuario.getString("nombre"), rsUsuario.getString("clave"));
                resultado.add(u);
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
    
    public void amigosUsuario(Usuario u){
        Connection con;
        PreparedStatement stmUser = null;
        ResultSet rsUsuario;
        ArrayList<String> listAmig;
        
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
            stmUser.setString(1, u.getNombre());
            stmUser.setString(2, u.getNombre());
            
            rsUsuario = stmUser.executeQuery();
            listAmig = u.getAmigos();
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
    }
    
    public void nuevoUsuario(Usuario u){
        PreparedStatement stmUsuario = null;
        Connection con;
        
        con = super.getConexion();

        try {
            stmUsuario = con.prepareStatement("insert into cliente(nombre, clave) "
                    + "values (?,?)");
            stmUsuario.setString(1, u.getNombre());
            stmUsuario.setString(2, u.getClave());
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
}
