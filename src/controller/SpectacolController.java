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
import util.SpectacolDao;
import model.Spectacol;

public class SpectacolController {
	
	@FXML
	private TextField denumireText;
	@FXML
	private TextField genText;
	@FXML
	private TextField regieText;
	@FXML
	private TextField distributieText;
	@FXML
	private TextField premieraText;
	@FXML
	private TextField nrBileteText;
	@FXML
	private TextField denumireSpectacolText;
	@FXML
	private TextField newOpeningText;
	@FXML
	private TextField newNrOfTicketsText;
	
	@FXML
    private TextArea resultArea;
	
	@FXML
    private TableView showTable;
	
    @FXML
    private TableColumn<Spectacol, String>  denumireSpectacolColumn;
    @FXML
    private TableColumn<Spectacol, String>  genColumn;
    @FXML
    private TableColumn<Spectacol, String> regieColumn;
    @FXML
    private TableColumn<Spectacol, String> distributieColumn;
    @FXML
    private TableColumn<Spectacol, String> premieraColumn;
    @FXML
    private TableColumn<Spectacol, Integer> nrBileteColumn;
    
    //Search a show after name
    @FXML
    private void searchShow (ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        try {
            //Get Show information
            Spectacol show = SpectacolDao.searchShow(denumireSpectacolText.getText());
            //Populate Show on TableView and Display on TextArea
            populateAndShowSpectacol(show);
        } catch (SQLException e) {
            e.printStackTrace();
            resultArea.setText("Error occurred while getting show information from DB.\n" + e);
            throw e;
        }
    }
    
    //Search all shows
    @FXML
    private void searchShows(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            //Get all Shows information
            ObservableList<Spectacol> showData = SpectacolDao.searchShows();
            //Populate Shows on TableView
            populateShows(showData);
        } catch (SQLException e){
            System.out.println("Error occurred while getting shows information from DB.\n" + e);
            throw e;
        }
    }

    //Initializing the controller class.
    //This method is automatically called after the fxml file has been loaded.
    @FXML
    private void initialize () {
       
    	denumireSpectacolColumn.setCellValueFactory(cellData -> cellData.getValue().spectacolDenumireProperty());
    	genColumn.setCellValueFactory(cellData -> cellData.getValue().genProperty());
    	regieColumn.setCellValueFactory(cellData -> cellData.getValue().regieProperty());
    	distributieColumn.setCellValueFactory(cellData -> cellData.getValue().distributieProperty());
    	premieraColumn.setCellValueFactory(cellData -> cellData.getValue().premieraProperty());
    	nrBileteColumn.setCellValueFactory(cellData -> cellData.getValue().nrBileteProperty().asObject());
    }
    
    //Populate Show
    @FXML
    private void populateShow (Spectacol show) throws ClassNotFoundException {
        //Declare and ObservableList for table view
        ObservableList<Spectacol> showData = FXCollections.observableArrayList();
        //Add show to the ObservableList
        showData.add(show);
        //Set items to the showTable
        showTable.setItems(showData);
    }
    
    //Set Show information to Text Area
    @FXML
    private void setShowInfoToTextArea (Spectacol show) {
        resultArea.setText("Denumire: " + show.getDenumire() + "\n" +"Gen: " + show.getGen() + "\n" + "Regie: " + show.getRegie() + "\n" + "Distributie: " + show.getDistributie() + "\n" + "Premiera: " + show.getPremiera() + "\n" + "Nr.bilete: " + show.getNrBilete());
    }
    
    //Populate Show for TableView and Display Show on TextArea
    @FXML
    private void populateAndShowSpectacol(Spectacol show) throws ClassNotFoundException {
        if (show != null) {
            populateShow(show);
            setShowInfoToTextArea(show);
        } else {
            resultArea.setText("This show does not exist!\n");
        }
    }
    
    //Populate Shows for TableView
    @FXML
    private void populateShows (ObservableList<Spectacol> showData) throws ClassNotFoundException {
        //Set items to the showTable
    	showTable.setItems(showData);
    }
    
    //Update show's opening with the opening which is written in newOpeningTextField
    @FXML
    private void updateShowOpening (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
        	SpectacolDao.updateShowOpening(denumireSpectacolText.getText(),newOpeningText.getText());
            resultArea.setText("Opening has been updated for, show name: " + denumireSpectacolText.getText() + "\n");
        } catch (SQLException e) {
            resultArea.setText("Problem occurred while updating opening: " + e);
        }
    }
    
    //Update show's number of tickets with the number of tickets which is written on newNrOfTicketsTextField
    @FXML
    private void updateShowNrOfTickets (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
        	SpectacolDao.updateShowNrOfTickets(denumireSpectacolText.getText(),newNrOfTicketsText.getText());
            resultArea.setText("Number of tickets has been updated for, show name: " + denumireSpectacolText.getText() + "\n");
        } catch (SQLException e) {
            resultArea.setText("Problem occurred while updating number of tickets: " + e);
        }
    }
    
    //Insert a show to the DB
    @FXML
    private void insertShow (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
        	SpectacolDao.insertShow(denumireText.getText(),genText.getText(),regieText.getText(),distributieText.getText(),premieraText.getText(),nrBileteText.getText());
            resultArea.setText("Show inserted! \n");
        } catch (SQLException e) {
            resultArea.setText("Problem occurred while inserting show " + e);
            throw e;
        }
    }
    
    //Delete a show with a given name from DB
    @FXML
    private void deleteShow (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            SpectacolDao.deleteShowWithName(denumireSpectacolText.getText());
            resultArea.setText("Show deleted! Show name: " + denumireSpectacolText.getText() + "\n");
        } catch (SQLException e) {
            resultArea.setText("Problem occurred while deleting show " + e);
            throw e;
        }
    }


}
