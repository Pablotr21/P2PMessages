package gui;

import aplicacion.CallbackClientInterface;
import aplicacion.CallbackServerInterface;

public class FachadaGUI {
    
    aplicacion.CallbackClient cl;
    VPrincipal vp;
    
    public FachadaGUI(aplicacion.CallbackClient client) {
        this.cl = client;
        vp = new VPrincipal();
    }
    
    public void iniciarSesion(CallbackClientInterface c, CallbackServerInterface s){
        VAutenticacion va;
        
        va = new VAutenticacion(c,s,this);
        va.setVisible(true);
    }
    
    public void iniciarPrincipal(CallbackClientInterface c, CallbackServerInterface s){
        try {
            vp.setCliente(c);
            vp.setServidor(s);
            vp.setVisible(true);
            vp.inicializarTablas(); //Deberíamos hacerlo dentro del VP quizás
        } catch (Exception ex) {
        }
    }
    
    public void actividadAmigos(String msg){
        vp.actividadAmigos(msg);
    }
    
    public void iniciarVista (){
        VConexion vc;
        
        vc = new VConexion(this);
        vc.setVisible(true);
    }
}
