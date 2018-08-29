package modelo;

import java.io.*;
import modelo.*;

public class AccesoAleatorioProducto {

    private static RandomAccessFile flujo;
    private static int numeroRegistros;
    private static int tamañoRegistro = 100;

    public static RandomAccessFile getFlujo() {
        return flujo;
    }

    public static void setFlujo(RandomAccessFile flujo) {
        AccesoAleatorioProducto.flujo = flujo;
    }

    public static int getTamañoRegistro() {
        return tamañoRegistro;
    }

    public static void setTamañoRegistro(int tamañoRegistro) {
        AccesoAleatorioProducto.tamañoRegistro = tamañoRegistro;
    }

    public static void crearFileAlumno(File archivo) throws IOException {
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

    public static boolean setPersona(int i, producto producto) throws IOException {
        if (i >= 0 && i <= getNumeroRegistros()) {
            if (producto.getTamaño() > tamañoRegistro) {
                System.out.println("\nTamaño de registro excedido.");
            } else {
                flujo.seek(i * tamañoRegistro);
                flujo.writeUTF(producto.getNombre());
                System.out.println("id"+producto.getId());
                flujo.writeUTF(producto.getId());
                flujo.writeDouble(producto.getPrecio());
                flujo.writeInt(producto.getCantidad());
                flujo.writeBoolean(producto.isActivo());
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
            System.out.println(getPersona(i).isActivo());
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
        producto personaEliminada = getPersona(pos);
        personaEliminada.setActivo(false);
        setPersona(pos, personaEliminada);
        System.out.println("xd");

        return true;
    }

    public static void compactarArchivo(File archivo) throws IOException {
        crearFileAlumno(archivo); // Abrimos el flujo.
        producto[] listado = new producto[numeroRegistros];
        for (int i = 0; i < numeroRegistros; ++i) {
            listado[i] = getPersona(i);
        }
        cerrar(); // Cerramos el flujo.
        archivo.delete(); // Borramos el archivo.

        File tempo = new File("temporal.txt");
        crearFileAlumno(tempo); // Como no existe se crea.
        for (producto p : listado) {
            if (p.isActivo()) {
                añadirPersona(p);
            }
        }
        cerrar();

        tempo.renameTo(archivo); // Renombramos.
    }

    public static void añadirPersona(producto persona) throws IOException {
        int inactivo = buscarRegistroInactivo();
        if (setPersona(inactivo == -1 ? numeroRegistros : inactivo, persona)) {
            numeroRegistros++;
        }
    }

    public static int getNumeroRegistros() {
        return numeroRegistros;
    }

    public static producto getPersona(int i) throws IOException {
        if (i >= 0 && i <= getNumeroRegistros()) {
            flujo.seek(i * tamañoRegistro);

            return new producto(flujo.readUTF(), flujo.readUTF(), flujo.readDouble(),flujo.readInt(), flujo.readBoolean());
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
        producto p;

        if (buscado == null) {
            return -1;
        }
        for (int i = 0; i < getNumeroRegistros(); i++) {
            flujo.seek(i * tamañoRegistro);
            p = getPersona(i);
            System.out.println("------- registro");
           
            System.out.println(p.isActivo());
            
            if (p.getId().equals(buscado) && p.isActivo()) {
                System.out.println("exito");
                return i;
            }
        }
        return -1;
    }

}
