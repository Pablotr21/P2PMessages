package gui;

import aplicacion.CallbackClientInterface;
import aplicacion.CallbackServerInterface;

public class FachadaGUI {
    
    aplicacion.CallbackClient cl;
    
    public FachadaGUI(aplicacion.CallbackClient client) {
        this.cl = client;
    }
    
    public void iniciarSesion(CallbackClientInterface c, CallbackServerInterface s){
        VAutenticacion va;
        
        va = new VAutenticacion(c,s,this);
        va.setVisible(true);
    }
    
    public void iniciarPrincipal(CallbackClientInterface c, CallbackServerInterface s){
        VPrincipal vp;
        
        try {
            vp = new VPrincipal();
            vp.setCliente(c);
            vp.setServidor(s);
            vp.setVisible(true);
            vp.inicializarTablas(); //Deberíamos hacerlo dentro del VP quizás
        } catch (Exception ex) {
        }
    }
    
    public void iniciarVista (){
        VConexion vc;
        
        vc = new VConexion();
        vc.setVisible(true);
    }
}
