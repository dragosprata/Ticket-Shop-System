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
import model.Spectacol;
import model.Utilizator;
import model.Utilizator;
import util.SpectacolDao;
import util.UtilizatorDao;
import util.UtilizatorDao;

public class AccountController {

	@FXML
	private TextField usernameText;
	@FXML
	private TextField parolaText;
	@FXML
	private TextField numeText;
	@FXML
	private TextField tipText;
	@FXML
	private TextField casierUsernameText;

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
	
    //Search all users
    @FXML
    private void searchUsers(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            //Get all Users information
            ObservableList<Utilizator> userData = UtilizatorDao.searchUsers();
            //Populate Users on TableView
            populateUsers(userData);
        } catch (SQLException e){
            System.out.println("Error occurred while getting users information from DB.\n" + e);
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
    
    //Insert a user to the DB
    @FXML
    private void insertUser (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
        	UtilizatorDao.insertUser(usernameText.getText(),parolaText.getText(),numeText.getText(),tipText.getText());
            resultArea.setText("User inserted! \n");
        } catch (SQLException e) {
            resultArea.setText("Problem occurred while inserting user " + e);
            throw e;
        }
    }
    
    //Delete a user with a given username from DB
    @FXML
    private void deleteUser (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            UtilizatorDao.deleteUserWithUsername(casierUsernameText.getText());
            resultArea.setText("User deleted! Username: " + casierUsernameText.getText() + "\n");
        } catch (SQLException e) {
            resultArea.setText("Problem occurred while deleting user " + e);
            throw e;
        }
    }
}
