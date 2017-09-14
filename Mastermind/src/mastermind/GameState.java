package mastermind;

/*
 * value object that represents a number of pegs, black or white
 * also represents the state of the game: 4 black pegs = win
 */
public class GameState 
{
	public final int blackPegs;
	public final int whitePegs;
	public Boolean gameOver = false;
	
	public GameState(int blackPegs, int whitePegs)
	{
		this.blackPegs = blackPegs;
		this.whitePegs = whitePegs;
	}
	
	public String toString()
	{
		return "white pegs: " + whitePegs + " black pegs: " + blackPegs;
	}
}
