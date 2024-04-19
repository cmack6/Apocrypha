package edu.ycp.cs320.booksdb;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.ycp.cs320.booksdb.model.*;
import edu.ycp.cs320.booksdb.persist.DatabaseProvider;
import edu.ycp.cs320.booksdb.persist.IDatabase;

public class AllRoomConnections {
	public static void main(String[] args) throws Exception {
		Scanner keyboard = new Scanner(System.in);

		// Create the default IDatabase instance
		InitDatabase.init(keyboard);
		
		//System.out.println("enter a gameID : ");
		//int gameID = keyboard.nextInt();
		// get the DB instance and execute transaction
		IDatabase db = DatabaseProvider.getInstance();
		List<RoomConnection> roomConnectionList = db.findRoomConnections();
		
		// check if anything was returned and output the list
		if (roomConnectionList.isEmpty()) {
			System.out.println("There are no rooms in the database for this gameID");
		}
		else {
			for (RoomConnection roomConnections : roomConnectionList) {
				
				System.out.println(roomConnections.getStartingRoomID() + ", " + roomConnections.getCommand() + ", " + roomConnections.getDestinationRoomID());
			
			}
		}
	}
}
