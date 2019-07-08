/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hybrid;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Mk
 */
public class WelcomeController implements Initializable {
    
    private final String user="root";
    private final String pass="";
    private final String conn="jdbc:mysql://localhost/hybridcontrol";
    private Connection con=null;
    private ResultSet rs=null;;
    private PreparedStatement pst=null;
    Stage stage=new Stage();
    @FXML
    private Text textempty;

    @FXML
    private Text passwordempty;

    @FXML
    private Text Invalidlogin;
    
    @FXML
    private JFXTextField txtuser;

    @FXML
    private JFXPasswordField txtpass;
    @FXML
    private void handleLogin(ActionEvent event) throws SQLException, IOException {
       con=DriverManager.getConnection(conn, user, pass);
        String loguser=txtuser.getText().trim();
        String logpass=txtpass.getText().trim();
        String sql="SELECT * FROM tbuser WHERE username=? AND password=?";
        try{
            pst=con.prepareStatement(sql);
            pst.setString(1, loguser);
            pst.setString(2, logpass);
            rs=pst.executeQuery();
            if(txtuser.getText().length()==0)
            {
                textempty.setText("Please Enter Your Username");
            }
            else if(txtpass.getText().length()==0){
                passwordempty.setText("Please Enter Your Password");
            }
            else if(rs.next()){
                ((Node)(event.getSource())).getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("Index.fxml"));
                Scene scene = new Scene(root);
                stage.setTitle("Welcome To Hybrid Data Control File System");
                stage.setScene(scene);
                stage.show();
            }else{
                txtuser.clear();
                txtpass.clear();
                textempty.setText("");
                passwordempty.setText("");
                Alert alert=new Alert(AlertType.WARNING);
                alert.setTitle("Invalid...!");
                alert.setContentText("Invalid Username & password");
                alert.showAndWait();
            }
        }catch(SQLException e){
            System.out.print(e);
        }
    }
    @FXML
    public void handleRegister(ActionEvent event) throws IOException{
        ((Node)(event.getSource())).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("Register.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Register Account");
        stage.setScene(scene);
        stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
