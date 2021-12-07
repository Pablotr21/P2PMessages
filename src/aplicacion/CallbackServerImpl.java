package aplicacion;

import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

public class CallbackServerImpl extends UnicastRemoteObject
     implements CallbackServerInterface {

    private Vector clientList;
    private baseDatos.FachadaBaseDatos fbd;
    private HashMap<String,ArrayList<String>> amigos;//todos los usuarios con sus amigos


    public CallbackServerImpl(baseDatos.FachadaBaseDatos fbd) throws RemoteException {
        super( );
        clientList = new Vector();
        this.fbd = fbd;
        ArrayList<String> usuarios = this.fbd.listaUsuarios();
        amigos = new HashMap<>();
        for(String usuario : usuarios){
            amigos.put(usuario, fbd.amigosUsuario(usuario));
        }
    }
  
    @Override
    public boolean comprobarSesion(String nombre, String clave) throws RemoteException {
        return fbd.comprobarSesion(nombre,clave);
    }
  
    @Override
    public ArrayList<String> obtenerAmigosOnline(String nombre) throws RemoteException {
        ArrayList<String> online = new ArrayList<>();
        for(int i=0; i<clientList.size(); i++){
            CallbackClientInterface nextClient = 
          (CallbackClientInterface)clientList.elementAt(i);
            if(amigos.get(nombre).contains(nextClient.getNombre())){
                online.add(nextClient.getNombre());
            }
        }
        return online;
    }
    
    @Override
    public CallbackClientInterface obtenerPorNombre(String nombre) throws RemoteException {
        for(int i=0; i<clientList.size(); i++){
            CallbackClientInterface nextClient = 
          (CallbackClientInterface)clientList.elementAt(i);
            if(nextClient.getNombre().equals(nombre)){
                return nextClient;
            }
        }
        return null;
    }
    
    @Override
    public boolean nuevoUsuario(String nombre, String clave) throws RemoteException {
        boolean resultado = false;
        
        if(!this.amigos.keySet().contains(nombre)){
            fbd.nuevoUsuario(nombre, clave);
            amigos.put(nombre, new ArrayList<>());
            resultado = true;
        }
        
        return resultado;
    }
    
    @Override
    public void aceptarAmistad(String u1, String u2) throws RemoteException {
        fbd.aceptarAmistad(u1, u2);
        amigos.get(u1).add(u2);
        amigos.get(u2).add(u1);
        CallbackClientInterface c = obtenerPorNombre(u2);
        c.getFgui().actualizarTablas();
    }
    
    @Override
    public boolean solicitarAmistad(String u1, String u2) throws RemoteException {
        boolean resultado = false;
        
        if(this.amigos.keySet().contains(u1) && this.amigos.keySet().contains(u2)){
            fbd.solicitarAmistad(u1, u2);
            resultado = true;
        }
        
        
        return resultado;
    }
    
    @Override
    public ArrayList<String> obtenerSolicitudes(String nombre) throws RemoteException {
        return fbd.solicitudesUsuario(nombre);
    }
    
    @Override
    public synchronized void registerForCallback(
        CallbackClientInterface callbackClientObject)
        throws java.rmi.RemoteException{
          // store the callback object into the vector
          if (!(clientList.contains(callbackClientObject))) {
             clientList.addElement(callbackClientObject);
          System.out.println("Registered new client ");
          doCallbacks(callbackClientObject.getNombre(), " se ha conectado");
        } // end if
    }  

    // This remote method allows an object client to 
    // cancel its registration for callback
    // @param id is an ID for the client; to be used by
    // the server to uniquely identify the registered client.
    @Override
      public synchronized void unregisterForCallback(
        CallbackClientInterface callbackClientObject) 
        throws java.rmi.RemoteException{
        if (clientList.removeElement(callbackClientObject)) {
          System.out.println("Unregistered client ");
          doCallbacks(callbackClientObject.getNombre(), " se ha desconectado");
        } else {
           System.out.println(
             "unregister: clientwasn't registered.");
        }
      } 

    private synchronized void doCallbacks( String nombre, String msg ) throws java.rmi.RemoteException{
      // make callback to each registered client
      System.out.println(
         "**************************************\n"
          + "Callbacks initiated ---");
      for (int i = 0; i < clientList.size(); i++){
        System.out.println("doing "+ i +"-th callback\n");    
        // convert the vector object to a callback object
        CallbackClientInterface nextClient = 
          (CallbackClientInterface)clientList.elementAt(i);
        // invoke the callback method
          if(amigos.get(nextClient.getNombre()).contains(nombre)){
                nextClient.notifyMe(nombre, msg);
            }
      }// end for
      System.out.println("********************************\n" +
                         "Server completed callbacks ---");
    } // doCallbacks

}// end CallbackServerImpl class 
