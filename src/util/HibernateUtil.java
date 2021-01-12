package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.sun.rowset.CachedRowSetImpl;

import model.Spectacol;

public class HibernateUtil {

	private static SessionFactory sessionFactory = null;
	
	static{
		try{
			loadSessionFactory();
		}catch(Exception e){
			System.err.println("Exception while initializing hibernate util...");
			e.printStackTrace();
		}
	}
	
	public static void loadSessionFactory(){
		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		configuration.addAnnotatedClass(Spectacol.class);
		ServiceRegistry srvcReg = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		sessionFactory = configuration.buildSessionFactory(srvcReg);
	}
	
	public static Session getSession() throws HibernateException{
		Session retSession = null;
		try{
			retSession = sessionFactory.openSession();
		}catch(Throwable t){
			System.err.println("Exception while getting session...");
			t.printStackTrace();
		}
		if(retSession == null){
			System.err.println("session is discovered null");
		}
		return retSession;
	}
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    //Connection
    private static Connection conn = null;

    private static final String DBURL = "jdbc:mysql://localhost:3306/Opera";
    

    private static final String USER = "dragos";
    private static final String PASS = "prata1996";
    
    //Connect to DB
    public static void dbConnect() throws SQLException, 

ClassNotFoundException {
        //Setting MySQL JDBC Driver
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your MySQL JDBC Driver?");
            e.printStackTrace();
            throw e;
        }

        System.out.println("MySQL JDBC Driver Registered!");

        //Establish the MySQL Connection using Connection String
        try {
            
            conn = DriverManager.getConnection(DBURL,USER,PASS);
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console" + e);
            e.printStackTrace();
            throw e;
        }
    }

    //Close Connection
    public static void dbDisconnect() throws SQLException {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (Exception e){
           throw e;
        }
    }
	
	//DB Execute Query Operation
    public static ResultSet dbExecuteQuery(String queryStmt) 

throws SQLException, ClassNotFoundException {
        //Declare statement, resultSet and CachedResultSet as null
        Statement stmt = null;
        ResultSet resultSet = null;
        CachedRowSetImpl crs = null;
        try {
            //Connect to DB (Establish MySQL Connection)
            dbConnect();
            System.out.println("Select statement: " + queryStmt + "\n");

            //Create statement
            stmt = conn.createStatement();

            //Execute select (query) operation
            resultSet = stmt.executeQuery(queryStmt);

            //CachedRowSet Implementation
            //In order to prevent "java.sql.SQLRecoverableException: Closed Connection: next" error
            //We are using CachedRowSet
            crs = new CachedRowSetImpl();
            crs.populate(resultSet);
        } catch (SQLException e) {
            System.out.println("Problem occurred at executeQuery operation : " + e);
            throw e;
        } finally {
            if (resultSet != null) {
                //Close resultSet
                resultSet.close();
            }
            if (stmt != null) {
                //Close Statement
                stmt.close();
            }
            //Close connection
            dbDisconnect();
        }
        //Return CachedRowSet
        return crs;
    }
    
    //DB Execute Update (For Update/Insert/Delete) Operation
    public static void dbExecuteUpdate(String sqlStmt) throws SQLException, ClassNotFoundException {
        //Declare statement as null
        Statement stmt = null;
        try {
            //Connect to DB (Establish MySQL Connection)
            dbConnect();
            //Create Statement
            stmt = conn.createStatement();
            //Run executeUpdate operation with given sql statement
            stmt.executeUpdate(sqlStmt);
        } catch (SQLException e) {
            System.out.println("Problem occurred at executeUpdate operation : " + e);
            throw e;
        } finally {
            if (stmt != null) {
                //Close statement
                stmt.close();
            }
            //Close connection
            dbDisconnect();
        }
    }
}
