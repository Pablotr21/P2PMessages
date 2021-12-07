package aplicacion;

import java.io.*;
import java.rmi.*;

/**
 * This class represents the object client for a
 * distributed object of class CallbackServerImpl, 
 * which implements the remote interface 
 * CallbackServerInterface.  It also accepts callback
 * from the server.
 * 
 * 
 * 
 * @author M. L. Liu
 */

public class CallbackClient {
    
    gui.FachadaGUI fgui;

    public static void main(String args[]) {
        CallbackClient cl;
        cl = new CallbackClient();
        cl.iniciaInterfaz();
    } //end main

    public CallbackClient() {
        fgui = new gui.FachadaGUI(this);
    }
    
    public void iniciaInterfaz(){
        fgui.iniciarVista();
    }
}//end class
