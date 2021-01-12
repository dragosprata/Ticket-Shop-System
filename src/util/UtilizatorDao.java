package util;

import java.sql.ResultSet;
import java.sql.SQLException;
import util.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Utilizator;

public class UtilizatorDao {
	
	//*******************************
    //SEARCH a User
    //*******************************
	public static Utilizator searchUser(String username) throws SQLException,ClassNotFoundException{
		//Declare a SELECT statement
		String selectStmt = "SELECT * FROM utilizator WHERE USERNAME = '"+username+"'";
		
		//Execute SELECT statement
		try{
			//Get ResultSet from dbExecuteQuery method
			ResultSet rsUser = HibernateUtil.dbExecuteQuery(selectStmt);
			
			//Send ResultSet to the getUserFromResultSet method and get user object
			Utilizator user = getUserFromResultSet(rsUser);
			
			//Return user object
			return user; 
		}catch(SQLException e){
			System.out.println("While searching an user with " + username + " username, an error occurred: " + e);
			//Return exception
			throw e;
		}
	}
	
	//Use ResultSet from DB as parameter and set user Object's attributes and return user object.
	private static Utilizator getUserFromResultSet(ResultSet rs) throws SQLException{
		Utilizator user = null;
		if(rs.next()){
			user = new Utilizator();
			user.setUsername(rs.getString("USERNAME"));
			user.setPassword(rs.getString("PAROLA"));
			user.setNume(rs.getString("NUME"));
			user.setType(rs.getString("TIP"));
			
		}
		return user;
	}
	
	//*******************************
    //SELECT users
    //*******************************
	public static ObservableList<Utilizator> searchUsers () throws SQLException, ClassNotFoundException {
		//Declare a SELECT statement
		String selectStmt = "SELECT * FROM utilizator";
		
		//Execute SELECT statement
		try{
			//Get ResultSet from dbExecuteQuery method
			ResultSet rsUsers = HibernateUtil.dbExecuteQuery(selectStmt);
			
			//Send ResultSet to the getUserList method and get user object
			ObservableList<Utilizator> userList = getUserList(rsUsers);
			
			//Return user object
			return userList;
		}catch (SQLException e){
			System.out.println("SQL select operation has been failed: " + e);
			//Return exception
			throw e;
		}
	}
	
	//Select * from users operation
	private static ObservableList<Utilizator> getUserList(ResultSet rs) throws SQLException,ClassNotFoundException{
		//Declare a observable List which comprises of user objects
		ObservableList<Utilizator> userList = FXCollections.observableArrayList();
		
		while (rs.next()){
			Utilizator user = new Utilizator();
			user.setUsername(rs.getString("USERNAME"));
			user.setPassword(rs.getString("PAROLA"));
			user.setNume(rs.getString("NUME"));
			user.setType(rs.getString("TIP"));
			userList.add(user);
		}
		//return userList (ObservableList of users)
		return userList;
	}
	
	 //*************************************
    //UPDATE an user's username
    //*************************************
	
	public static void updateUserUsername(String oldUser,String newUser) throws SQLException, ClassNotFoundException {
		//Declare a UPDATE statement
		String updateStmt = "UPDATE utilizator SET USERNAME='"+newUser+"' WHERE USERNAME = '"+oldUser+"'";
		//Execute UPDATE operation
		try{
			HibernateUtil.dbExecuteUpdate(updateStmt);
		}catch (SQLException e) {
			System.out.print("Error occurred while UPDATE Operation: " + e);
			throw e;
		}
	}
	
	 //*************************************
    //UPDATE an user's password
    //*************************************
	
	public static void updateUserPassword(String username,String pass) throws SQLException, ClassNotFoundException {
		//Declare a UPDATE statement
		String updateStmt = "UPDATE utilizator SET PAROLA='"+pass+"' WHERE USERNAME= '"+username+"'";
		//Execute UPDATE operation
		try{
			HibernateUtil.dbExecuteUpdate(updateStmt);
		}catch (SQLException e) {
			System.out.print("Error occurred while UPDATE Operation: " + e);
			throw e;
		}
	}
	
	//*************************************
    //DELETE an user
    //*************************************
	public static void deleteUserWithUsername(String username) throws SQLException, ClassNotFoundException {
		//Declare a DELETE statement
		String deleteStmt = "DELETE FROM utilizator WHERE username ='"+username+"'";
		//Execute DELETE operation
		try{
			HibernateUtil.dbExecuteUpdate(deleteStmt);
		} catch (SQLException e){
			System.out.print("Error occurred while DELETE Operation: " + e);
			throw e;
		}
	}
	
    //*************************************
    //INSERT an user
    //*************************************
	
	public static void insertUser (String username,String pass,String nume,String tip) throws SQLException, ClassNotFoundException{
		//Declare a INSERT statement
		String insertStmt = "INSERT INTO utilizator (USERNAME,PAROLA,NUME,TIP) VALUES ('"+username+"','"+pass+"','"+nume+"','"+tip+"')";
		
		//Execute INSERT operation
		try{
			HibernateUtil.dbExecuteUpdate(insertStmt);
		} catch (SQLException e){
			System.out.print("Error occurred while INSERT Operation: " + e);
			throw e;
		}
	}
}
