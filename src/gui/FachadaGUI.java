package gui;

import aplicacion.CallbackClientInterface;
import aplicacion.CallbackServerInterface;

public class FachadaGUI {
    
    VPrincipal vp;
    VAutenticacion va;

    public FachadaGUI() {
        try {
            this.vp = new VPrincipal();
        } catch (Exception ex) {
        }
    }
    
    public void iniciarSesion(CallbackClientInterface c, CallbackServerInterface s){
        this.va = new VAutenticacion(c,s,this);
        this.va.setVisible(true);
    }
    
    public void iniciarPrincipal(CallbackClientInterface c, CallbackServerInterface s){
        vp.setCliente(c);
        vp.setServidor(s);
        vp.setVisible(true);
        vp.inicializarTablas();
    }
}
