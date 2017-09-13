package mastermind;

/*
 * value object that represents a number of pegs, black or white
 * also represents the state of the game: 4 black pegs = win
 */
public class GameState 
{
	final int blackPegs;
	final int whitePegs;
	Boolean gameOver = false;
	
	public GameState(int b, int w)
	{
		blackPegs = b;
		whitePegs = w;
	}
	
	public String toString()
	{
		return "white pegs: " + whitePegs + " black pegs: " + blackPegs;
	}
}
