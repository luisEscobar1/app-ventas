package modelo;

import java.io.*;
import modelo.*;

public class AccesoAleatorioAdministrador {

    private static RandomAccessFile flujo;
    private static int numeroRegistros;
    private static int tamañoRegistro = 100;

    public static RandomAccessFile getFlujo() {
        return flujo;
    }

    public static void setFlujo(RandomAccessFile flujo) {
        AccesoAleatorioAdministrador.flujo = flujo;
    }

    public static int getTamañoRegistro() {
        return tamañoRegistro;
    }

    public static void setTamañoRegistro(int tamañoRegistro) {
        AccesoAleatorioAdministrador.tamañoRegistro = tamañoRegistro;
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

    public static boolean setPersona(int i, administrador admon) throws IOException {
        if (i >= 0 && i <= getNumeroRegistros()) {
            if (admon.getTamaño() > tamañoRegistro) {
                System.out.println("\nTamaño de registro excedido.");
            } else {
                flujo.seek(i * tamañoRegistro);
                flujo.writeUTF(admon.getNombre());

                flujo.writeUTF(admon.getAplellido());

                flujo.writeUTF(admon.getId());
                flujo.writeUTF(admon.getUsuario());
                flujo.writeUTF(admon.getClave());
                flujo.writeBoolean(admon.isActivo());
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
        administrador adminEliminado = getPersona(pos);
        adminEliminado.setActivo(false);
        setPersona(pos, adminEliminado);
        System.out.println("xd");

        return true;
    }

    public static void compactarArchivo(File archivo) throws IOException {
        crearFileAdmon(archivo); // Abrimos el flujo.
        administrador[] listado = new administrador[numeroRegistros];
        for (int i = 0; i < numeroRegistros; ++i) {
            listado[i] = getPersona(i);
        }
        cerrar(); // Cerramos el flujo.
        archivo.delete(); // Borramos el archivo.

        File tempo = new File("temporal.txt");
        crearFileAdmon(tempo); // Como no existe se crea.
        for (administrador p : listado) {
            if (p.isActivo()) {
                añadirPersona(p);
            }
        }
        cerrar();

        tempo.renameTo(archivo); // Renombramos.
    }

    public static void añadirPersona(administrador administrador) throws IOException {
        int inactivo = buscarRegistroInactivo();
        if (setPersona(inactivo == -1 ? numeroRegistros : inactivo, administrador)) {
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

    public static administrador getPersona(int i) throws IOException {
        if (i >= 0 && i <= getNumeroRegistros()) {
            flujo.seek(i * tamañoRegistro);

            return new administrador(flujo.readUTF(), flujo.readUTF(), flujo.readUTF(), flujo.readUTF(), flujo.readUTF(), flujo.readBoolean());
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
        administrador p;

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
