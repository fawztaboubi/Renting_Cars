/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javax.swing.JOptionPane;


/**
 * FXML Controller class
 *
 * @author cheti
 */
public class Graphic03SliderController implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private Pane content;

    @FXML
    private JFXDrawer slider;

    @FXML
    private JFXHamburger icon;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
        VBox v = FXMLLoader.load(getClass().getResource("/view/Menu.fxml"));
        slider.setSidePane(v);
        
        for (Node n: v.getChildren()){
            if(n.getAccessibleText() !=null){
                n.addEventHandler(MouseEvent.MOUSE_CLICKED, e->{
                    
                    try {
                      switch(n.getAccessibleText()){ 
                        case "Add":
                            Node add = FXMLLoader.load(getClass().getResource("/view/Add.fxml"));
                            content.getChildren().setAll(add);                    
                            break;
                        
                        case "Update":
                            Node update = FXMLLoader.load(getClass().getResource("/view/Update.fxml"));
                            content.getChildren().setAll(update);
                            break;   
                            
                        case "Delete": 
                            Node delete = FXMLLoader.load(getClass().getResource("/view/Delete.fxml"));
                            content.getChildren().setAll(delete);
                             break;  
                             
                        case "Search":  
                            Node search = FXMLLoader.load(getClass().getResource("/view/Search.fxml"));
                            content.getChildren().setAll(search);
                            
                             break; 
                             
                        case "Exit":  Platform.exit();
                             break; 

               
                    
                    }   
                    } catch (Exception ee) {
                    }
                    
                 
                    
                });
            }
        
        }
        
        HamburgerBasicCloseTransition hbct = new HamburgerBasicCloseTransition(icon);
        hbct.setRate(-1);
        icon.addEventFilter(MouseEvent.MOUSE_CLICKED, e->{
        
            hbct.setRate(hbct.getRate()*-1);
            hbct.play();
            
        if(slider.isOpened()){
            slider.close();
            
            }else{
            slider.open();
            }   
            

        });
             
        } catch (Exception e) {
        }
        
    }    
    
}
