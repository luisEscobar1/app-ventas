/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sucursal;



import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class FactController implements Initializable {

    @FXML
    private TextField buscador;
    @FXML
    private Button buscar;
    @FXML
    private TextArea area_fact;
    @FXML
    private Button generar;
    @FXML
    private Button salir;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Buscar_f(ActionEvent event) {
     JFileChooser dlg = new JFileChooser();
       int option = dlg.showSaveDialog(dlg);
        if (option == JFileChooser.APPROVE_OPTION) {
            File f = dlg.getSelectedFile();
            buscador.setText(f.toString());
        }
    }

    @FXML
    private void Generar_f(ActionEvent event) {
    String ruta = buscador.getText();
    String contenido = area_fact.getText();
    
    try{
    
        FileOutputStream archivo = new FileOutputStream(ruta+".pdf");
        Document doc = new Document();
        PdfWriter.getInstance(doc, archivo);
        doc.open();
        doc.add(new Paragraph(contenido));
        
        doc.close();
        
        JOptionPane.showMessageDialog(null, "pdf creado");
    }catch(Exception e){
        System.out.println("error"+e);
    }
    
    }
    @FXML
    private void Volver_caj(ActionEvent event) throws IOException {
    Stage stage = (Stage) salir.getScene().getWindow();
        stage.close();
        Stage rp = new Stage();
        FXMLLoader loader = new FXMLLoader();
        AnchorPane root = (AnchorPane) loader.load(getClass().getResource("caja.fxml").openStream());
        Scene ac = new Scene(root);
        rp.setScene(ac);
        rp.show();

    }
    
}
