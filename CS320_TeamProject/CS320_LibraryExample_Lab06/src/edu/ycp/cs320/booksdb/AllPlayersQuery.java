package edu.ycp.cs320.booksdb;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.ycp.cs320.booksdb.model.*;
import edu.ycp.cs320.booksdb.persist.DatabaseProvider;
import edu.ycp.cs320.booksdb.persist.IDatabase;

public class AllPlayersQuery {
	public static void main(String[] args) throws Exception {
		Scanner keyboard = new Scanner(System.in);

		// Create the default IDatabase instance
		InitDatabase.init(keyboard);
		
		// get the DB instance and execute transaction
		IDatabase db = DatabaseProvider.getInstance();
		List<Player> playerList = db.findAllPlayers();
		
		// check if anything was returned and output the list
		if (playerList.isEmpty()) {
			System.out.println("There are no players in the database");
		}
		else {
			for (Player player: playerList) {
				
				System.out.println(player.getPlayerID() + ", " + player.getScore() + ", " + player.getHealth() + ", " + player.getRoomID() + ", " + player.getGameID() + ", " + player.getUserID() + ", " + player.isInCombat() + ", " +  player.getLog());
			
			}
		}
		System.out.println("what gameid?");
		int gameID = keyboard.nextInt();
		Player player = db.getPlayerFromGameID(gameID);
		System.out.println(player.getGameID() + " is the gameID, and " + player.getHealth() + " is their health.");
	}
}
