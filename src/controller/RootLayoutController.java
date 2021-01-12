package controller;

import javafx.event.ActionEvent;
import java.io.IOException;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RootLayoutController  {
	
	@FXML
	private Label lblStatus;
	
	@FXML
	private TextField usernameTextField;
	
	@FXML
	private TextField passwordTextField;
	
	@FXML
	public void initialize() {
		
	}
	//Exit the program
	public void handleExit(ActionEvent actionEvent){
		System.exit(0);
	}
	
	//Help Menu button behavior
	public void handleHelp(ActionEvent actionEvent){
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Program Information");
		alert.setHeaderText("Aplicatie PS Dragos!");
		alert.setContentText("You can search,delete,update,insert some data with this program.");
		alert.show();
	}
	

	public void handleAdminLogin(ActionEvent actionEvent) throws IOException{
		Stage stage1 = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/view/AdminLogin.fxml"));
		Scene scene = new Scene(root,685,348);
		scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		stage1.setScene(scene);
		stage1.setTitle("Admin login");
		stage1.show();
	}
	
	public void handleLogout(ActionEvent actionEvent) throws IOException{
		Stage stage2 = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/view/RootLayout.fxml"));
		Scene scene = new Scene(root,685,348);		
		scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		stage2.setScene(scene);
		stage2.show();
	}
	
	public void handleCasierLogin(ActionEvent actionEvent) throws IOException{
		Stage stage3 = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/view/CasierLogin.fxml"));
		Scene scene = new Scene(root,685,348);
		scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		stage3.setScene(scene);
		stage3.setTitle("Casier login");
		stage3.show();
	}
	
	public void handleShowView(ActionEvent actionEvent) throws IOException{
		Stage stage4 = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/view/ShowView.fxml"));
		Scene scene = new Scene(root,800,348);
		scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		stage4.setScene(scene);
		stage4.setTitle("Show view");
		stage4.show();
	}
	
	public void handleCashierView(ActionEvent actionEvent) throws IOException{
		Stage stage5 = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/view/CashierView.fxml"));
		Scene scene = new Scene(root,685,348);
		scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		stage5.setScene(scene);
		stage5.setTitle("Cashier view");
		stage5.show();
	}
	
	public void handleTicketView(ActionEvent actionEvent) throws IOException{
		Stage stage6 = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/view/CashierTicketView.fxml"));
		Scene scene = new Scene(root,685,348);
		scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		stage6.setScene(scene);
		stage6.setTitle("Ticket view");
		stage6.show();
	}
	
	public void handleAccountView(ActionEvent actionEvent) throws IOException{
		Stage stage7 = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/view/AccountView.fxml"));
		Scene scene = new Scene(root,685,348);
		scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		stage7.setScene(scene);
		stage7.setTitle("Account view");
		stage7.show();
	}
	
	public void handleOrderView(ActionEvent actionEvent) throws IOException{
		Stage stage8 = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/view/OrderView.fxml"));
		Scene scene = new Scene(root,685,348);
		scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		stage8.setScene(scene);
		stage8.setTitle("Order view");
		stage8.show();
	}
}
