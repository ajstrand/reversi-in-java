
package game;
//this class handles moving for a particular player at specific row and col 
public class Move
{
	private final int row;
	private final int column;
	private final Player player;

	

	public Move( Player in_currentPlayer, int in_row, int in_col)
	{
		row = in_row;
		column = in_col;
		player = in_currentPlayer;
	}


	public boolean equals(Move otherMove)
	{
		 return getPlayer().equals(otherMove.getPlayer()) &&
               getRow() == otherMove.getRow() &&
               getColumn() == otherMove.getColumn();
}

	public int getColumn()// this method returns the column of the current move
	{
		return column;
	}

	public Player getPlayer()// this method returns the player associated with the current move

	{
		return player;
	}

	public int getRow()// this method returns the row of the current move

	{
		return row;
	}

	public String toString()
	{
		 return "" + getPlayer() + " [" + getRow() + ", " + getColumn() + "]";

	}
	
	public String toStringFormatted()
	{
		 return "Move for: " + getPlayer().toStringFormatted() + 
               " row: " + getRow() + " column: " + getColumn();

	}
}
