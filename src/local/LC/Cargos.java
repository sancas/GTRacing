/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package local.LC;

/**
 *
 * @author LeonIV
 */
public class Cargos {
    private int idcargo;
    private String cargo;

    public Cargos() {
    }

    public Cargos(int idcargo, String cargo) {
        this.idcargo = idcargo;
        this.cargo = cargo;
    }

    /**
     * @return the idcargo
     */
    public int getIdcargo() {
        return idcargo;
    }

    /**
     * @param idcargo the idcargo to set
     */
    public void setIdcargo(int idcargo) {
        this.idcargo = idcargo;
    }

    /**
     * @return the cargo
     */
    public String getCargo() {
        return cargo;
    }

    /**
     * @param cargo the cargo to set
     */
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    
    public String toString(){
        return cargo;
    }
}
