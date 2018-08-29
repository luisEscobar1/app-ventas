/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author LUIS
 */
public class AccesoCaja {
    
    private static RandomAccessFile flujo;
    private static int numeroRegistros;
    private static int tamañoRegistro = 100;
    
    public static RandomAccessFile getFlujo() {
        return flujo;
    }
    
    public static void setFlujo(RandomAccessFile flujo) {
        AccesoCaja.flujo = flujo;
    }
    
    public static int getTamañoRegistro() {
        return tamañoRegistro;
    }
    
    public static void setTamañoRegistro(int tamañoRegistro) {
        AccesoCaja.tamañoRegistro = tamañoRegistro;
    }
    
    public static void crearFileAdmon(File archivo) throws IOException {
        if (archivo.exists() && !archivo.isFile()) {
            throw new IOException(archivo.getName() + " no es un archivo");
        }
        flujo = new RandomAccessFile(archivo, "rw");
        numeroRegistros = (int) Math.ceil(
                (double) flujo.length() / (double) tamañoRegistro);
    }
    
    public static void cerrar() throws IOException {
        flujo.close();
    }
    
    public static boolean setPersona(int i, caja caja) throws IOException {
        if (i >= 0 && i <= getNumeroRegistros()) {
            if (caja.getTamaño() > tamañoRegistro) {
                System.out.println("\nTamaño de registro excedido.");
            } else {
                flujo.seek(i * tamañoRegistro);
                flujo.writeUTF(caja.getId());
                flujo.writeDouble(caja.getDinero());
                flujo.writeBoolean(caja.isAcctivo());
                return true;
            }
        } else {
            System.out.println("\nNúmero de registro fuera de límites.");
        }
        return false;
    }
    
    private static int buscarRegistroInactivo() throws IOException {
        String nombre;
        for (int i = 0; i < getNumeroRegistros(); i++) {
            flujo.seek(i * tamañoRegistro);
            if (!getPersona(i).isAcctivo()) {
                return i;
            }
        }
        return -1;
    }
    
    public static boolean eliminarPersona(String aEliminar) throws IOException {
        int pos = buscarRegistro(aEliminar);
        
        if (pos == -1) {
            
            return false;
        }
        caja cajaEliminado = getPersona(pos);
        cajaEliminado.setAcctivo(false);
        setPersona(pos, cajaEliminado);
        System.out.println("xd");
        
        return true;
    }
    
    public static void compactarArchivo(File archivo) throws IOException {
        crearFileAdmon(archivo); // Abrimos el flujo.
       caja[] listado = new caja[numeroRegistros];
        for (int i = 0; i < numeroRegistros; ++i) {
            listado[i] = getPersona(i);
        }
        cerrar(); // Cerramos el flujo.
        archivo.delete(); // Borramos el archivo.

        File tempo = new File("temporal.txt");
        crearFileAdmon(tempo); // Como no existe se crea.
        for (caja p : listado) {
            if (p.isAcctivo()) {
                añadirPersona(p);
            }
        }
        cerrar();
        
        tempo.renameTo(archivo); // Renombramos.
    }
    
    public static void añadirPersona(caja caja) throws IOException {
        int inactivo = buscarRegistroInactivo();
        if (setPersona(inactivo == -1 ? numeroRegistros : inactivo, caja)) {
            numeroRegistros++;
        }
    }

    /**
     *
     * @return
     */
    public static int getNumeroRegistros() {
        return numeroRegistros;
    }
    
    public static caja getPersona(int i) throws IOException {
        if (i >= 0 && i <= getNumeroRegistros()) {
            flujo.seek(i * tamañoRegistro);
            
            return new caja(flujo.readUTF(),flujo.readDouble(), flujo.readBoolean());
        } else {
            System.out.println("\nNúmero de registro fuera de límites.");
            return null;
        }
    }
    
    public static RandomAccessFile getf(int i) throws IOException {
        if (i >= 0 && i <= getNumeroRegistros()) {
            flujo.seek(i * tamañoRegistro);
            
            return flujo;
        } else {
            System.out.println("\nNúmero de registro fuera de límites.");
            return null;
        }
    }
    
    public static int buscarRegistro(String buscado) throws IOException {
        caja p;
        
        if (buscado == null) {
            return -1;
        }
        for (int i = 0; i < getNumeroRegistros(); i++) {
            flujo.seek(i * tamañoRegistro);
            p = getPersona(i);
            System.out.println("------- registro");
            System.out.println(p.getId());
            System.out.println(p.isAcctivo());
            System.out.println(buscado);
            if (p.getId().equals(buscado) && p.isAcctivo()) {
                System.out.println("exito");
                return i;
            }
        }
        return -1;
    }
    
}
