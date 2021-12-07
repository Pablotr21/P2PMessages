package aplicacion;

import java.rmi.*;
import java.util.ArrayList;
//Hola
/**
 * This is a remote interface for illustrating RMI 
 * client callback.
 * @author M. L. Liu
 */

public interface CallbackServerInterface extends Remote {

    public String sayHello( )   
      throws java.rmi.RemoteException;

    // This remote method allows an object client to 
    // register for callback
    // @param callbackClientObject is a reference to the
    //        object of the client; to be used by the server
    //        to make its callbacks.

    public void registerForCallback(
      CallbackClientInterface callbackClientObject
      ) throws java.rmi.RemoteException;

    // This remote method allows an object client to 
    // cancel its registration for callback

    public void unregisterForCallback(
      CallbackClientInterface callbackClientObject)
      throws java.rmi.RemoteException;
  
    public boolean comprobarSesion(String nombre, String clave)
            throws java.rmi.RemoteException;

    public ArrayList<String> obtenerAmigosOnline(String nombre)
            throws java.rmi.RemoteException;
    
    public CallbackClientInterface obtenerPorNombre(String nombre)
            throws java.rmi.RemoteException;
}
