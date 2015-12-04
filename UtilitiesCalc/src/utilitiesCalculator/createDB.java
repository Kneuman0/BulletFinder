package utilitiesCalculator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class createDB {

	public static void main(String[] args) {
		
		final boolean DROP_TABLE = true;
		DatabaseUtility util = new DatabaseUtility();
		
		final String DB_URL = "jdbc:derby:db/UtilitiesCalc;create=true;";
		
		try{
			
			Connection conn = DriverManager.getConnection(DB_URL);
			
//			createTenantTable(conn, DROP_TABLE);
//			createBillMonthTable(conn, DROP_TABLE);
//			createBillMonthPerTenant(conn, DROP_TABLE);
//			createTenantTable(conn, DROP_TABLE);
			util.inputSampleTenantEntries();
			String allTenantsSQL = "SELECT * FROM tenant";
			ArrayList<Tenant> allTenants = util.fetchTenantSelection(allTenantsSQL);
			
			System.out.println(allTenants.get(0).tenantType);
			
			
			conn.close();
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("Database already exists");
		}
	}

	private static void createTenantTable(Connection conn, boolean input) throws SQLException {
		Statement stmt = conn.createStatement();
		if(input){
			try 
			{
				String dropTable = "drop table Tenant";
				stmt.execute(dropTable);
				System.out.println("Tenant table dropped.");
			} catch (Exception e) {
				
				System.out.println("Tenant table does not exist");
			}
		}
			
		String createTableSQL = "CREATE TABLE tenant (" 
				+ " name varchar(100),"
				+ " active boolean, "
				+ " tenantType varchar(100),"
				+ " tenant_ID int NOT NULL GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1),"
				+ " PRIMARY KEY(Tenant_ID)" + ")";
		
		stmt.execute(createTableSQL);
	}
	
	private static void createHouseTable(Connection conn, boolean input) throws SQLException {
		
		Statement stmt = conn.createStatement();
		if(input){
			try 
			{
				String dropTable = "drop table house";
				stmt.execute(dropTable);
				System.out.println("house table dropped.");
			} catch (Exception e) {
				
				System.out.println("house table does not exist");
			}
		}
			
		String createTableSQL = "CREATE TABLE house (" 
				+ " address varchar(100),"
				+ " numRooms integer,"
				+ " sqFt integer,"
				+ " house_ID int NOT NULL GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1),"
				+ " PRIMARY KEY(house_ID)" + ")";
		
		stmt.execute(createTableSQL);
	}
	
	private static void createBillMonthTable(Connection conn, boolean input) throws SQLException {
		
		Statement stmt = conn.createStatement();
		if(input){
			try 
			{
				String dropTable = "drop table billMonth";
				stmt.execute(dropTable);
				System.out.println("billMonth table dropped.");
			} catch (Exception e) {
				
				System.out.println("billMonth table does not exist");
			}
		}
			
		String createTableSQL = "CREATE TABLE billMonth (" 
				+ " date char(7),"
				+ " fossilFuel double,"
				+ " electric double,"
				+ " other double,"
				+ " billMonth_ID int NOT NULL GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1),"
				+ " PRIMARY KEY(billMonth_ID)" + ")";
		
		stmt.execute(createTableSQL);
	}
	
	private static void createBillMonthPerTenant(Connection conn, boolean input) throws SQLException {
		
		Statement stmt = conn.createStatement();
		if(input){
			try 
			{
				String dropTable = "drop table billPerTenant";
				stmt.execute(dropTable);
				System.out.println("billPerTenant table dropped.");
			} catch (Exception e) {
				
				System.out.println("billPerTenant table does not exist");
			}
		}
			
		String createTableSQL = "CREATE TABLE billPerTenant (" 
				+ " billingMonth_ID integer,"
				+ " house_ID integer,"
				+ " fte double,"
				+ " bill double,"
				+ " tenant_ID integer" + ")";
		
		stmt.execute(createTableSQL);
	}
}
