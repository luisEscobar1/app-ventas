/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sucursal;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import modelo.AccesoCaja;
import modelo.caja;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class DineroEnCajaController implements Initializable {

    @FXML
    private TextField id_caja;
    @FXML
    private Button mostrar;
    @FXML
    private Label total;
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
    private void Mostrar_Dinero(ActionEvent event) throws IOException {
        AccesoCaja.crearFileAdmon(new File("caja.dat"));
        int c = AccesoCaja.buscarRegistro(id_caja.getText());
        if (c != 1) {
            caja v = AccesoCaja.getPersona(c);
            total.setText(String.valueOf(v.getDinero()));
        }
    }

    @FXML
    private void Salir(ActionEvent event) throws IOException {
        Stage stage = (Stage) salir.getScene().getWindow();
        stage.close();
        Stage inter = new Stage();
        FXMLLoader loader = new FXMLLoader();
        AnchorPane root = (AnchorPane) loader.load(getClass().getResource("inter2.fxml").openStream());
        Scene ac = new Scene(root);
        inter.setScene(ac);
        inter.show();

    }
}
