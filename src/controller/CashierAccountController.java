package controller;

import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Utilizator;
import util.UtilizatorDao;

public class CashierAccountController {
	
	@FXML
	private TextField casierUsernameText;
	@FXML
	private TextField newUsernameText;
	@FXML
	private TextField newPasswordText;
	
	@FXML
    private TextArea resultArea;
	
	@FXML
    private TableView usersTable;
	
	@FXML
	private TableColumn<Utilizator, String>  casierUsernameColumn;
	@FXML
	private TableColumn<Utilizator, String>  casierPasswordColumn;
	@FXML
	private TableColumn<Utilizator, String> casierNameColumn;
	@FXML
	private TableColumn<Utilizator, String> tipColumn;
	
    //Search a User after name
    @FXML
    private void searchUser (ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        try {
            //Get User information
            Utilizator User = UtilizatorDao.searchUser(casierUsernameText.getText());
            //Populate User on TableView and Display on TextArea
            populateAndShowUser(User);
        } catch (SQLException e) {
            e.printStackTrace();
            resultArea.setText("Error occurred while getting user information from DB.\n" + e);
            throw e;
        }
    }
    
    //Initializing the controller class.
    //This method is automatically called after the fxml file has been loaded.
    @FXML
    private void initialize () {
       
    	casierUsernameColumn.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());
    	casierPasswordColumn.setCellValueFactory(cellData -> cellData.getValue().passwordProperty());
    	casierNameColumn.setCellValueFactory(cellData -> cellData.getValue().numeProperty());
    	tipColumn.setCellValueFactory(cellData -> cellData.getValue().tipProperty());
    }
    
    //Populate Show
    @FXML
    private void populateUser (Utilizator user) throws ClassNotFoundException {
        //Declare and ObservableList for table view
        ObservableList<Utilizator> userData = FXCollections.observableArrayList();
        //Add show to the ObservableList
        userData.add(user);
        //Set items to the userTable
        usersTable.setItems(userData);
    }
    
    //Set User information to Text Area
    @FXML
    private void setShowInfoToTextArea (Utilizator user) {
        resultArea.setText("Username: " + user.getUsername() + "\n" +"Password: " + user.getPassword() + "\n" + "Nume: " + user.getNume() + "\n" + "Tip: " + user.getType());
    }
    
    //Populate User for TableView and Display User on TextArea
    @FXML
    private void populateAndShowUser(Utilizator user) throws ClassNotFoundException {
        if (user != null) {
            populateUser(user);
            setShowInfoToTextArea(user);
        } else {
            resultArea.setText("This user does not exist!\n");
        }
    }
    
    //Populate Users for TableView
    @FXML
    private void populateUsers (ObservableList<Utilizator> userData) throws ClassNotFoundException {
        //Set items to the usersTable
    	usersTable.setItems(userData);
    }
    
    //Update user's username with the username which is written in newUsernameText
    @FXML
    private void updateUsername (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
        	UtilizatorDao.updateUserUsername(casierUsernameText.getText(),newUsernameText.getText());
            resultArea.setText("Username has been updated for, username: " + casierUsernameText.getText() + "\n");
        } catch (SQLException e) {
            resultArea.setText("Problem occurred while updating username: " + e);
        }
    }
    
    //Update user's password with the password which is written on newPasswordText
    @FXML
    private void updatePassword (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
        	UtilizatorDao.updateUserPassword(casierUsernameText.getText(),newPasswordText.getText());
            resultArea.setText("Password has been updated for, username: " + casierUsernameText.getText() + "\n");
        } catch (SQLException e) {
            resultArea.setText("Problem occurred while updating password: " + e);
        }
    }
}
