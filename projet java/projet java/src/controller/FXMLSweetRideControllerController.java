
package controller;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

 
/**
 *
 * @author azer
 */
public class FXMLSweetRideControllerController implements Initializable  {         
    
  @FXML
    private Button bb;

          
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    // THIS METOD SHALL CHANGE THE SCENE AND THEREFORE SWITCHS TO THE NEXT INTERFACE
    public void ChangeScreenButtonPushed(ActionEvent event) throws IOException
    {
        bb.getScene().getWindow().hide();
      Parent TableViewParent =FXMLLoader.load(getClass().getResource("/view/Catalog.fxml"));
      Scene TableViewScene = new Scene(TableViewParent);
      Stage Window = (Stage)((Node)event.getSource()).getScene().getWindow();
      Window.setScene(TableViewScene);
      Window.show();
    }
    
    public void ShowProfile(ActionEvent event) throws IOException
    {
      Parent TableViewParent =FXMLLoader.load(getClass().getResource("/view/FXMLProfile.fxml"));
      Scene TableViewScene = new Scene(TableViewParent);
      Stage Window = (Stage)((Node)event.getSource()).getScene().getWindow();
      Window.setScene(TableViewScene);
      Window.show();
    }
    
}
