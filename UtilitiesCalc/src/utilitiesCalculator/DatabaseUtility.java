package utilitiesCalculator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DatabaseUtility {

		String DB_URL = "jdbc:derby:Personnel";
		
		
		
		
		

		/**
		 * creates a database named Personnel Will create a new table named employee
		 * if it doesn't exist Will drop any table named employee and create a new
		 * one
		 */
		public void creatDatabase() {
			String DB_URL_CREATE = "jdbc:derby:Personnel;create=true";
			Connection conn = null;

			try {
				conn = DriverManager.getConnection(DB_URL_CREATE);
				this.createEmployeeTable();
			} catch (SQLException e) {
				System.out.println("Database already exists");
				this.createEmployeeTable();
				System.out.println(e.getMessage());
			}
		}

		/**
		 * Utility method for dropping a table named Employee
		 */
		public void dropEmployeeTableFromPersonnelDB() {
			Connection connDrop = null;
			try {
				connDrop = DriverManager.getConnection(DB_URL);
				Statement stmt = connDrop.createStatement();
				stmt.execute("DROP TABLE employee");
			} catch (SQLException e) {
				System.out.println("Employee table does not exist");
			} finally {
				try {
					connDrop.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		}

		/**
		 * Check if a table named Employee exists, if it does, it drops the table
		 * and creates a new table New table will have a columns and SQL variables:
		 * name varchar(100) position varchar(100) payRtHrly double (PayRateHourly)
		 * Employee_ID int (primary key, increments by 1 and starts at 1)
		 */
		public void createEmployeeTable() {
			String createTableSQL = "CREATE TABLE Employee"
					+ "(name varchar(100), "
					+ "position varchar(255),"
					+ "payRtHrly double,"
					+ "Employee_ID int NOT NULL GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1),"
					+ "PRIMARY KEY(Employee_ID)" + ")";
			Connection conn = null;
			// String checkIfTableExists =
			// "IF ("
			// + "EXISTS ("
			// + "SELECT * FROM INFORMATION_SCHEMA.TABLES"
			// + " WHERE TABLE_NAME = 'TheTable'"
			// + ")"
			// + ")"
			// + "BEGIN "
			// + "DROP TABLE employee"
			// + "END";
			try {
				conn = DriverManager.getConnection(DB_URL);
				Statement stmt = conn.createStatement();
				this.dropEmployeeTableFromPersonnelDB();
				stmt.execute(createTableSQL);
				System.out.println("New Employee database has been created");
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}

		/**
		 * Inserts 5 sample rows into the database, one with the name Kyle Neuman
		 */
		public void inputSampleEntries() {
			Connection conn = null;
			String insertSampleRow1 = "INSERT INTO Employee (name, position, payRtHrly)"
					+ " VALUES('Telemundo Deltoro', 'stapler operator', 15.00)";
			String insertSampleRow2 = "INSERT INTO Employee (name, position, payRtHrly)"
					+ "VALUES('Smithery Smithonson', 'scissor operator', 15.00)";
			String insertSampleRow3 = "INSERT INTO Employee (name, position, payRtHrly)"
					+ "VALUES('Roger Rabit', 'staple remover', 15.00)";
			String insertSampleRow4 = "INSERT INTO Employee (name, position, payRtHrly)"
					+ "VALUES('Ignious Rockface', 'paper clip getter', 15.00)";
			String insertSampleRow5 = "INSERT INTO Employee (name, position, payRtHrly)"
					+ "VALUES('Kyle Neuman', 'Sole Proprieter', 100.00)";
			try {
				conn = DriverManager.getConnection(DB_URL);
				Statement stmt = conn.createStatement();
				stmt.executeUpdate(insertSampleRow1);
				stmt.executeUpdate(insertSampleRow2);
				stmt.executeUpdate(insertSampleRow3);
				stmt.executeUpdate(insertSampleRow4);
				stmt.executeUpdate(insertSampleRow5);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public ResultSet retriveTable() {
			Connection conn = null;
			ResultSet results = null;
			String firstRow = "SELECT * FROM Employee";
			// + "WHERE name='Kyle Neuman'";
			try {
				conn = DriverManager.getConnection(DB_URL);
				Statement stmt = conn.createStatement();
				results = stmt.executeQuery(firstRow);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return results;
		}

//		public ArrayList<Employee> fetchSelection(String SQLStatement) throws InvalidHourlyRateException {
//			Connection conn = null;
//			ResultSet result = null;
//			ArrayList<Employee> emp = new ArrayList<>();
//			try {
//				conn = DriverManager.getConnection(DB_URL);
//				Statement stmt = conn.createStatement();
//				result = stmt.executeQuery(SQLStatement);
//				while(result.next()){
//					emp.add(new Employee(result.getString(1), result.getString(2), result.getDouble(3), result.getInt(4)));
//				}
//				
//				if(emp.isEmpty()) throw new InvalidHourlyRateException("0");
//			} catch (SQLException e) {
//				throw new InvalidHourlyRateException("0");
//			} finally {
//				try {
//					conn.close();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//			
//			
//			return emp;
//
//		}

		public void updateDatabase(String SQLStatement) {
			Connection conn = null;
			try {
				conn = DriverManager.getConnection(DB_URL);
				Statement stmt = conn.createStatement();
				stmt.executeUpdate(SQLStatement);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}


