package aplicacion;

import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;

public class CallbackClientImpl extends UnicastRemoteObject
     implements CallbackClientInterface{
    
    private String nombre;
    private ArrayList<String> amigos;
    private gui.FachadaGUI fgui;
  
    public CallbackClientImpl(gui.FachadaGUI fgui) throws RemoteException {
        super();
        this.amigos = new ArrayList<>();
        this.fgui = fgui;
    }

    @Override
    public void actualizarTablasVP() throws RemoteException {
        this.fgui.actualizarTablas();
    }
    
    @Override
    public String getNombre() throws RemoteException {
        return nombre;
    }

    @Override
    public void setNombre(String nombre) throws RemoteException {
        this.nombre = nombre;
    }

    @Override
    public ArrayList<String> getAmigos() throws RemoteException {
        return amigos;
    }

    @Override
    public void setAmigos(ArrayList<String> amigos) throws RemoteException {
        this.amigos = amigos;
    }
    
    @Override
    public void recibir(String mensaje) throws RemoteException {
        fgui.recibir(mensaje);
    }

    @Override
   public String notifyMe(String nombre, String message) throws RemoteException {
        String returnMessage = "Tu amigo " + nombre + message + "\n";
        fgui.actividadAmigos(returnMessage);
        fgui.actualizarTablas();
        return returnMessage;
   }      

}// end CallbackClientImpl class    
