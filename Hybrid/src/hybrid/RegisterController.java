/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hybrid;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mk
 */
public class RegisterController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXTextField txtus;

    @FXML
    private JFXPasswordField txtpd;

    @FXML
    private JFXTextField txtna;

    @FXML
    private JFXTextField txtemail;

    @FXML
    private JFXTextField txtmobile;

    @FXML
    private JFXTextField txtans;


    @FXML
    private JFXComboBox combo;

    @FXML
    private JFXDatePicker date;

    @FXML
    private JFXRadioButton male;

    @FXML
    private JFXRadioButton female;

    @FXML
    private Text regok;
    private final String username="root";
    private final String password="";
    private final String conn="jdbc:mysql://localhost/hybridcontrol";
    private Connection con=null;
    private ResultSet rs=null;;
    private PreparedStatement pst=null;
    Stage stage=new Stage();
    String gen;
    String dat;
    @FXML
    private void registerUser(ActionEvent event) throws SQLException{
        con=DriverManager.getConnection(conn, username, password);
        String sql="insert into tbuser (username,password,name,gender,date,email,mobile,security,answer) values (?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps=con.prepareStatement(sql);
       ps.setString(1, txtus.getText());
       ps.setString(2, txtpd.getText());
       ps.setString(3, txtna.getText());
        if(male.isSelected()){
            gen="male";
        }else if(female.isSelected()){
            gen="female";
        }
        ps.setString(4, gen);
        dat=date.getValue().toString();
        ps.setString(5, dat);
        ps.setString(6, txtemail.getText());
        ps.setString(7, txtmobile.getText());
        ps.setString(8, "Favorate game");
        ps.setString(9, txtans.getText());
        ps.executeUpdate();
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucess...!");
                alert.setContentText("Register Successfully..!");
                alert.showAndWait();
    }
    @FXML
    public void backLogin(ActionEvent event) throws IOException{
        ((Node)(event.getSource())).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("Welcome.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Welcome to Login");
        stage.setScene(scene);
        stage.show();
    }
    ObservableList<String> listdata=FXCollections.observableArrayList("Favorate game");
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        combo.setItems(listdata);
    }    
    
}
