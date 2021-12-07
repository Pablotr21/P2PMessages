package aplicacion;

import java.rmi.*;
import java.util.ArrayList;

/**
 * This is a remote interface for illustrating RMI 
 * client callback.
 * @author M. L. Liu
 */

public interface CallbackClientInterface extends java.rmi.Remote {
    // This remote method is invoked by a callback
    // server to make a callback to an client which
    // implements this interface.
    // @param message - a string containing information for the
    //                  client to process upon being called back.
    
    public String getNombre()
            throws java.rmi.RemoteException;

    public void setNombre(String nombre)
            throws java.rmi.RemoteException;

    public ArrayList<String> getAmigos()
            throws java.rmi.RemoteException;

    public void setAmigos(ArrayList<String> amigos)
            throws java.rmi.RemoteException;
    
    public void recibir(String mensaje)
            throws java.rmi.RemoteException;

    public String notifyMe(String nombre, String message) 
      throws java.rmi.RemoteException;

} // end interface
