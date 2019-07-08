/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hybrid;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.security.Key;
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
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * FXML Controller class
 *
 * @author Mk
 */
public class DataReleaseController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXComboBox<String> combotype;
    
    @FXML
    private JFXTextField fname;

    @FXML
    private JFXTextField pass1;

    @FXML
    private JFXTextField bf;
    @FXML
    private Text msg;
    String formats;
    @FXML
    private JFXTextField sf;
    private static final String ALGORITHM = "Blowfish";
    private static final String keyString = "DesireSecretKey";
        public static void encrypt(File inputFile, File outputFile)throws Exception {
		doCrypto(Cipher.ENCRYPT_MODE, inputFile, outputFile);
    }
    public static void decrypt(File inputFile, File outputFile)throws Exception {
		doCrypto(Cipher.DECRYPT_MODE, inputFile, outputFile);
    }
    private static void doCrypto(int cipherMode, File inputFile,File outputFile) throws Exception {
		Key secretKey = new SecretKeySpec(keyString.getBytes(), ALGORITHM);
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(cipherMode, secretKey);

		FileInputStream is = new FileInputStream(inputFile);
		byte[] inputBytes = new byte[(int) inputFile.length()];
		is.read(inputBytes);

		byte[] outputBytes = cipher.doFinal(inputBytes);

		FileOutputStream outputStream = new FileOutputStream(outputFile);
		outputStream.write(outputBytes);

		is.close();
		outputStream.close();
    }
    @FXML
private void handleDeProcess(ActionEvent event){
		File encryptedFile = new File(bf.getText());
		
		File decryptedFile = new File(sf.getText()+"\\"+fname.getText()+combotype.getValue());

		try {
			decrypt(encryptedFile, decryptedFile);
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucess...!");
                alert.setContentText("Success..!");
                alert.showAndWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
     }
Stage stage=new Stage();
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
    private void dataClear(ActionEvent event){
        fname.clear();
        sf.clear();
        bf.clear();
        pass1.clear();
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
        bf.setText(pa);
    }
    @FXML
     private void handleSaveAs(ActionEvent event){
        DirectoryChooser chooser = new DirectoryChooser();
        File defaultDirectory = new File("C:\\");
        chooser.setInitialDirectory(defaultDirectory);
        File selectedDirectory = chooser.showDialog(null);
        String pa=selectedDirectory.getAbsolutePath();
        sf.setText(pa);
    }
ObservableList<String> listdata=FXCollections.observableArrayList(".txt",".docx",".pdf",".jpg");
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        combotype.setItems(listdata);
    }    
    
}
