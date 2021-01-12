package util;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.Bilet;
import model.Spectacol;

public class BiletDao {

	//*******************************
    //SEARCH a ticket
    //*******************************
	public static Bilet searchTicket(String spectacol) throws SQLException,ClassNotFoundException{
		//Declare a SELECT statement
		String selectStmt = "SELECT * FROM bilet WHERE SPECTACOL = '"+spectacol+"'";
		
		//Execute SELECT statement
		try{
			//Get ResultSet from dbExecuteQuery method
			ResultSet rsTicket = HibernateUtil.dbExecuteQuery(selectStmt);
			
			//Send ResultSet to the getTicketFromResultSet method and get ticket object
			Bilet ticket = getTicketFromResultSet(rsTicket);
			
			//Return ticket object
			return ticket; 
		}catch(SQLException e){
			System.out.println("While searching a ticket at " + spectacol + " show, an error occurred: " + e);
			//Return exception
			throw e;
		}
	}
	
	//Use ResultSet from DB as parameter and set ticket Object's attributes and return ticket object.
	private static Bilet getTicketFromResultSet(ResultSet rs) throws SQLException{
		Bilet ticket = null;
		if(rs.next()){
			ticket = new Bilet();
			ticket.setDenumireSpectacol(rs.getString("SPECTACOL"));
			ticket.setNumarBilet(rs.getInt("NUMAR"));
			ticket.setRand(rs.getInt("RAND"));
		}
		return ticket;
	}
	
	//*******************************
    //SELECT tickets
    //*******************************
	public static ObservableList<Bilet> searchTickets () throws SQLException, ClassNotFoundException {
		//Declare a SELECT statement
		String selectStmt = "SELECT * FROM bilet";
		
		//Execute SELECT statement
		try{
			//Get ResultSet from dbExecuteQuery method
			ResultSet rsTickets = HibernateUtil.dbExecuteQuery(selectStmt);
			
			//Send ResultSet to the getTicketList method and get ticket object
			ObservableList<Bilet> ticketList = getTicketList(rsTickets);
			
			//Return ticket object
			return ticketList;
		}catch (SQLException e){
			System.out.println("SQL select operation has been failed: " + e);
			//Return exception
			throw e;
		}
	}
	
	//Select * from tickets operation
	private static ObservableList<Bilet> getTicketList(ResultSet rs) throws SQLException,ClassNotFoundException{
		//Declare a observable List which comprises of ticket objects
		ObservableList<Bilet> ticketList = FXCollections.observableArrayList();
		
		while (rs.next()){
			Bilet ticket = new Bilet();
			ticket.setDenumireSpectacol(rs.getString("SPECTACOL"));
			ticket.setNumarBilet(rs.getInt("NUMAR"));
			ticket.setRand(rs.getInt("RAND"));
			ticketList.add(ticket);
		}
		//return ticketList (ObservableList of tickets)
		return ticketList;
	}
	
	 //*************************************
    //INSERT a ticket
    //*************************************
	
	public static void insertTicket (String spectacol,String rand) throws SQLException, ClassNotFoundException{
		//Declare a INSERT statement
		String insertStmt = "INSERT INTO bilet (SPECTACOL,RAND) VALUES ('"+spectacol+"','"+rand+"')";
		
		Spectacol show = new Spectacol();
		Bilet ticket = new Bilet();
		int nrTotalBilete = show.getNrBilete();
		int nrBilet = ticket.getNumarBilet();
		int nrLocPerRand = ticket.getRand();
		
		//Execute INSERT operation
		try{
			if(nrBilet < nrTotalBilete){
			HibernateUtil.dbExecuteUpdate(insertStmt);
			} else {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Exception");
				alert.setHeaderText("The tickets is over!");
				alert.setContentText("You can buy tickets just at another show.");
				alert.show();
			if(nrLocPerRand < 20){
				HibernateUtil.dbExecuteUpdate(insertStmt);
			} else {
				Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
				alert2.setTitle("Exception");
				alert2.setHeaderText("The places on this row are over!");
				alert2.setContentText("You can buy tickets just at another row.");
				alert2.show();
			}
			}
		} catch (SQLException e){
			System.out.print("Error occurred while INSERT Operation: " + e);
			throw e;
		}
	}
}
