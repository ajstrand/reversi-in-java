package game;

public interface Display 
{
	public abstract void displayMessage(String message);
	
	public abstract void displayStatus(Player currentPlayer, String message);	
	
	public abstract void displayWinner(Player winner);
	
	public abstract Move getMove(Player player);
}