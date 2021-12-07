package aplicacion;

import java.io.*;
import java.rmi.*;

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
