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
  
   public CallbackClientImpl() throws RemoteException {
      super( );
      this.nombre = new String();
      this.amigos = new ArrayList<String>();
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

   public String notifyMe(String message){
      String returnMessage = "Call back received: " + message;
      System.out.println(returnMessage);
      return returnMessage;
   }      

}// end CallbackClientImpl class   
