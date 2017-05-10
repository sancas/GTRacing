/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package local.LC;

/**
 *
 * @author Leonel
 */
public class Empleados {
    private int idempleado;
    private String dui;

    public Empleados() {
    }

    public Empleados(int id, String dui) {
        this.dui = dui;
        this.idempleado = id;
    }

    /**
     * @return the dui
     */
    public String getDui() {
        return dui;
    }

    /**
     * @param dui the dui to set
     */
    public void setDui(String dui) {
        this.dui = dui;
    }

    /**
     * @return the idempleado
     */
    public int getIdempleado() {
        return idempleado;
    }

    /**
     * @param idempleado the idempleado to set
     */
    public void setIdempleado(int idempleado) {
        this.idempleado = idempleado;
    }

    @Override
    public String toString() {
        return dui;
    }
}
