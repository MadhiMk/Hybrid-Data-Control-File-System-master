/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hybrid;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mk
 */
public class IndexController implements Initializable {

    /**
     * Initializes the controller class.
     */
    Stage stage=new Stage();
    @FXML
    public void backSecure(ActionEvent event) throws IOException{
        ((Node)(event.getSource())).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("DataSecure.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Welcome To Data Secure System");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void backRelease(ActionEvent event) throws IOException{
        ((Node)(event.getSource())).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("DataRelease.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Welcome To Data Release System");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void backDataHide(ActionEvent event) throws IOException{
        ((Node)(event.getSource())).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("DataHide.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Welcome To File Hide Manager");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void signOut(ActionEvent event) throws IOException{
        ((Node)(event.getSource())).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("Welcome.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Welcome To Login");
        stage.setScene(scene);
        stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
