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
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.*;

/**
 * FXML Controller class
 *
 * @author LUIS
 */
public class ReportController implements Initializable {

    @FXML
    private TableView<producto> table_VP;
    @FXML
    private TableColumn<producto, String> tableproducNombre;
    @FXML
    private TableColumn<producto, Integer> tableProduc;
    @FXML
    private TableColumn<?, ?> tablePorProduct;
    @FXML
    private Button actualizar_VP;
    @FXML
    private Button graficar_VP;
    @FXML
    private PieChart grafica_VP;
    @FXML
    private TableView<ven> table_VV;
    @FXML
    private TableColumn<ven, String> tableVnombre;
    @FXML
    private TableColumn<ven, Integer> tableCventas;
    @FXML
    private TableColumn<ven, Double> tablePvendedor;
    @FXML
    private Button actualizar_VV;
    @FXML
    private Button graficar_VV;
    @FXML
    private StackedBarChart<vendedor, Integer> grafica_VV;
    @FXML
    private NumberAxis yNventas;
    @FXML
    private CategoryAxis xNombre;
    @FXML
    private TableView<?> table_EV;
    @FXML
    private TableColumn<?, ?> tableMes;
    @FXML
    private TableColumn<?, ?> tableMventas;
    @FXML
    private Button actualizar_EV;
    @FXML
    private Button graficar_EV;
    @FXML
    private CategoryAxis xmes;
    @FXML
    private TableView<?> table_CM_1;
    @FXML
    private Button actualizar_CM;
    @FXML
    private Button graficar_CM;
    @FXML
    private TableView<?> table_CM_2;
    @FXML
    private StackedBarChart<?, ?> grafica_CM;
    @FXML
    private Button salir;
    private ObservableList<ven> inven  ;
    private ArrayList<vendedor> vendedor=new ArrayList<vendedor>();
 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.inicializarTablaVendedores();
    }

    @FXML
    private void Act_VP(ActionEvent event) {
    }

    @FXML
    private void Graf_VP(ActionEvent event) {
    }

    @FXML
    private void Act_VV(ActionEvent event) throws IOException {
        int total = 0;
        AccesoAleatorioVendedor.crearFileVen(new File("vendedor.dat"));
        int a =  AccesoAleatorioVendedor.getNumeroRegistros();
        System.out.println(a);
        for (int i = 0; i < a; i++) {
            System.out.println(i);
            vendedor p = AccesoAleatorioVendedor.getPersona(i);
            vendedor.add(p);
            total = total + p.getCventas();
        }
         AccesoAleatorioVendedor.cerrar();
        for (int i = 0; i < vendedor.size(); i++) {

            vendedor p = vendedor.get(i);
            ven v = new ven(p.getNombre(), p.getCventas(), p.calP(total, p.getCventas()));
            inven.add(v);
        }
    }

    @FXML
    private void Graf_VV(ActionEvent event) {
    }

    @FXML
    private void Act_EV(ActionEvent event) {
    }

    @FXML
    private void Graf_EV(ActionEvent event) {
    }

    @FXML
    private void Act_CM(ActionEvent event) {
    }

    @FXML
    private void Graf_CM(ActionEvent event) {
    }

    @FXML
    private void Volver(ActionEvent event) {
    }

    private void inicializarTablaVendedores() {

        tableVnombre.setCellValueFactory(new PropertyValueFactory<ven, String>("nombre"));
        tableCventas.setCellValueFactory(new PropertyValueFactory<ven, Integer>("Cventas"));
        tablePvendedor.setCellValueFactory(new PropertyValueFactory<ven, Double>("porcent"));
        inven = FXCollections.observableArrayList();
        table_VV.setItems(inven);
    }

}
