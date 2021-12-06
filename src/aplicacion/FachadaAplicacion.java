/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion;

/**
 *
 * @author alumnogreibd
 */
public class FachadaAplicacion {
    gui.FachadaGUI fgui;
    baseDatos.FachadaBaseDatos fbd;

    public FachadaAplicacion() {
        fgui = new gui.FachadaGUI(this);
        fbd = new baseDatos.FachadaBaseDatos(this);
    }
}
