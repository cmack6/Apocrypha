package edu.ycp.cs320.booksdb;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.ycp.cs320.booksdb.model.*;
import edu.ycp.cs320.booksdb.persist.DatabaseProvider;
import edu.ycp.cs320.booksdb.persist.IDatabase;

public class AllNPCsQuery {
	public static void main(String[] args) throws Exception {
		Scanner keyboard = new Scanner(System.in);

		// Create the default IDatabase instance
		InitDatabase.init(keyboard);
		
		// get the DB instance and execute transaction
		IDatabase db = DatabaseProvider.getInstance();
		List<NPC> NPCList = db.findAllNPCs();
		
		// check if anything was returned and output the list
		if (NPCList.isEmpty()) {
			System.out.println("There are no players in the database");
		}
		else {
			for (NPC npc: NPCList) {
				
				System.out.println(npc.getNPCID() + ", " + npc.getRoomDialogue() + ", " + npc.getSpeakDialogue() + ", " + npc.getRoomID() + ", " + npc.getHealth() + ", " + npc.getGameID());
			
			}
		}
	}
}
