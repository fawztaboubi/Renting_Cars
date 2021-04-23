/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import modele.mysqlconnect;
import modele.voiture;

/**
 * FXML Controller class
 *
 * @author cheti
 */
public class UpdateController implements Initializable {

    @FXML
    private JFXComboBox<String> cb;
     @FXML
    private TableView<voiture> table_voiture;

    @FXML
    private TableColumn<voiture, String> col_plaque;

    @FXML
    private TableColumn<voiture, String> col_marque;

    @FXML
    private TableColumn<voiture, String> col_modele;

    @FXML
    private TableColumn<voiture, String> col_tran;

    @FXML
    private TableColumn<voiture, Float> col_prix;
    @FXML
    private JFXTextField txt_plaque;

    @FXML
    private JFXTextField txt_modele;

    @FXML
    private JFXTextField txt_marque;

    @FXML
    private JFXTextField txt_prix;
    ObservableList<voiture> listM;
    int index = -1;
    Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    
    public void Edit (){   
        try {
            conn = mysqlconnect.ConnectDb();
            String value1 = txt_plaque.getText();
            String value2 = txt_marque.getText();
            String value3 = txt_modele.getText();
            String value4 = txt_prix.getText();
            
            String sql = "update voitures set Plaque= '"+value1+"',Marque= '"+value2+"',Modele= '"+
                    value3+"',Prix= '"+value4+"' where Plaque='"+value1+"' ";
            pst= conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Update");
            UpdateTable();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
     @FXML
    void getSelected (MouseEvent event){
    index = table_voiture.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
    txt_plaque.setText(col_plaque.getCellData(index).toString());
    txt_marque.setText(col_marque.getCellData(index).toString());
    txt_modele.setText(col_modele.getCellData(index).toString());
    txt_prix.setText(col_prix.getCellData(index).toString());
    
    }
      public void UpdateTable(){
         col_plaque.setCellValueFactory(new PropertyValueFactory<voiture,String>("Plaque"));
        col_marque.setCellValueFactory(new PropertyValueFactory<voiture,String>("Marque"));
        col_modele.setCellValueFactory(new PropertyValueFactory<voiture,String>("Modele"));
        col_tran.setCellValueFactory(new PropertyValueFactory<voiture,String>("Transmission"));
        col_prix.setCellValueFactory(new PropertyValueFactory<voiture,Float>("Prix"));
        
        listM= mysqlconnect.getDatavoiture();
        table_voiture.setItems(listM);
    }
    @FXML  
    private void quit (ActionEvent event) throws IOException {  
            
           
               
                txt_modele.getScene().getWindow().hide();
                Parent root;
                root = FXMLLoader.load(getClass().getResource("/view/FXMLDocument.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
                
           
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cb.getItems().add("Y");
        cb.getItems().add("N");
        UpdateTable();
       
    }    

  
    
}
  