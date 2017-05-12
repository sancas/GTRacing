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
public class Repuestos_LC {
    private int idRepuesto;
    private String nombreRepuesto;
    
    public Repuestos_LC(){}
    
    public Repuestos_LC(int id, String marca){
        this.idRepuesto = id;
        this.nombreRepuesto = marca;
    }

    /**
     * @return the idRepuesto
     */
    public int getIdRepuesto() {
        return idRepuesto;
    }

    /**
     * @param idRepuesto the idRepuesto to set
     */
    public void setIdRepuesto(int idRepuesto) {
        this.idRepuesto = idRepuesto;
    }

    /**
     * @return the nombreRepuesto
     */
    public String getNombreRepuesto() {
        return nombreRepuesto;
    }

    /**
     * @param nombreRepuesto the nombreRepuesto to set
     */
    public void setNombreRepuesto(String nombreRepuesto) {
        this.nombreRepuesto = nombreRepuesto;
    }
    
    public String toString(){
        return nombreRepuesto;
    }
}
