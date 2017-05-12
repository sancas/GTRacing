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
public class AutosLC {
    private int idAuto;
    private String modeloAuto;

    public AutosLC() {
    }

    public AutosLC(int idAuto, String modeloAuto) {
        this.idAuto = idAuto;
        this.modeloAuto = modeloAuto;
    }

    /**
     * @return the idAuto
     */
    public int getIdAuto() {
        return idAuto;
    }

    /**
     * @param idAuto the idAuto to set
     */
    public void setIdAuto(int idAuto) {
        this.idAuto = idAuto;
    }

    /**
     * @return the modeloAuto
     */
    public String getModeloAuto() {
        return modeloAuto;
    }

    /**
     * @param modeloAuto the modeloAuto to set
     */
    public void setModeloAuto(String modeloAuto) {
        this.modeloAuto = modeloAuto;
    }
    
    public String toString(){
        return modeloAuto;
    }
}
