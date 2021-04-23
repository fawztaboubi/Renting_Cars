/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import modele.mysqlconnect;
import modele.voiture;

/**
 * FXML Controller class
 *
 * @author wafa
 */
public class SearchController implements Initializable {

    
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
    private JFXTextField filterField;
     ObservableList<voiture> dataList;
     @FXML
    void search_voiture() {          
        col_plaque.setCellValueFactory(new PropertyValueFactory<voiture,String>("Plaque"));
        col_marque.setCellValueFactory(new PropertyValueFactory<voiture,String>("Marque"));
        col_modele.setCellValueFactory(new PropertyValueFactory<voiture,String>("Modele"));
        col_tran.setCellValueFactory(new PropertyValueFactory<voiture,String>("Transmission"));
        col_prix.setCellValueFactory(new PropertyValueFactory<voiture,Float>("Prix"));
        
        dataList= mysqlconnect.getDatavoiture();
        table_voiture.setItems(dataList);
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
  sortedData.comparatorProperty().bind(table_voiture.comparatorProperty());  
  table_voiture.setItems(sortedData);      
    }
    @FXML
     private void quit (ActionEvent event) throws IOException {  
            
           
               
                filterField.getScene().getWindow().hide();
                Parent root;
                root = FXMLLoader.load(getClass().getResource("/view/FXMLDocument.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
                
           
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        search_voiture();
        
    }    
    
}
