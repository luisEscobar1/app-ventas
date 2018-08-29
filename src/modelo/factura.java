/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javafx.collections.ObservableList;

/**
 *
 * @author LUIS
 */
public class factura {

    private ObservableList<producto> inven;

    public ObservableList<producto> getInven() {
        return inven;
    }

    public void setInven(ObservableList<producto> inven) {
        this.inven = inven;
    }
    private Font fuenteB = new Font(Font.FontFamily.COURIER, 10, Font.BOLD);
    private Font fuenteN = new Font(Font.FontFamily.COURIER, 8, Font.NORMAL);

    public void generarPDF(String cabeza,String nombre,String id, String iva, String s, String t, String pago, String cambio, String salida) throws FileNotFoundException, DocumentException {
        try {
            Document document = new Document(PageSize.A6);
            PdfWriter.getInstance(document, new FileOutputStream(salida));
            document.open();
            document.add(getCabeza(cabeza));
            document.add(getCabeza("___________"));
            document.add(getInfo("cliente:" + nombre));
            document.add(getInfo("id:" + id));
            
            document.add(getInfo("             cambio:$" + cambio));
            document.add(getCabeza("___________"));
            document.add(getInfo("--------Articulo--------precio--------cant"));
            for (int i = 0; i < inven.size(); i++) {
                document.add(getInfo("         " + inven.get(i).getNombre() + "        $" + inven.get(i).getPrecio() + "         " + inven.get(i).getCantidad()));
            }
            document.add(getCabeza("___________"));
            document.add(getInfo("           subtotal:$" + s));
            document.add(getInfo("            IVA18%:$" + s));
            document.add(getInfo("             Total:$" + t));
            document.add(getInfo("      Pago Efectivo:$" + pago));
            document.add(getInfo("             cambio:$" + cambio));

            document.close();
        } catch (Exception e) {

        }

    }

    private Paragraph getCabeza(String texto) {
        Paragraph p = new Paragraph();
        Chunk c = new Chunk();
        p.setAlignment(Element.ALIGN_CENTER);
        c.append(texto);
        c.setFont(fuenteB);
        p.add(c);
        return p;
    }

    private Paragraph getInfo(String texto) {
        Paragraph p = new Paragraph();
        Chunk c = new Chunk();
        p.setAlignment(Element.ALIGN_JUSTIFIED);
        c.append(texto);
        c.setFont(fuenteN);
        p.add(c);
        return p;
    }

}
