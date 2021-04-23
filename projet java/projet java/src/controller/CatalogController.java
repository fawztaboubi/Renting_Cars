/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import modele.Profil;
import modele.mysqlconnect;
import modele.voiture;


/**
 * FXML Controller class
 *
 * @author azer
 */

public class CatalogController implements Initializable 
{
    
    @FXML
    private TableView<voiture> table_catalog;

    @FXML
    private TableColumn<voiture, String> col_Plaque;

    @FXML
    private TableColumn<voiture,String> col_marque;

    @FXML
    private TableColumn<voiture,String> col_modele;
    @FXML
    private TableColumn<voiture,Float> prix;
   
    
    @FXML
    private TextField txt_plaque;
  

    @FXML
    private JFXDatePicker date1;

    @FXML
    private JFXDatePicker date2;
    @FXML
     private JFXCheckBox assurance;
    
    ObservableList<voiture>ListVoitures;
     @FXML
    private TextField filterField;
     ObservableList<voiture> dataList;
    private String a,a2,a3,a4;
    int index=-1;
    Connection conn=null;
    ResultSet rs= null;
    PreparedStatement pst=null;
  
    //ObservableList<catalog> ObCat = new FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    public void PreviousInterface(ActionEvent event) throws IOException
    {
      Parent TableViewParent =FXMLLoader.load(getClass().getResource("/view/FXMLSweetRide.fxml"));
      Scene TableViewScene = new Scene(TableViewParent);
      Stage Window = (Stage)((Node)event.getSource()).getScene().getWindow();
      Window.setScene(TableViewScene);
      Window.show();
    }
     
   
   
     @FXML
    void search_voiture() {          
        col_Plaque.setCellValueFactory(new PropertyValueFactory<>("Plaque"));
        col_marque.setCellValueFactory(new PropertyValueFactory<>("Marque"));
        col_modele.setCellValueFactory(new PropertyValueFactory<>("Modele"));
        prix.setCellValueFactory(new PropertyValueFactory<>("Prix"));
        
        dataList= mysqlconnect.getDatavoiture1();
        table_catalog.setItems(dataList);
         FilteredList<voiture> filteredData = new FilteredList<>(dataList, b -> true);  
         filterField.textProperty().addListener((observable, oldValue, newValue) -> {
 filteredData.setPredicate(voiture -> {
    if (newValue == null || newValue.isEmpty()) {
     return true;
    }    
    String lowerCaseFilter = newValue.toLowerCase();
    
    if (voiture.getPlaque().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
     return true; // Filter matches username
    } else if (voiture.getMarque().toLowerCase().indexOf(lowerCaseFilter) != -1) {
     return true; // Filter matches password
    }else if (voiture.getModele().toLowerCase().indexOf(lowerCaseFilter) != -1) {
     return true; // Filter matches password
    }
    else if (String.valueOf(voiture.getPrix()).indexOf(lowerCaseFilter)!=-1)
         return true;// Filter matches email
                                
         else  
          return false; // Does not match.
   });
  });  
  SortedList<voiture> sortedData = new SortedList<>(filteredData);  
  sortedData.comparatorProperty().bind(table_catalog.comparatorProperty());  
  table_catalog.setItems(sortedData);
  
    }
    @FXML
    void getSelected (MouseEvent event){
    index = table_catalog.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
    txt_plaque.setText(col_Plaque.getCellData(index).toString());
    
    
    }
    
     public void Add_Reservation () throws SQLException{  
          conn = mysqlconnect.ConnectDb();
        String sql = "select * from users where user_name = ? and password = ? ";
        try {
            pst = conn.prepareStatement(sql);
          
            pst.setString(1, Profil.getNom());
            pst.setString(2, Profil.getId());
            ResultSet rs = pst.executeQuery();
              while (rs.next()){   
                
                 a=rs.getString("user_id");
              }
              
            
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
         
         a2=date1.getValue().toString();
         a3=date2.getValue().toString();
        if (assurance.isSelected()){a4="yes";}else{a4="no";}
        conn = mysqlconnect.ConnectDb();

         sql = "insert into locations (user_id,Plaque,DateDebut,DateFin,Assurance)values(?,?,?,?,?)";
        try {
            
            pst = conn.prepareStatement(sql);
            pst.setString(1,a );
            pst.setString(2, txt_plaque.getText());
            pst.setString(3, a2);
            pst.setString(4, a3);
            pst.setString(5, a4);
            
            
            
            pst.execute();
            
             sql = "update voitures set Transmission = 'N' where Plaque='"+txt_plaque.getText()+"' ";
            pst= conn.prepareStatement(sql);
            pst.execute();
            search_voiture();
            
            JOptionPane.showMessageDialog(null, "reservation Add succes");
            
              
            //search_user();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        search_voiture();
         
        // date1.setValue(LocalDate.of(2020,19,12));
        // date2.setValue(LocalDate.of(2020,19,12));
        /*col_Plaque.setCellValueFactory(new PropertyValueFactory<voiture,String>("Plaque"));
        col_marque.setCellValueFactory(new PropertyValueFactory<voiture,String>("Marque"));
        col_modele.setCellValueFactory(new PropertyValueFactory<voiture,String>("Modele"));
       
        col_prix.setCellValueFactory(new PropertyValueFactory<voiture,Float>("Prix"));
        
        ListVoitures= mysqlconnect.getDatavoiture();
        table_catalog.setItems(ListVoitures);*/
        
    }    
    
}

  
   

// on action button stakcpane get the resilt of the sql request 
    // create an object connexion and connect to DB, use method"affichcatalog" du db 
    


