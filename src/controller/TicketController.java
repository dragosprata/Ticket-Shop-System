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
import model.Bilet;
import util.BiletDao;

public class TicketController {

	@FXML
	private TextField spectacolText;
	@FXML
	private TextField randText;
	
	@FXML
	private TextField denumireSpectacolText;
	
	
	@FXML
    private TextArea resultArea;
	
	@FXML
    private TableView ticketTable;
	
	@FXML
	private TableColumn<Bilet, String> spectacolColumn;
	@FXML
	private TableColumn<Bilet, Integer>  numarColumn;
	@FXML
	private TableColumn<Bilet, Integer>  randColumn;
	
	//Search a Ticket after show
    @FXML
    private void searchTicket (ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        try {
            //Get Ticket information
            Bilet ticket = BiletDao.searchTicket(denumireSpectacolText.getText());
            //Populate Ticket on TableView and Display on TextArea
            populateAndShowTicket(ticket);
        } catch (SQLException e) {
            e.printStackTrace();
            resultArea.setText("Error occurred while getting ticket information from DB.\n" + e);
            throw e;
        }
    }
	
    //Search all tickets
    @FXML
    private void searchTickets(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            //Get all Tickets information
            ObservableList<Bilet> ticketData = BiletDao.searchTickets();
            //Populate Tickets on TableView
            populateTickets(ticketData);
        } catch (SQLException e){
            System.out.println("Error occurred while getting tickets information from DB.\n" + e);
            throw e;
        }
    }
    
    //Initializing the controller class.
    //This method is automatically called after the fxml file has been loaded.
    @FXML
    private void initialize () {
    	
    	spectacolColumn.setCellValueFactory(cellData -> cellData.getValue().spectacolDenumireProperty());
    	numarColumn.setCellValueFactory(cellData -> cellData.getValue().numarBiletProperty().asObject());
    	randColumn.setCellValueFactory(cellData -> cellData.getValue().randProperty().asObject());
    }
    
    //Populate Ticket
    @FXML
    private void populateTicket (Bilet ticket) throws ClassNotFoundException {
        //Declare and ObservableList for table view
        ObservableList<Bilet> ticketData = FXCollections.observableArrayList();
        //Add ticket to the ObservableList
        ticketData.add(ticket);
        //Set items to the ticketTable
        ticketTable.setItems(ticketData);
    }
    
    //Set Ticket information to Text Area
    @FXML
    private void setTicketInfoToTextArea (Bilet ticket) {
        resultArea.setText("Spectacol: " + ticket.getDenumireSpectacol() + "\n" +"Numar: " + ticket.getNumarBilet() + "\n" + "Rand: " + ticket.getRand());
    }
    
    //Populate Ticket for TableView and Display Ticket on TextArea
    @FXML
    private void populateAndShowTicket(Bilet ticket) throws ClassNotFoundException {
        if (ticket != null) {
            populateTicket(ticket);
            setTicketInfoToTextArea(ticket);
        } else {
            resultArea.setText("This ticket does not exist!\n");
        }
    }
    
    //Populate Tickets for TableView
    @FXML
    private void populateTickets (ObservableList<Bilet> ticketData) throws ClassNotFoundException {
        //Set items to the ticketTable
    	ticketTable.setItems(ticketData);
    }
    
    //Insert a ticket to the DB
    @FXML
    private void insertTicket (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
        	BiletDao.insertTicket(spectacolText.getText(),randText.getText());
            resultArea.setText("Ticket inserted! \n");
        } catch (SQLException e) {
            resultArea.setText("Problem occurred while inserting ticket " + e);
            throw e;
        }
    }  
}
