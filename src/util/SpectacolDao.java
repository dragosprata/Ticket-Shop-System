package util;

import java.sql.ResultSet;
import java.sql.SQLException;


import util.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Spectacol;

public class SpectacolDao {
	
	//*******************************
    //SELECT a Show
    //*******************************
	public static Spectacol searchShow(String denumire) throws SQLException,ClassNotFoundException{
		//Declare a SELECT statement
		String selectStmt = "SELECT * FROM spectacol WHERE DENUMIRE = '"+denumire+"'" ;
		
		//Execute SELECT statement
		try{
			//Get ResultSet from dbExecuteQuery method
			ResultSet rsShow = HibernateUtil.dbExecuteQuery(selectStmt);
			
			//Send ResultSet to the getShowFromResultSet method and get show object
			Spectacol show = getShowFromResultSet(rsShow);
			
			//Return client object
			return show; 
		}catch(SQLException e){
			System.out.println("While searching a show with " + denumire + " name, an error occurred: " + e);
			//Return exception
			throw e;
		}
	}
	
	//Use ResultSet from DB as parameter and set Spectacol Object's attributes and return show object.
	private static Spectacol getShowFromResultSet(ResultSet rs) throws SQLException{
		Spectacol show = null;
		if(rs.next()){
			show = new Spectacol();
			show.setDenumire(rs.getString("DENUMIRE"));
			show.setGen(rs.getString("GEN"));
			show.setRegie(rs.getString("REGIE"));
			show.setDistributie(rs.getString("DISTRIBUTIE"));
			show.setPremiera(rs.getString("PREMIERA"));
			show.setNrBilete(rs.getInt("NR_BILETE"));
		}
		return show;
	}
	
	//*******************************
    //SELECT Shows
    //*******************************
	public static ObservableList<Spectacol> searchShows () throws SQLException, ClassNotFoundException {
		//Declare a SELECT statement
		String selectStmt = "SELECT * FROM spectacol";
		
		//Execute SELECT statement
		try{
			//Get ResultSet from dbExecuteQuery method
			ResultSet rsShows = HibernateUtil.dbExecuteQuery(selectStmt);
			
			//Send ResultSet to the getShowList method and get show object
			ObservableList<Spectacol> showList = getShowsList(rsShows);
			
			//Return show object
			return showList;
		}catch (SQLException e){
			System.out.println("SQL select operation has been failed: " + e);
			//Return exception
			throw e;
		}
	}
	
	//Select * from shows operation
	private static ObservableList<Spectacol> getShowsList(ResultSet rs) throws SQLException,ClassNotFoundException{
		//Declare a observable List which comprises of Show objects
		ObservableList<Spectacol> showList = FXCollections.observableArrayList();
		
		while (rs.next()){
			Spectacol show = new Spectacol();
			show.setDenumire(rs.getString("DENUMIRE"));
			show.setGen(rs.getString("GEN"));
			show.setRegie(rs.getString("REGIE"));
			show.setDistributie(rs.getString("DISTRIBUTIE"));
			show.setPremiera(rs.getString("PREMIERA"));
			show.setNrBilete(rs.getInt("NR_BILETE"));
			showList.add(show);
		}
		//return clientList (ObservableList of Clients)
		return showList;
	}
	
	 //*************************************
    //UPDATE a show's opening
    //*************************************
	
	public static void updateShowOpening(String denumire,String premiera) throws SQLException, ClassNotFoundException {
		//Declare a UPDATE statement
		String updateStmt = "UPDATE spectacol SET PREMIERA='"+premiera+"' WHERE DENUMIRE = '"+denumire+"'";
		//Execute UPDATE operation
		try{
			HibernateUtil.dbExecuteUpdate(updateStmt);
		}catch (SQLException e) {
			System.out.print("Error occurred while UPDATE Operation: " + e);
			throw e;
		}
	}
	
	 //*************************************
    //UPDATE a show's numbers of tickets
    //*************************************
	
	public static void updateShowNrOfTickets(String denumire,String nrBilete) throws SQLException, ClassNotFoundException {
		//Declare a UPDATE statement
		String updateStmt = "UPDATE spectacol SET NR_BILETE='"+nrBilete+"' WHERE DENUMIRE = '"+denumire+"'";
		//Execute UPDATE operation
		try{
			HibernateUtil.dbExecuteUpdate(updateStmt);
		}catch (SQLException e) {
			System.out.print("Error occurred while UPDATE Operation: " + e);
			throw e;
		}
	}
	
	//*************************************
    //DELETE a show
    //*************************************
	public static void deleteShowWithName(String denumire) throws SQLException, ClassNotFoundException {
		//Declare a DELETE statement
		String deleteStmt = "DELETE FROM spectacol WHERE denumire ='"+denumire+"'";
		//Execute DELETE operation
		try{
			HibernateUtil.dbExecuteUpdate(deleteStmt);
		} catch (SQLException e){
			System.out.print("Error occurred while DELETE Operation: " + e);
			throw e;
		}
	}
	
    //*************************************
    //INSERT a show
    //*************************************
	
	public static void insertShow (String denumire,String gen,String regie,String distributie,String premiera,String nrBilete) throws SQLException, ClassNotFoundException{
		//Declare a INSERT statement
		String insertStmt = "INSERT INTO spectacol (DENUMIRE,GEN,REGIE,DISTRIBUTIE,PREMIERA,NR_BILETE) VALUES ('"+denumire+"','"+gen+"','"+regie+"','"+distributie+"','"+premiera+"','"+nrBilete+"')";
		
		//Execute INSERT operation
		try{
			HibernateUtil.dbExecuteUpdate(insertStmt);
		} catch (SQLException e){
			System.out.print("Error occurred while INSERT Operation: " + e);
			throw e;
		}
	}
}
