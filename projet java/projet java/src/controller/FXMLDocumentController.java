package controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import modele.Profil;
import modele.mysqlconnect;

/**
 *
 * @author wafa
 */
public class FXMLDocumentController implements Initializable {
   @FXML
    private AnchorPane pane_signup;

    @FXML
    private JFXComboBox<String> type_up;

    @FXML
    private JFXPasswordField txt_password_up;

    @FXML
    private JFXTextField txt_username_up;

    @FXML
    private JFXTextField email_up;

    @FXML
    private JFXButton sign_in;

    @FXML
    private AnchorPane pane_login;

    @FXML
    private JFXTextField txt_username;

    @FXML
           private JFXPasswordField txt_password; 

    @FXML
    private JFXButton bnt_login;
    
       @FXML
    private JFXTextField code_admin;
   

    
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    
    public void LoginpaneShow(){
    
        pane_login.setVisible(true);
        pane_signup.setVisible(false);
    }
    
    public void SignuppaneShow(){
    
        pane_login.setVisible(false);
        pane_signup.setVisible(true);
    }
    
   
    @FXML  
    private void Login (ActionEvent event) throws Exception{  
    conn = mysqlconnect.ConnectDb();
    String sql = "Select * from users where user_name = ? and password = ? and type = ? ";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_username.getText());
            pst.setString(2, txt_password.getText());
            pst.setString(3, "Admin");
           
            
            rs = pst.executeQuery();
            
            if(rs.next()){ 
                JOptionPane.showMessageDialog(null, "Welcome Admin");
               
                bnt_login.getScene().getWindow().hide();
                Parent root;
                root = FXMLLoader.load(getClass().getResource("/view/Graphic 03 slider.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
                
            }else
                try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_username.getText());
            pst.setString(2, txt_password.getText());
            pst.setString(3, "Client");
            
            
           
            
            rs = pst.executeQuery();
            
            if(rs.next()){ 
                
                JOptionPane.showMessageDialog(null, "Welcome User");
                Profil.setNom(txt_username.getText());
                Profil.setId(txt_password.getText());
            
                bnt_login.getScene().getWindow().hide();
                Parent root;
                root = FXMLLoader.load(getClass().getResource("/view/FXMLSweetRide.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
                
            }else
                JOptionPane.showMessageDialog(null, "Invalide Username Or Password");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
               
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
   
    }
    
    
    public void add_users(ActionEvent event){    
        conn = mysqlconnect.ConnectDb();
       
        if ( "Admin".equals(type_up.getValue())){
             
             if ("0000".equals(code_admin.getText())){
              String sql = "insert into users (user_name,password,type,email) values (?,?,?,?)";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_username_up.getText());
            pst.setString(2, txt_password_up.getText());
            pst.setString(3, type_up.getValue().toString());
            pst.setString(4, email_up.getText());
            pst.execute();
            
            JOptionPane.showMessageDialog(null, "Saved");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }  }else JOptionPane.showMessageDialog(null, "enter code or code invalid");
                 
        }else { String sql = "insert into users (user_name,password,type,email) values (?,?,?,?)";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_username_up.getText());
            pst.setString(2, txt_password_up.getText());
            pst.setString(3, type_up.getValue().toString());
            pst.setString(4, email_up.getText());
            pst.execute();
            
            JOptionPane.showMessageDialog(null, "Saved");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }  }}
        
       
    
    
      
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         type_up.getItems().addAll("Admin","Client");
         
    }        
    
}
