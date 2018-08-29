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
public class AccesoAleatorioVendedor {

    private static RandomAccessFile flujo;
    private static int numeroRegistros;
    private static int tamañoRegistro = 100;

    public static RandomAccessFile getFlujo() {
        return flujo;
    }

    public static void setFlujo(RandomAccessFile flujo) {
        AccesoAleatorioVendedor.flujo = flujo;
    }

    public static int getTamañoRegistro() {
        return tamañoRegistro;
    }

    public static void setTamañoRegistro(int tamañoRegistro) {
        AccesoAleatorioVendedor.tamañoRegistro = tamañoRegistro;
    }

    public static void crearFileVen(File archivo) throws IOException {
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

    public static boolean setPersona(int i, vendedor vendedor) throws IOException {
        if (i >= 0 && i <= getNumeroRegistros()) {
            if (vendedor.getTamaño() > tamañoRegistro) {
                System.out.println("\nTamaño de registro excedido.");
            } else {
                flujo.seek(i * tamañoRegistro);
                flujo.writeUTF(vendedor.getNombre());

                flujo.writeUTF(vendedor.getAplellido());

                flujo.writeUTF(vendedor.getId());
                flujo.writeUTF(vendedor.getUsuario());
                flujo.writeUTF(vendedor.getClave());
                 flujo.writeInt(vendedor.getCventas());
                flujo.writeBoolean(vendedor.isActivo());
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
            if (!getPersona(i).isActivo()) {
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
        vendedor venEliminado = getPersona(pos);
        venEliminado.setActivo(false);
        setPersona(pos, venEliminado);
        System.out.println("xd");

        return true;
    }

    public static void compactarArchivo(File archivo) throws IOException {
        crearFileVen(archivo); // Abrimos el flujo.
        vendedor[] listado = new vendedor[numeroRegistros];
        for (int i = 0; i < numeroRegistros; ++i) {
            listado[i] = getPersona(i);
        }
        cerrar(); // Cerramos el flujo.
        archivo.delete(); // Borramos el archivo.

        File tempo = new File("temporal.txt");
        crearFileVen(tempo); // Como no existe se crea.
        for (vendedor p : listado) {
            if (p.isActivo()) {
                añadirPersona(p);
            }
        }
        cerrar();

        tempo.renameTo(archivo); // Renombramos.
    }

    public static void añadirPersona(vendedor vendedor) throws IOException {
        int inactivo = buscarRegistroInactivo();
        if (setPersona(inactivo == -1 ? numeroRegistros : inactivo, vendedor)) {
            numeroRegistros++;
        }
    }

    public static int getNumeroRegistros() {
        return numeroRegistros;
    }

    public static vendedor getPersona(int i) throws IOException {
        if (i >= 0 && i <= getNumeroRegistros()) {
            flujo.seek(i * tamañoRegistro);
            
            return new vendedor(flujo.readUTF(), flujo.readUTF(), flujo.readUTF(), flujo.readUTF(), flujo.readUTF(),flujo.readInt(), flujo.readBoolean());
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
        vendedor p;

        if (buscado == null) {
            return -1;
        }
        for (int i = 0; i < getNumeroRegistros(); i++) {
            flujo.seek(i * tamañoRegistro);
            p = getPersona(i);
            System.out.println("------- registro");
            System.out.println(p.getNombre());
            System.out.println(p.isActivo());
            System.out.println(buscado);
            if (p.getNombre().equals(buscado) && p.isActivo()) {
                System.out.println("exito");
                return i;
            }
        }
        return -1;
    }

}
