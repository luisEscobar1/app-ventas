/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author LUIS
 */
public class producto implements Serializable {

    private SimpleStringProperty nombre;
    private SimpleStringProperty id;
    private SimpleDoubleProperty precio;
    private SimpleIntegerProperty cantidad;
    private boolean activo;

    public producto(String nombre, String id, double precio, Integer cantidad, boolean activo) {
        this.nombre = new SimpleStringProperty(nombre);
        this.id = new SimpleStringProperty(id);
        this.precio = new SimpleDoubleProperty(precio);
        this.cantidad = new SimpleIntegerProperty(cantidad);
        this.activo = activo;
    }

    public Integer getCantidad() {
        return cantidad.get();
    }

    public void setCantidad(SimpleIntegerProperty cantidad) {
        this.cantidad = cantidad;
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

    public Double getPrecio() {
        return precio.get();
    }

    public void setPrecio(SimpleDoubleProperty precio) {
        this.precio = precio;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public int getTamaño() {
        return getId().length() * 2 + 2 + 4 + 1;
    }
    
    
}
