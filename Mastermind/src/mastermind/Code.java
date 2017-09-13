package mastermind;

import java.util.ArrayList;
import java.util.Random;

/*
 * represents the state of a row on the game board:
*/
public class Code 
{
	ArrayList<Integer> sequence = new ArrayList<>();
	
	public Code()
	{
		sequence = new ArrayList<>();
	}
	public void generate()
	{
		Random rand = new Random();
		for (int i = 0; i < 4;i++)
		{
			sequence.add(rand.nextInt(5)+1); // Comparison method uses * by -1. Colors must be 1 indexed
		}
		
	}
	
	/*
	 * compare this row with another row, return the number of black/white pegs
	 * black pegs indicate exact matches (color and location)
	 * white pegs indicate correct color, but wrong location
	 */
	public GameState compare(Code candidate)
	{
		// TODO - clarify variable names. confusing what is being compared to what
		Integer[] thisSequence = sequence.toArray(new Integer[sequence.size()]);
		Integer[] candidateSequence = candidate.sequence.toArray(new Integer[candidate.sequence.size()]);
		int blackPegs = 0;
		int whitePegs = 0;
		
		// check for black pegs first
        for (int i = 0; i < 4; i++)                    
        {		
        	if (thisSequence[i] == candidateSequence[i])
        	{
        		blackPegs += 1;
        		candidateSequence[i] *= -1;
        		thisSequence[i] *= -1;
        	}
        }
        
        // white pins
        for (int p : thisSequence)
        {
        	if (p < 0)
        		{continue;} // this pin has already been counted 

        	int result = contains(candidateSequence,p);
        	if (result > -1)
        	{
        		whitePegs += 1;
        		candidateSequence[result] *= -1;
        		thisSequence[sequence.indexOf(p)] *= -1;
        	}        	
        }
        //System.out.println("modified test " + Arrays.toString(testSequence));
        //System.out.println("modified candidate " + Arrays.toString(candidateSequence));
        return new GameState(blackPegs,whitePegs);
	}
	
	/*
	 * checks to see if an array contains the given number and return first index location
	 */
	private int contains(Integer[] candidate, int checkFor)
	{
		int indexCounter = 0;
		for (int i : candidate)
		{
			if (i == checkFor)
			{ return indexCounter; } 
			indexCounter++;
		}
		return -1;
	}
}
