/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package appinformes;

import static appinformes.AppInformes.conexion;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author usuario
 */
public class MenuFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void exitApp(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void listadoFacturas(ActionEvent event) {
        try {
            JasperReport jr = (JasperReport) JRLoader.loadObject(AppInformes.class.getResource("facturas.jasper"));
            //Map de parámetros
            Map parametros = new HashMap();

            JasperPrint jp = (JasperPrint) JasperFillManager.fillReport(jr, parametros, conexion);
            JasperViewer.viewReport(jp, false);
        } catch (JRException ex) {
            System.out.println("Error al recuperar el jasper");
            JOptionPane.showMessageDialog(null, ex);
        }

    }

    @FXML
    private void ventasTotales(ActionEvent event) {
        try {
            JasperReport jr = (JasperReport) JRLoader.loadObject(AppInformes.class.getResource("ventasTotales.jasper"));
            //Map de parámetros
            Map parametros = new HashMap();

            JasperPrint jp = (JasperPrint) JasperFillManager.fillReport(jr, parametros, conexion);
            JasperViewer.viewReport(jp, false);
        } catch (JRException ex) {
            System.out.println("Error al recuperar el jasper");
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    @FXML
    private void facturasCliente(ActionEvent event) {
        Stage primaryStage = new Stage();
        TextField tituloIntro = new TextField("nº cliente");
        Button btn = new Button();
        btn.setText("Generar Informe");
        VBox root = new VBox();
        root.getChildren().addAll(tituloIntro, btn);
        btn.setOnAction((ActionEvent event1) -> {
            try {
                JasperReport jr = (JasperReport) JRLoader.loadObject(AppInformes.class.getResource("FacturasCliente.jasper"));
                //Map de parámetros
                Map parametros = new HashMap();
                int nproducto = Integer.parseInt(tituloIntro.getText());
                parametros.put("CodigoCliente", nproducto);
                JasperPrint jp = (JasperPrint) JasperFillManager.fillReport(jr, parametros, conexion);
                JasperViewer.viewReport(jp, false);
            } catch (JRException ex) {
                System.out.println("Error al recuperar el jasper");
                JOptionPane.showMessageDialog(null, ex);
            }
        });

        Scene scene = new Scene(root, 300, 150);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    private void subinformeListado(ActionEvent event) {
        try {
            JasperReport jr = (JasperReport) JRLoader.loadObject(AppInformes.class.getResource("subinformeListadoFacturas.jasper"));
            JasperReport jsr = (JasperReport) JRLoader.loadObject(AppInformes.class.getResource("subinformeProd.jasper"));
            //Map de parámetros
            Map parametros = new HashMap();
            parametros.put("subReportParameter", jsr);
            JasperPrint jp = (JasperPrint) JasperFillManager.fillReport(jr, parametros, conexion);
            JasperViewer.viewReport(jp, false);
        } catch (JRException ex) {
            System.out.println("Error al recuperar el jasper");
            JOptionPane.showMessageDialog(null, ex);
        }
    }
}
