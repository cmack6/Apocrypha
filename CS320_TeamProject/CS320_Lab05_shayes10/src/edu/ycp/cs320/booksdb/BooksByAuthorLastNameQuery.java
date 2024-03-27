package edu.ycp.cs320.booksdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Scanner;

import servlets.DBUtil;

public class BooksByAuthorLastNameQuery {
	public static void main(String[] args) throws Exception {
		// load Derby JDBC driver
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		} catch (Exception e) {
			System.err.println("Could not load Derby JDBC driver");
			System.err.println(e.getMessage());
			System.exit(1);
		}

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		
		PreparedStatement stmt2 = null;
		Connection conn2 = null;
		ResultSet resultSet2 = null;

		// connect to the database
		conn = DriverManager.getConnection("jdbc:derby:test.db;create=true");
		conn2 = DriverManager.getConnection("jdbc:derby:test.db;create=true");

		@SuppressWarnings("resource")
		Scanner keyboard = new Scanner(System.in);

		try {
			conn.setAutoCommit(true);
			
			// prompt user for title to search for
			System.out.print("Author's first name: ");
			String firstname = keyboard.nextLine();
			System.out.print("Author's last name: ");
			String lastname = keyboard.nextLine();


			// a canned query to find book information (including author name) from title
			stmt = conn.prepareStatement(
					"select author_id "
					+ "  from authors "
					+ "  where  authors.firstname = ?"
					+ "       and authors.lastname = ?"    
			);

			// substitute the title entered by the user for the placeholder in the query
			stmt.setString(1, firstname);
			stmt.setString(2,  lastname);

			// execute the query
			resultSet = stmt.executeQuery();
			
			ResultSetMetaData resultSchema = stmt.getMetaData();

			// iterate through the returned tuples, printing each one
			// count # of rows returned
			int rowsReturned = 0;
			
			while (resultSet.next()) {
				for (int i = 1; i <= resultSchema.getColumnCount(); i++) {
					Object obj = resultSet.getObject(i);
					if (i > 1) {
						System.out.print(",");
					}
					System.out.print(obj.toString());
				}
				System.out.println();
				
				// count # of rows returned
				rowsReturned++;
			}
			
			
			try {
				conn2.setAutoCommit(true);
				
			Object p1 = resultSet.getObject(0);
			String id = p1.toString();
			
			System.out.print("Book Title: ");
			String title = keyboard.nextLine();
			System.out.print("Book ISBN: ");
			String isbn = keyboard.nextLine();
			System.out.print("Book Publishing Year: ");
			String year = keyboard.nextLine();
			
			stmt2 = conn2.prepareStatement(
					"insert into books (author_id, title, isbn, published)"
					+ "values (?, ?, ?, ?,)"
					);
			
			stmt2.setString(1, id);
			stmt2.setString(2, title);
			stmt2.setString(3, isbn);
			stmt2.setString(4, year);
			
			resultSet2 = stmt2.executeQuery();
			
			// get the precise schema of the tuples returned as the result of the query
			ResultSetMetaData resultSchema2 = stmt2.getMetaData();

			}
			catch (Exception e) {
				System.err.println("Could not load Derby JDBC driver");
				System.err.println(e.getMessage());
				System.exit(1);
			}
		} finally {
			// close result set, statement, connection
			DBUtil.closeQuietly(resultSet2);
			DBUtil.closeQuietly(stmt2);
			DBUtil.closeQuietly(conn2);
			DBUtil.closeQuietly(resultSet);
			DBUtil.closeQuietly(stmt);
			DBUtil.closeQuietly(conn);
		}
	}
}
