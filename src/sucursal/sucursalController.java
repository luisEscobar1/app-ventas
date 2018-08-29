package sucursal;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.*;

/**
 *
 * @author pc
 */
public class sucursalController implements Initializable {

    @FXML
    private TextField usuario;
    @FXML
    private PasswordField clave;

    @FXML
    private Button entrar;
    @FXML
    private Button salir;
    @FXML
    private Button Registrar;
    sucursalController sc;

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        //falta un condicional para saber que tipo de usuario es, con eso variar la siguiente vista
        //pudeser la que esta o la de caja
        String u = usuario.getText();
        String c = clave.getText();
        AccesoAleatorioAdministrador.crearFileAdmon(new File("administrador.dat"));
        AccesoAleatorioVendedor.crearFileVen(new File("vendedor.dat"));

        int a = AccesoAleatorioAdministrador.buscarRegistro(u);
        if (a != -1) {
            administrador p = AccesoAleatorioAdministrador.getPersona(a);
            if (p.getClave().equalsIgnoreCase(c));
            Stage stage = (Stage) salir.getScene().getWindow();
            stage.close();
            Stage caja = new Stage();
            FXMLLoader loader = new FXMLLoader();
            AnchorPane root = (AnchorPane) loader.load(getClass().getResource("inter2.fxml").openStream());
            Scene ac = new Scene(root);
            caja.setScene(ac);
            caja.show();
        } else {
            int b = AccesoAleatorioVendedor.buscarRegistro(u);
            if (a != 1) {
                vendedor v = AccesoAleatorioVendedor.getPersona(b);
                if (v.getClave().equals(c)) {
                    Stage stage = (Stage) salir.getScene().getWindow();
                    stage.close();
                    Stage caja = new Stage();
                    FXMLLoader loader = new FXMLLoader();

                    AnchorPane root = (AnchorPane) loader.load(getClass().getResource("caja.fxml").openStream());
                    Scene ac = new Scene(root);
                    caja.setScene(ac);
                    caja.show();
                }
            }
        }
        AccesoAleatorioAdministrador.cerrar();
        AccesoAleatorioVendedor.cerrar();
    }

    @FXML
    private void Salir_api(ActionEvent event) {
        Stage stage = (Stage) salir.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void Ir_Registro(ActionEvent event) throws IOException {
        Stage stage = (Stage) Registrar.getScene().getWindow();
        stage.close();
        Stage registro = new Stage();
        FXMLLoader loader = new FXMLLoader();
        AnchorPane root = (AnchorPane) loader.load(getClass().getResource("registro2.fxml").openStream());
        Scene ac = new Scene(root);
        registro.setScene(ac);
        registro.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sc = this;
    }

}
