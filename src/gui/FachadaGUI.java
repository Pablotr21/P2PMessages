package gui;

public class FachadaGUI {
    
    aplicacion.FachadaAplicacion fa;
    VPrincipal vp;

    public FachadaGUI(aplicacion.FachadaAplicacion fa) {
        try {
            this.fa = fa;
            this.vp = new VPrincipal();
        } catch (Exception ex) {
        }
    }
}
