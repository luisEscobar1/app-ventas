/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author LUIS
 */
public class ven {

    private SimpleStringProperty nombre;
    private SimpleIntegerProperty Cventas;
    private SimpleDoubleProperty porcent;

    public ven(String nombre, Integer Cventas, double porcent) {
        this.nombre = new SimpleStringProperty(nombre);
        this.Cventas = new SimpleIntegerProperty(Cventas);
        this.porcent = new SimpleDoubleProperty(porcent);
    }

    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre = new SimpleStringProperty(nombre);
    }

    public Integer getCventas() {
        return Cventas.get();
    }

    public void setCventas(Integer Cventas) {
        this.Cventas = new SimpleIntegerProperty(Cventas);
    }

    public Double getPorcent() {
        return porcent.get();
    }

    public void setPorcent(Double porcent) {
        this.porcent = new SimpleDoubleProperty(porcent);
    }

}
