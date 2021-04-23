package modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;

/**
 *
 * @author amir
 */
public class mysqlconnect {
    Connection conn = null;
    public static Connection ConnectDb(){
 
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/location_voiture","root","");
           // JOptionPane.showMessageDialog(null, "Connection Established");
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        } 
    }
    public static ObservableList<voiture> getDatavoiture(){
        Connection conn = ConnectDb();
        ObservableList<voiture> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from voitures");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new voiture(rs.getString("Plaque"), rs.getString("Marque"), rs.getString("Modele"), rs.getString("Transmission"), Float.parseFloat(rs.getString("Prix"))));               
            }
        } catch (Exception e) {
        }
        return list;
    }
    public static ObservableList<voiture> getDatavoiture1(){
        Connection conn = ConnectDb();
        ObservableList<voiture> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from voitures where Transmission = 'Y'");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new voiture(rs.getString("Plaque"), rs.getString("Marque"), rs.getString("Modele"), rs.getString("Transmission"), Float.parseFloat(rs.getString("Prix"))));               
            }
        } catch (Exception e) {
        }
        return list;
    }

}