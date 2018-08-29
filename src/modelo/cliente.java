/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author LUIS
 */
public class cliente implements Serializable {

    private SimpleStringProperty nombre;
    private SimpleStringProperty id;
    private SimpleDoubleProperty dinero;
    private boolean activo;

    public cliente(String nombre, String id, Double dinero, boolean activo) {
        this.nombre = new SimpleStringProperty(nombre);
        this.id = new SimpleStringProperty(id);;
        this.dinero = new SimpleDoubleProperty(dinero);
        this.activo = activo;
    }

    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(SimpleStringProperty nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id.get();
    }

    public void setId(SimpleStringProperty id) {
        this.id = id;
    }

    public Double getDinero() {
        return dinero.get();
    }

    public void setDinero(SimpleDoubleProperty dinero) {
        this.dinero = dinero;
    }

}
