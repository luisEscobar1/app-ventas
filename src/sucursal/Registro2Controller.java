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
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import modelo.*;

/**
 * FXML Controller class
 *
 * @author LUIS
 */
public class Registro2Controller implements Initializable {

    @FXML
    private TextField nombre;
    @FXML
    private TextField apellido;
    @FXML
    private TextField id;
    @FXML
    private TextField usuario;
    @FXML
    private TextField clave;
    @FXML
    private CheckBox vendedor;
    @FXML
    private CheckBox administrador;
    @FXML
    private Button guardar;
    @FXML
    private Button Salir;
    private ObservableList<administrador> admons;
    ArrayList<administrador> administradores;
    String rol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void checkEvent(ActionEvent event) {
        System.out.println("entro");
        if (administrador.isSelected()) {
            rol = "administrador";
            System.out.println(rol + "xd");
            System.out.println("-----");
        }
        if (vendedor.isSelected()) {
            rol = "vendedor";
            System.out.println(rol + "xd");
            System.out.println("-----");
        }
    }

    @FXML
    private void guardar_registro(ActionEvent event) throws IOException {
        String n = nombre.getText();
        String ap = apellido.getText();
        String Id = id.getText();
        String Us = usuario.getText();
        String c = clave.getText();

        System.out.println(rol + "exito");

        if (rol.equalsIgnoreCase("administrador")) {
            AccesoAleatorioAdministrador.crearFileAdmon(new File("administrador.dat"));
            AccesoAleatorioAdministrador.añadirPersona(new administrador(n, ap, Id, Us, c, true));
            AccesoAleatorioAdministrador.cerrar();
        }
        if (rol.equalsIgnoreCase("vendedor")) {
            AccesoAleatorioVendedor.crearFileVen(new File("vendedor.dat"));
            AccesoAleatorioVendedor.añadirPersona(new vendedor(n, ap, Id, Us, c,0, true));
        }
        Stage stage = (Stage) guardar.getScene().getWindow();
        stage.close();
        Stage suc = new Stage();
        FXMLLoader loader = new FXMLLoader();
        AnchorPane root = (AnchorPane) loader.load(getClass().getResource("sucursal.fxml").openStream());
        Scene ac = new Scene(root);
        suc.setScene(ac);
        suc.show();

    }

    @FXML
    private void Salir(ActionEvent event) throws IOException {

        Stage stage = (Stage) Salir.getScene().getWindow();
        stage.close();
        Stage suc = new Stage();
        FXMLLoader loader = new FXMLLoader();
        AnchorPane root = (AnchorPane) loader.load(getClass().getResource("sucursal.fxml").openStream());
        Scene ac = new Scene(root);
        suc.setScene(ac);
        suc.show();
    }

}
