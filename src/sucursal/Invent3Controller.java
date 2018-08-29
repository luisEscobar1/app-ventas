/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sucursal;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
public class Invent3Controller implements Initializable {

    @FXML
    private TextField nombre;
    @FXML
    private TextField id;
    @FXML
    private TextField precio;
    @FXML
    private TextField cantidad;
    @FXML
    private TableView<producto> table;
    @FXML
    private TableColumn<producto, String> nombre_tab;
    @FXML
    private TableColumn<producto, String> id_tab;
    @FXML
    private TableColumn<producto, Double> precio_tab;
    @FXML
    private TableColumn<producto, Integer> cantidad_tab;
    @FXML
    private Button guardar;
    @FXML
    private Button mostrar;
    @FXML
    private Button salir;
    private ObservableList<producto> inven;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        this.inicializarTabla();
    }

    private void inicializarTabla() {

        nombre_tab.setCellValueFactory(new PropertyValueFactory<producto, String>("nombre"));
        id_tab.setCellValueFactory(new PropertyValueFactory<producto, String>("id"));
        precio_tab.setCellValueFactory(new PropertyValueFactory<producto, Double>("precio"));
        cantidad_tab.setCellValueFactory(new PropertyValueFactory<producto, Integer>("cantidad"));
        inven = FXCollections.observableArrayList();
        table.setItems(inven);
    }

    @FXML
    private void Guardar_producto(ActionEvent event) throws IOException {
        
        System.out.println("id label"+id.getText());
        AccesoAleatorioProducto.crearFileAlumno(new File("inventario.dat"));
        producto p = new producto(nombre.getText(), id.getText(), Double.parseDouble(precio.getText()), Integer.parseInt(cantidad.getText()), true);
        AccesoAleatorioProducto.añadirPersona(p);
        AccesoAleatorioProducto.cerrar();
        inven.add(p);
    }

    @FXML
    private void mostrar_productos(ActionEvent event) throws IOException {
        AccesoAleatorioProducto.crearFileAlumno(new File("inventario.dat"));
        int a = AccesoAleatorioProducto.getNumeroRegistros();
        System.out.println(a);
        for (int i = 0; i < a; i++) {
            System.out.println(i);
            producto p = AccesoAleatorioProducto.getPersona(i);
            inven.add(p);
        }
        AccesoAleatorioProducto.cerrar();
    }

    @FXML
    private void Volver(ActionEvent event) throws IOException {
        Stage stage = (Stage) salir.getScene().getWindow();
        stage.close();
        Stage suc = new Stage();
        FXMLLoader loader = new FXMLLoader();
        AnchorPane root = (AnchorPane) loader.load(getClass().getResource("inter2.fxml").openStream());
        Scene ac = new Scene(root);
        suc.setScene(ac);
        suc.show();
    }

}
