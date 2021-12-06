package baseDatos;

import aplicacion.FachadaAplicacion;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class FachadaBaseDatos {
    private java.sql.Connection conexion;
    private final FachadaAplicacion fa;
    
    
    public FachadaBaseDatos(aplicacion.FachadaAplicacion fa) {

        Properties configuracion = new Properties();
        this.fa = fa;
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

        } catch (FileNotFoundException f) {
            System.out.println(f.getMessage());
        } catch (IOException i) {
            System.out.println(i.getMessage());
        } catch (java.sql.SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}
