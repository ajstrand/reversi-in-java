package game;
import java.util.*;
import java.io.*;
import java.lang.*;
public class Board
{
	 Piece[][] boardArray = new Piece[8][8];
	static Scanner console = new Scanner(System.in);
	final static int BADMOVE=2;
	static int DEFAULTSIZE = 8;
	final static int ENDGAME=0;
	final static int LEGALMOVE=1;
	int localSize = DEFAULTSIZE;
	
	Player whitePlayer;
	Player blackPlayer;
	Player curPlayer;
	Player inWhitePlayer; 
	Player inBlackPlayer;
	
	public Board()
	{
		boardArray = new Piece[DEFAULTSIZE][DEFAULTSIZE];
		
		int x = DEFAULTSIZE / 2;
		
		for(int row = 0; row < DEFAULTSIZE; row++){
			for(int col = 0; col < DEFAULTSIZE; col++){
				setPiece(new Piece(Piece.BLANKPIECE), row, col);
				boardArray[row][col] = new Piece(Piece.BLANKPIECE);
			}
	  }
	  setPiece(new Piece(Piece.WHITEPIECE), x , x);
	  setPiece(new Piece(Piece.BLACKPIECE), x , x + 1);
	  setPiece(new Piece(Piece.WHITEPIECE), x + 1, x);
	  setPiece(new Piece(Piece.BLACKPIECE), x + 1, x + 1);

	}
	public Board(int inSize)
	{
		
		boardArray = new Piece[inSize][inSize];
		
		int x = (inSize - 1) /2;
		
		for(int row = 0; row < inSize; row++){
			for(int col = 0; col < inSize; col++){
				setPiece(new Piece(Piece.BLANKPIECE), row, col);
			}
	   }

		whitePlayer = inWhitePlayer;
		blackPlayer = inBlackPlayer;
	 	curPlayer = blackPlayer;

	  setPiece(new Piece(Piece.WHITEPIECE), x , x);
	  setPiece(new Piece(Piece.BLACKPIECE), x , x + 1);
	  setPiece(new Piece(Piece.WHITEPIECE), x + 1, x);
	  setPiece(new Piece(Piece.BLACKPIECE), x + 1, x + 1);


	}
	public int countPieces(Player player)
	{
		int row, col, size;
		int count = 0;
		Piece playerPiece = player.getPiece();
			
		size = getSize();
		for (row = 0; row < size; row++) {
				for (col = 0; col < size; col++) {
					if (playerPiece.equals(getPiece(row, col))) {
						count++;
					}
			   }
		}
			return count;
	}
		// loop through every position on boardArray like when creating boardArray, keep incrementing x any if less than boardArray siize of both boardArrays
		// see if otherBoards piece equals boardArray's Piece
		// use getPiece
	public boolean equals(Board otherBoard)
	{
		
		for(int row = 0; row < getSize(); row++){
			for (int col = 0; col <getSize(); col++){
				if(!(otherBoard.getPiece(row, col).equals(boardArray[row][col])))
					return true;
				}
		   }return false;
	 }
	
	public Piece getPiece(int row, int col)
	{
		if(onBoard(row, col)){
			return boardArray[row][col];
		}
		return null;
	}
	public int getSize()
	{
		return boardArray[0].length;
	}
	public void loadBoard(Scanner inFile, int inSize)
	{
		int next = 0;
		for(int row = 0; row < getSize(); row++){
			for(int col = 0; col < getSize(); col++){
					next = inFile.nextInt();
					
					switch(next){
					case 2:
					boardArray[row][col] = new Piece(Piece.BLACKPIECE);	
					break;
					case 1:
						boardArray[row][col] = new Piece(Piece.WHITEPIECE);
						break;
					default:
						boardArray[row][col] = new Piece(Piece.BLANKPIECE);
					}
						/*boardArray[row][col] = new Piece(Piece.BLACKPIECE);	
					else if(next == 1)
						boardArray[row][col] = new Piece(Piece.WHITEPIECE);
					else 
						boardArray[row][col] = new Piece(Piece.BLANKPIECE);*/
			}
			 
		}


				
	}
	public void setPiece(Piece inPiece, int row, int col)
	{
		if(onBoard(row, col))
			boardArray[row][col] = inPiece;
	}
	public void savePieces(PrintWriter outFile)
	{
		for(int row = 0; row < getSize(); row++){
			for (int col = 0; col < getSize(); col++){
				if(boardArray[row][col].equals(new Piece(Piece.BLACKPIECE)))
					outFile.print(Piece.BLACKPIECE);		
					if(boardArray[row][col].equals(new Piece(Piece.WHITEPIECE)))
						outFile.print(Piece.WHITEPIECE);
					else 
						outFile.print(Piece.BLANKPIECE);
			  }
			  outFile.println();
		}

	}
	public boolean onBoard(int row, int col)
	{
		if(row < 0 || col < 0){
			//System.out.println(boardArray[0].length);
			//System.out.println("too small");
			return false;
		}
		else if(row > this.getSize() || col > this.getSize()){
			//System.out.println("too big");
			return false;
	}else{
            //System.out.println("you made it");
			return true;
	}
	}
	/*private void firstPiecesOnBoard()
	{
		//Piece BLANKPIECEPIECE;
		int x = localSize/ 2;
		
		for(int row = 0; row < localSize; row++){
			for(int col = 0; col < localSize; col++){
				setPiece(new Piece(Piece.BLANKPIECE), row, col);
			}
	  }
	  setPiece(new Piece(Piece.WHITEPIECE), x , x);
	  setPiece(new Piece(Piece.BLACKPIECE), x , x + 1);
	  setPiece(new Piece(Piece.WHITEPIECE), x + 1, x);
	  setPiece(new Piece(Piece.BLACKPIECE), x + 1, x + 1);
	}*/
}