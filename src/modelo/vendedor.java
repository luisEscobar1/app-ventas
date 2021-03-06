/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author LUIS
 */
public class vendedor implements Serializable {

    private SimpleStringProperty nombre;
    private SimpleStringProperty aplellido;
    private SimpleStringProperty id;
    private SimpleStringProperty usuario;
    private SimpleStringProperty clave;
    private int Cventas;
    private boolean activo;

    public vendedor(String nombre, String aplellido, String id, String usuario, String clave, int Cventas, boolean activo) {
        this.nombre = new SimpleStringProperty(nombre);
        this.aplellido = new SimpleStringProperty(aplellido);
        this.id = new SimpleStringProperty(id);
        this.usuario = new SimpleStringProperty(usuario);
        this.clave = new SimpleStringProperty(clave);
        this.Cventas = Cventas;
        this.activo = activo;
    }

    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(SimpleStringProperty nombre) {
        this.nombre = nombre;
    }

    public String getAplellido() {
        return aplellido.get();
    }

    public void setAplellido(SimpleStringProperty aplellido) {
        this.aplellido = aplellido;
    }

    public String getId() {
        return id.get();
    }

    public void setId(SimpleStringProperty id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario.get();
    }

    public void setUsuario(SimpleStringProperty usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave.get();
    }

    public void setClave(SimpleStringProperty clave) {
        this.clave = clave;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public int getTamaño() {
        return getUsuario().length() * 2 + 2 + 4 + 1;
    }

    public int getCventas() {
        return Cventas;
    }

    public void setCventas(int Cventas) {
        this.Cventas = Cventas;
    }

    public int AgV(int a) {
        int ag = 1 + a;
        return ag;
    }

    public double calP(int total, int cant) {

        double porce = (cant * 100) / total;
        return porce;
    }
}
