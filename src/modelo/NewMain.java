/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author LUIS
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        AccesoCaja.crearFileAdmon(new File("caja.dat"));
        AccesoCaja.añadirPersona(new caja("1", 0.0, true));
        AccesoCaja.añadirPersona(new caja("2", 0.0, true));
        AccesoCaja.añadirPersona(new caja("3", 0.0, true));
    }

}
