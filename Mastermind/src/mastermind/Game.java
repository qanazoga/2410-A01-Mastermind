package mastermind;

import java.util.Scanner;

public class Game 
{
	Code winningCode; 
	Code candidateCode;
	Boolean gameInProgress = true;
	int turn;
	int gameLength;
	
	
	public Game()
	{
		gameLength = 3;
		turn = 1;
		// generate winning sequence 
		winningCode = new Code();
		winningCode.generate();		
		// initialize candidate sequence
		candidateCode = new Code();
	}
	
	/*
	 * add a color to the candidate code in the next available slot
	 */
	public void Guess(int guess)
	{
		// TODO handle too many guesses, allow taking back guesses
		candidateCode.sequence.add(guess);
	}
	
	/*
	 * checks for a win/loss,
	 * advances to the next turn and
	 * resets the candidate code
	 */
	public GameState NextTurn()
	{
		// check if we won
		GameState result = candidateCode.compare(winningCode);

		if (result.blackPegs == candidateCode.sequence.size() || turn++ == gameLength )
		{
			result.gameOver = true;
		}
		else
		{
			
			candidateCode = new Code(); // reset the candidate	
		}
		return result;

	}

	/*
	 * contains the main game loop
	 */
	public void run()
	{
		
		System.out.println("game generated. winning code is: " + winningCode.sequence.toString());
		Scanner input = new Scanner(System.in);
		
		// main loop
		// guess --> nextTurn

			do {
				System.out.println("======TURN " + turn + " ===== \nSelect 1 number and press enter:");
				for (int i = 0; i < 4; i++) {
					Guess(input.nextInt());
				}
				System.out.println("   candidate: " + candidateCode.sequence.toString());
				System.out.println("winning code: " + winningCode.sequence.toString());
				System.out.println(candidateCode.compare(winningCode).toString() + "\n");
			} while (!NextTurn().gameOver);
			
		// handle end of game
		if (turn > gameLength)
		{
			System.out.println("you lose"); 
		}
		else
		{
			System.out.println("good job! you won!");
		}
	}	
	
	
}
