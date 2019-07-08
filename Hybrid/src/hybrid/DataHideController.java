/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hybrid;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mk
 */
public class DataHideController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXTextField newname;
    @FXML
    private Label msg;
    @FXML
    private JFXTextField browse;
    Stage stage=new Stage();
    @FXML
    private JFXComboBox<String> combotype;
    @FXML
    private void handleDatahide(ActionEvent event){
        String sp=browse.getText();
        String dp="C:\\"+newname.getText()+combotype.getValue();
        FileInputStream fis=null;
        FileOutputStream fos=null;
        try{
            fis=new FileInputStream(sp);
            fos=new FileOutputStream(dp);
            byte[] b=new byte[1024];
            int ch;
            while((ch=fis.read(b))!=-1){
                fos.write(b,0,ch);
            }
            fos.flush();
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucess...!");
                alert.setContentText("Success..!");
                alert.showAndWait();
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            try{
                if(fis !=null){
                    fis.close();
                }
                if(fos !=null){
                    fos.close();
                }
            }catch(IOException e){
                        e.printStackTrace();
                        }
        }new File(sp).delete();
    }
    @FXML
    public void backHome(ActionEvent event) throws IOException{
        ((Node)(event.getSource())).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("Index.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Welcome To Hybrid Data Control File System");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void backSignout(ActionEvent event) throws IOException{
        ((Node)(event.getSource())).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("Welcome.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Welcome To Login");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void handleBrowse(ActionEvent event){
        FileChooser fc=new FileChooser();
        File file=fc.showOpenDialog(null);
        String pa=file.getAbsolutePath();
        browse.setText(pa);
    }
    @FXML
    public void openWindows(ActionEvent event) throws IOException{
        try {
    Desktop.getDesktop().open(new File("C:\\"));
} catch (IOException e) {
    e.printStackTrace();
}
    }
    ObservableList<String> listdata=FXCollections.observableArrayList(".txt",".docx",".pdf",".jpg");
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        combotype.setItems(listdata);
    }    
    
}
