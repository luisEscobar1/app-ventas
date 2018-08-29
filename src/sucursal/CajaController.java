/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sucursal;

import com.itextpdf.text.DocumentException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import modelo.*;

/**
 * FXML Controller class
 *
 * @author LUIS
 */
public class CajaController implements Initializable {

    @FXML
    private TextField id;
    @FXML
    private TextField Uvendedor;
    @FXML
    private TextField nombre;
    @FXML
    private TextField idProducto;
    @FXML
    private Label NombreP;
    @FXML
    private TextField id_cliente;
    @FXML
    private Label precio;
    @FXML
    private TextField dinero;
    @FXML
    private Button buscar;
    @FXML
    private TextField cantidad;
    @FXML
    private Button registrar;
    @FXML
    private TableView<producto> table;
    @FXML
    private TableColumn<producto, String> nombre_tab;
    @FXML
    private TableColumn<producto, Double> precio_tab;
    @FXML
    private TableColumn<producto, String> codigo_de_barras_tabl;
    @FXML
    private TableColumn<producto, Integer> tab_cantidad;
    @FXML
    private Label Subtotal;
    @FXML
    private Label total;
    @FXML
    private Button facturar;
    @FXML
    private Button cancelar;
    @FXML
    private Button salir;
    @FXML
    private Label IVA;
    caja a = new caja();
    private ObservableList<producto> inven;
    factura f = new factura();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.inicializarTablaPersonas();

    }

    @FXML
    private void BuscarP(ActionEvent event) throws IOException {
        AccesoAleatorioProducto.crearFileAlumno(new File("inventario.dat"));
        int a = AccesoAleatorioProducto.buscarRegistro(idProducto.getText());
        System.out.println("entro");
        System.out.println(a);

        if (a != -1) {
            System.out.println("entrox2");
            producto p = AccesoAleatorioProducto.getPersona(a);
            NombreP.setText(p.getNombre());
            precio.setText(String.valueOf(p.getPrecio()));

        }

    }

    @FXML
    private void registrarP(ActionEvent event) {
        String n = NombreP.getText();
        String Idp = idProducto.getText();
        double pre = a.calP(Double.parseDouble(precio.getText()), Integer.parseInt(cantidad.getText()));
        int c = Integer.parseInt(cantidad.getText());
        producto p = new producto(n, Idp, pre, c, true);
        inven.add(p);
        Subtotal.setText(String.valueOf(a.calS(inven)));
        IVA.setText(String.valueOf(a.calI(Double.parseDouble(Subtotal.getText()))));
        total.setText(String.valueOf(a.calT(Double.parseDouble(Subtotal.getText()), Double.parseDouble(IVA.getText()))));
        idProducto.setText("");
        cantidad.setText("");
        NombreP.setText("");
    }

    @FXML
    private void facturar_Venta(ActionEvent event) throws FileNotFoundException, DocumentException, IOException {
        f.setInven(inven);
        double cam = a.calC(Double.parseDouble(total.getText()), Double.parseDouble(dinero.getText()));
        f.generarPDF("FACTURA", nombre.getText(), id_cliente.getText(), IVA.getText(), Subtotal.getText(), total.getText(), dinero.getText(), String.valueOf(cam), "factura.pdf");
        AccesoAleatorioVendedor.crearFileVen(new File("vendedor.dat"));
        int b = AccesoAleatorioVendedor.buscarRegistro(Uvendedor.getText());
        if (b != 1) {
            vendedor v = AccesoAleatorioVendedor.getPersona(b);

            AccesoAleatorioVendedor.eliminarPersona(Uvendedor.getText());
            v.setCventas(v.AgV(v.getCventas()));
            AccesoAleatorioVendedor.añadirPersona(v);
            AccesoAleatorioVendedor.cerrar();

        }
        AccesoCaja.crearFileAdmon(new File("caja.dat"));
        int c = AccesoCaja.buscarRegistro(id.getText());
        if (b != 1) {
            caja v = AccesoCaja.getPersona(b);

            AccesoCaja.eliminarPersona(id.getText());
            v.Agd(Double.parseDouble(total.getText()), v.getDinero());
            v.setDinero(cam);
            System.out.println(v.getDinero());
            AccesoCaja.añadirPersona(v);
            AccesoCaja.cerrar();

        }

        nombre.setText("");
        id_cliente.setText("");
        dinero.setText("");
        Subtotal.setText("");
        total.setText("");
        IVA.setText("");
        inven.clear();

    }

    @FXML
    private void Cancelar_Venta(ActionEvent event) {
        nombre.setText("");
        id_cliente.setText("");
        dinero.setText("");
        Subtotal.setText("");
        total.setText("");
        IVA.setText("");
        inven.clear();
        idProducto.setText("");
        cantidad.setText("");
        NombreP.setText("");
    }

    @FXML
    private void Volver(ActionEvent event) throws IOException {

        Stage stage = (Stage) salir.getScene().getWindow();
        stage.close();
        Stage suc = new Stage();
        FXMLLoader loader = new FXMLLoader();
        AnchorPane root = (AnchorPane) loader.load(getClass().getResource("sucursal.fxml").openStream());
        Scene ac = new Scene(root);
        suc.setScene(ac);
        suc.show();
    }

    private void inicializarTablaPersonas() {
        nombre_tab.setCellValueFactory(new PropertyValueFactory<producto, String>("nombre"));
        precio_tab.setCellValueFactory(new PropertyValueFactory<producto, Double>("precio"));
        codigo_de_barras_tabl.setCellValueFactory(new PropertyValueFactory<producto, String>("id"));
        tab_cantidad.setCellValueFactory(new PropertyValueFactory<producto, Integer>("cantidad"));
        inven = FXCollections.observableArrayList();
        table.setItems(inven);
    }
}
