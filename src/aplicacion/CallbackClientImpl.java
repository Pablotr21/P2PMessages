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
     implements CallbackClientInterface {
    
    private String nombre;
    private ArrayList<String> amigos;
    private gui.FachadaGUI fgui;
  
   public CallbackClientImpl(gui.FachadaGUI fgui) throws RemoteException {
      super( );
      this.nombre = new String();
      this.amigos = new ArrayList<String>();
      this.fgui = fgui;
   }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<String> getAmigos() {
        return amigos;
    }

    public void setAmigos(ArrayList<String> amigos) {
        this.amigos = amigos;
    }

   public String notifyMe(String nombre, String message){
      String returnMessage = "Tu amigo " + nombre + message + "\n";
      fgui.actividadAmigos(returnMessage);
      return returnMessage;
   }      

}// end CallbackClientImpl class   
