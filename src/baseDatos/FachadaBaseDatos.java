package baseDatos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;


public class FachadaBaseDatos {
    private java.sql.Connection conexion;  
    private DAOUsuarios u;
    
    public FachadaBaseDatos() {

        Properties configuracion = new Properties();
        FileInputStream arqConfiguracion;

        try {
            arqConfiguracion = new FileInputStream("baseDatos.properties"); //linea a cambiar
            //arqConfiguracion = new FileInputStream("basesDatos.properties");

            configuracion.load(arqConfiguracion);
            arqConfiguracion.close();

            Properties usuario = new Properties();

            String gestor = configuracion.getProperty("gestor");

            usuario.setProperty("user", configuracion.getProperty("usuario"));
            usuario.setProperty("password", configuracion.getProperty("clave"));
            this.conexion = java.sql.DriverManager.getConnection("jdbc:" + gestor + "://"
                    + configuracion.getProperty("servidor") + ":"
                    + configuracion.getProperty("puerto") + "/"
                    + configuracion.getProperty("baseDatos"),
                    usuario);
            u = new DAOUsuarios(conexion);
            
        } catch (FileNotFoundException f) {
            System.out.println(f.getMessage());
        } catch (IOException i) {
            System.out.println(i.getMessage());
        } catch (java.sql.SQLException e) {
            System.out.println(e.getMessage());
        }

    }
    
    public boolean comprobarSesion(String nombre, String clave){
        return u.comprobarSesion(nombre,clave);
    }
    
    public ArrayList<String> listaUsuarios(){
        return u.listaUsuarios();
    }
    
    public ArrayList<String> amigosUsuario(String user){
        return u.amigosUsuario(user);
    }
    
    public void nuevoUsuario(String nombre, String clave){
        u.nuevoUsuario(nombre, clave);
    }
    
    public ArrayList<String> solicitudesUsuario(String user){
        return u.solicitudesUsuario(user);
    }
    
    public void aceptarAmistad(String u1, String u2){
        u.aceptarAmistad(u1, u2);
    }
    
    public void solicitarAmistad(String u1, String u2){
        u.solicitarAmistad(u1, u2);
    }
    
}
