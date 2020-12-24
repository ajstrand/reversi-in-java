package game;
//this class contains all the code for a Player object and its attributes
public class Player
{
	private String playerName;
	private Piece pieceType;
	
	

	public Player(String inPlayerName, Piece inPiece)
	{
		playerName = inPlayerName;
		pieceType = inPiece;
		
	}

	public boolean equals(Player otherPlayer)// need to come back and re do
	{
		return getName().equals(otherPlayer.getName()) &&
               getPiece().equals(otherPlayer.getPiece());
    }

   public String getName()// this method returns the name of the player
	{
		return playerName;
	}

	public  Piece getPiece()// need to finish
	{
		return pieceType;
	}

	public  String toString()// this method returns a string representing the player
	{
		 return (getName() + ": " + getPiece());
	}

	public String toStringFormatted()// this method returns a formatted string representing the player
	{
		 return ("Player name:" + getName() + ": Piece: " + getPiece());

	}
}