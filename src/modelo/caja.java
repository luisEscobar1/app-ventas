/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

/**
 *
 * @author LUIS
 */
public class caja implements Serializable {

    private SimpleStringProperty id;
    private SimpleDoubleProperty dinero;
    private boolean acctivo;

    public caja(String id, Double dinero, boolean activo) {
        this.id = new SimpleStringProperty(id);
        this.dinero = new SimpleDoubleProperty(dinero);
        this.acctivo = activo;
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

    public void setDinero(double dinero) {
        this.dinero = new SimpleDoubleProperty(dinero);
    }

    public boolean isAcctivo() {
        return acctivo;
    }

    public void setAcctivo(boolean acctivo) {
        this.acctivo = acctivo;
    }

    public int getTamaño() {
        return getId().length() * 2 + 2 + 4 + 1;
    }

    public double calP(Double p, int c) {
        double precio = p * c;
        return precio;
    }

    public double calS(ObservableList<producto> inven) {
        double sub = 0.0;
        for (int i = 0; i < inven.size(); i++) {
            sub = sub + inven.get(i).getPrecio();
        }
        return sub;
    }

    public double calI(double sub) {
        double iva = (sub * 0.18);
        return iva;
    }

    public double calT(double sub, double iva) {
        double total = sub + iva;
        return total;
    }

    public caja() {
    }

    public double calC(double P, double t) {
        double cambio = P - t;
        return P;
    }
    
    public double Agd(double d,double d1){
        double dolares=d+d1;
    return dolares;}
}
