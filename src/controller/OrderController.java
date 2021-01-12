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
import model.Comanda;
import model.Utilizator;
import util.ComandaDao;
import util.UtilizatorDao;

public class OrderController {

	@FXML
	private TextField cumparatorText;
	@FXML
	private TextField spectacolText;
	@FXML
	private TextField nrBileteText;
	
	@FXML
    private TextArea resultArea;
	
	@FXML
    private TableView ordersTable;
	
	@FXML
	private TableColumn<Comanda, String>  cumparatorColumn;
	@FXML
	private TableColumn<Comanda, String> spectacolColumn;
	@FXML
	private TableColumn<Comanda, Integer> nrBileteColumn;
	
	 //Search all orders
    @FXML
    private void searchOrders(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            //Get all Orders information
            ObservableList<Comanda> orderData = ComandaDao.searchOrders();
            //Populate Orders on TableView
            populateOrders(orderData);
        } catch (SQLException e){
            System.out.println("Error occurred while getting orders information from DB.\n" + e);
            throw e;
        }
    }
    
    //Initializing the controller class.
    //This method is automatically called after the fxml file has been loaded.
    @FXML
    private void initialize () {
       
    	cumparatorColumn.setCellValueFactory(cellData -> cellData.getValue().cumparatorProperty());
    	spectacolColumn.setCellValueFactory(cellData -> cellData.getValue().spectacolProperty());
    	nrBileteColumn.setCellValueFactory(cellData -> cellData.getValue().nrBileteProperty().asObject());
    }
    
    //Populate Order
    @FXML
    private void populateOrder (Comanda order) throws ClassNotFoundException {
        //Declare and ObservableList for table view
        ObservableList<Comanda> orderData = FXCollections.observableArrayList();
        //Add order to the ObservableList
        orderData.add(order);
        //Set items to the orderTable
        ordersTable.setItems(orderData);
    }
    
    //Set Order information to Text Area
    @FXML
    private void setOrderInfoToTextArea (Comanda order) {
        resultArea.setText("Cumparator: " + order.getCumparator() + "\n" + "Spectacol: " + order.getSpectacol() + "\n" + "Nr.bilete: " + order.getNrBilete());
    }
    
    //Populate Order for TableView and Display Order on TextArea
    @FXML
    private void populateAndShowOrder(Comanda order) throws ClassNotFoundException {
        if (order != null) {
            populateOrder(order);
            setOrderInfoToTextArea(order);
        } else {
            resultArea.setText("This order does not exist!\n");
        }
    }
    
    //Populate Orders for TableView
    @FXML
    private void populateOrders (ObservableList<Comanda> orderData) throws ClassNotFoundException {
        //Set items to the ordersTable
    	ordersTable.setItems(orderData);
    }
    
    //Insert a order to the DB
    @FXML
    private void insertOrder (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
        	ComandaDao.insertOrder(cumparatorText.getText(),spectacolText.getText(),nrBileteText.getText());
            resultArea.setText("Order inserted! \n");
        } catch (SQLException e) {
            resultArea.setText("Problem occurred while inserting order " + e);
            throw e;
        }
    }
}
