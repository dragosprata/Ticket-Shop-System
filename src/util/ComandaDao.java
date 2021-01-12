package util;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Comanda;


public class ComandaDao {
	
	//Use ResultSet from DB as parameter and set user Object's attributes and return order object.
		private static Comanda getOrderFromResultSet(ResultSet rs) throws SQLException{
			Comanda order = null;
			if(rs.next()){
				order = new Comanda();
				order.setCumparator(rs.getString("CUMPARATOR"));
				order.setSpectacol(rs.getString("SPECTACOL"));
				order.setNrBilete(rs.getInt("NR_BILETE"));
			}
			return order;
		}
	
		//*******************************
	    //SELECT orders
	    //*******************************
		public static ObservableList<Comanda> searchOrders () throws SQLException, ClassNotFoundException {
			//Declare a SELECT statement
			String selectStmt = "SELECT * FROM comanda";
			
			//Execute SELECT statement
			try{
				//Get ResultSet from dbExecuteQuery method
				ResultSet rsOrders = HibernateUtil.dbExecuteQuery(selectStmt);
				
				//Send ResultSet to the getOrderList method and get order object
				ObservableList<Comanda> orderList = getOrderList(rsOrders);
				
				//Return order object
				return orderList;
			}catch (SQLException e){
				System.out.println("SQL select operation has been failed: " + e);
				//Return exception
				throw e;
			}
		}
		
		//Select * from orders operation
		private static ObservableList<Comanda> getOrderList(ResultSet rs) throws SQLException,ClassNotFoundException{
			//Declare a observable List which comprises of order objects
			ObservableList<Comanda> orderList = FXCollections.observableArrayList();
			
			while (rs.next()){
				Comanda order = new Comanda();
				order.setCumparator(rs.getString("CUMPARATOR"));
				order.setSpectacol(rs.getString("SPECTACOL"));
				order.setNrBilete(rs.getInt("NR_BILETE"));
				orderList.add(order);
			}
			//return orderList (ObservableList of orders)
			return orderList;
		}
		
		
	 //*************************************
    //INSERT an order
    //*************************************
	
	public static void insertOrder (String cumparator,String spectacol,String nr_bilete) throws SQLException, ClassNotFoundException{
		//Declare a INSERT statement
		String insertStmt = "INSERT INTO Comanda (CUMPARATOR,SPECTACOL,NR_BILETE) VALUES ('"+cumparator+"','"+spectacol+"','"+nr_bilete+"')";
		
		//Execute INSERT operation
		try{
			HibernateUtil.dbExecuteUpdate(insertStmt);
		} catch (SQLException e){
			System.out.print("Error occurred while INSERT Operation: " + e);
			throw e;
		}
	}
}
