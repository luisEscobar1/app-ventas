/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sucursal;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class Inter2Controller implements Initializable {

    @FXML
    private Button dineroEnCaja;
    @FXML
    private Button reportes;
    @FXML
    private Button inventario;
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
    private void Dinero_Caja(ActionEvent event) throws IOException {
        Stage stage = (Stage) dineroEnCaja.getScene().getWindow();
        stage.close();
        Stage dc = new Stage();
        FXMLLoader loader = new FXMLLoader();
        AnchorPane root = (AnchorPane) loader.load(getClass().getResource("dineroEnCaja.fxml").openStream());
        Scene ac = new Scene(root);
        dc.setScene(ac);
        dc.show();

    }

    @FXML
    private void Ir_Vista_Reportes(ActionEvent event) throws IOException {
        Stage stage = (Stage) reportes.getScene().getWindow();
        stage.close();
        Stage rp = new Stage();
        FXMLLoader loader = new FXMLLoader();
        AnchorPane root = (AnchorPane) loader.load(getClass().getResource("report2.fxml").openStream());
        Scene ac = new Scene(root);
        rp.setScene(ac);
        rp.show();

    }

    @FXML
    private void Ir_Vista_Inventario(ActionEvent event) throws IOException {
        Stage stage = (Stage) salir.getScene().getWindow();
        stage.close();
        Stage invent = new Stage();
        FXMLLoader loader = new FXMLLoader();
        AnchorPane root = (AnchorPane) loader.load(getClass().getResource("invent3.fxml").openStream());
        Scene ac = new Scene(root);
        invent.setScene(ac);
        invent.show();

    }

    @FXML
    private void Volver(ActionEvent event) throws IOException {
        Stage stage = (Stage) salir.getScene().getWindow();
        stage.close();
        Stage sc = new Stage();
        FXMLLoader loader = new FXMLLoader();
        AnchorPane root = (AnchorPane) loader.load(getClass().getResource("sucursal.fxml").openStream());
        Scene ac = new Scene(root);
        sc.setScene(ac);
        sc.show();

    }

}
