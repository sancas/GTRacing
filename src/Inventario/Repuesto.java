/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inventario;

/**
 *
 * @author sanch
 */
public class Repuesto {

    private int id;
    private String nombre;

    public Repuesto(int id, String description) {
        this.id = id;
        this.nombre = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
