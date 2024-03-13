package edu.ycp.cs320.lab02.controller;

import edu.ycp.cs320.lab02.model.GuessingGame;

/**
 * Controller for the guessing game.
 */
public class GuessingGameController {
	private GuessingGame model;

	/**
	 * Set the model.
	 * 
	 * @param model the model to set
	 */
	public void setModel(GuessingGame model) {
		this.model = model;
	}

	/**
	 * Start a new guessing game by setting the minimum to 1 and the maximum to 100.
	 */
	public void startGame() {
		model.setMax(100);
		model.setMin(1);
	 }

	/**
	 * Called to indicate that the current guess is correct.
	 * Set the min and max to the current guess.
	 */
	public void setNumberFound() {
		
		int currentGuess = model.getGuess();
	
			model.setMin(currentGuess);
			model.setMax(currentGuess);
	}

	/**
	 * Called to indicate that the user is thinking of a number that
	 * is less than the current guess.
	 */
	public void setNumberIsLessThanGuess() {
		model.setIsLessThan(model.getGuess());
	}

	
	/**
	 * Called to indicate that the user is thinking of a number that
	 * is greater than the current guess.
	 */
	public void setNumberIsGreaterThanGuess() {
		model.setIsGreaterThan(model.getGuess());
	}
}
