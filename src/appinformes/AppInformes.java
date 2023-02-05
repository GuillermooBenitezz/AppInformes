/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package appinformes;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author usuario
 */
public class AppInformes extends Application {

    private double posX = 0;
    private double posY = 0;
    public static Connection conexion = null;

    @Override
    public void start(Stage primaryStage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("menuFXML.fxml"));

        Scene scene = new Scene(root);
        primaryStage.initStyle(StageStyle.DECORATED.UNDECORATED);

        root.setOnMousePressed(e -> {
            posX = primaryStage.getX() - e.getScreenX();
            posY = primaryStage.getY() - e.getScreenY();
        });
        root.setOnMouseDragged(e -> {
            primaryStage.setX(e.getScreenX() + posX);
            primaryStage.setY(e.getScreenY() + posY);
        });

        primaryStage.setScene(scene);
        primaryStage.show();

        //establecemos la conexión con la BD
        conectaBD();
    }

    @Override
    public void stop() throws Exception {
        try {
            DriverManager.getConnection("jdbc:hsqldb:hsql://localhost;shutdown=true");
        } catch (SQLException ex) {
            System.out.println("No se pudo cerrar la conexion a la BD");
        }
    }

    public void conectaBD() {
        //Establecemos conexión con la BD
        String baseDatos = "jdbc:hsqldb:hsql://localhost:9001/sampledb";
        String usuario = "sa";
        String clave = "";
        try {
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
            conexion = DriverManager.getConnection(baseDatos, usuario, clave);
        } catch (ClassNotFoundException cnfe) {
            System.err.println("Fallo al cargar JDBC");
            System.exit(1);
        } catch (SQLException sqle) {
            System.err.println("No se pudo conectar a BD");
            System.exit(1);
        } catch (java.lang.InstantiationException | IllegalAccessException sqlex) {
            System.err.println("Imposible Conectar");
            System.exit(1);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
