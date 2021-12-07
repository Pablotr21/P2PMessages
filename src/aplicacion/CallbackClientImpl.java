package aplicacion;

import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;

/**
 * This class implements the remote interface 
 * CallbackClientInterface.
 * @author M. L. Liu
 */

public class CallbackClientImpl extends UnicastRemoteObject
     implements CallbackClientInterface{
    
    private String nombre;
    private ArrayList<String> amigos;
    private gui.FachadaGUI fgui;
  
   public CallbackClientImpl(gui.FachadaGUI fgui) throws RemoteException {
        super( );
        this.nombre = new String();
        this.amigos = new ArrayList<String>();
        this.fgui = fgui;
   }

    public String getNombre() throws RemoteException {
        return nombre;
    }

    public void setNombre(String nombre) throws RemoteException {
        this.nombre = nombre;
    }

    public ArrayList<String> getAmigos() throws RemoteException {
        return amigos;
    }

    public void setAmigos(ArrayList<String> amigos) throws RemoteException {
        this.amigos = amigos;
    }
    
    public void recibir(String mensaje) throws RemoteException {
        fgui.recibir(mensaje);
    }

   public String notifyMe(String nombre, String message) throws RemoteException {
        String returnMessage = "Tu amigo " + nombre + message + "\n";
        fgui.actividadAmigos(returnMessage);
        return returnMessage;
   }      

}// end CallbackClientImpl class    
