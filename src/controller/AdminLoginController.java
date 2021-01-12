package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import controller.AdminLoginController;

public class AdminLoginController {

	@FXML
	private TextField user;
	
	@FXML
	private TextField pass;
	
	@FXML
	private Label lblStatus;
	
	public void login(ActionEvent event) throws IOException{
		
		Connection connection;
		PreparedStatement ps;
		
		try{
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Opera","dragos","prata1996");
			ps = connection.prepareStatement("SELECT `username`,`parola` FROM `utilizator` WHERE `username` = ? AND `parola` = ?");
			ps.setString(1,user.getText());
			ps.setString(2, pass.getText());
			ResultSet result = ps.executeQuery();
			
			if(result.next()){
				lblStatus.setText("Succes!");
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/AdminLayout.fxml"));
	            Parent root1 = (Parent) fxmlLoader.load();
	            Stage stage = new Stage();
	            stage.setScene(new Scene(root1));  
	            stage.show();
			} else{
				lblStatus.setText("Failed!");
			}
		} catch(SQLException e){
			Logger.getLogger(AdminLoginController.class.getName()).log(Level.SEVERE, null, e);
		}
	}
}
