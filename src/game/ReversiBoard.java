package game;
import java.io.*;

import java.util.*;

import game.Piece;
public class ReversiBoard extends Board 
{
	protected static final int[] DX = { -1, 0, 1, -1, 1, -1, 0, 1 };
    protected static final int[] DY = { -1, -1, -1, 0, 0, 1, 1, 1 };
    //might not use this
    /*public enum Direction {
	    UP_LEFT, UP, UP_RIGHT, RIGHT, BOTTOM_RIGHT, BOTTOM, BOTTOM_LEFT, LEFT;
   }*/
   Player whitePlayer;
   Player blackPlayer;
   Player curPlayer;

public ReversiBoard(int inSize, Player inWhitePlayer, Player inBlackPlayer) 
   {
      super(inSize);
      whitePlayer = inWhitePlayer;
      blackPlayer = inBlackPlayer;
   		
      Piece[][] boardArray = new Piece[inSize][inSize];
   	
      int x = (inSize - 1) /2;
   	
   		
   		
      super.setPiece(whitePlayer.getPiece(), x , x);
      super.setPiece(blackPlayer.getPiece(), x , x + 1);
      super.setPiece(blackPlayer.getPiece(), x + 1, x);
      super.setPiece(whitePlayer.getPiece(), x + 1, x + 1);
   
   }
   public ReversiBoard(Player inWhitePlayer, Player inBlackPlayer) 
   {	
   		
      Piece[][] boardArray = new Piece[DEFAULTSIZE][DEFAULTSIZE];
   	
      int x = DEFAULTSIZE / 2;
   	
      for(int row = 0; row < DEFAULTSIZE; row++){
         for(int col = 0; col < DEFAULTSIZE; col++){
            setPiece(new Piece(Piece.BLANKPIECE), row, col);
            boardArray[row][col] = new Piece(Piece.BLANKPIECE);
         }
      }
   
       whitePlayer = inWhitePlayer;
       blackPlayer = inBlackPlayer;
   
   		
       curPlayer = inBlackPlayer;
   		
      setPiece(inWhitePlayer.getPiece(), x - 1, x -1);
      setPiece(inBlackPlayer.getPiece(), x, x -1 );
      setPiece(inWhitePlayer.getPiece(), x, x);
      setPiece(inBlackPlayer.getPiece(), x-1, x);
   }
        //this next method is for loading in a saved game file
   public ReversiBoard(String fileName)
   {
      int num;
      int size;
   		
      try
      {
         Scanner inFile = new Scanner(new FileReader(fileName));
      	
      	
         blackPlayer = new Player(inFile.next(), new Piece(Piece.BLACKPIECE));
      	
         whitePlayer = new Player(inFile.next(), new Piece(Piece.WHITEPIECE));
      	
         curPlayer = new Player(inFile.next(), new Piece(Piece.BLANKPIECE));
      	
         if(blackPlayer.getName() == inFile.next())
            setCurrentPlayer(blackPlayer);
         else
            setCurrentPlayer(whitePlayer);
      	
         size = inFile.nextInt();
      	
         loadBoard(inFile, size);
      		
      }
      catch(Exception e)
      {
         System.err.print("File Not Found");
      }
   		
   }
		
   public Player getWinner()
   {
      Player blackPlayer, whitePlayer;
      int blackCount, whiteCount;
   		
      blackPlayer = getBlackPlayer();
      whitePlayer = getWhitePlayer();
   		
      blackCount = countPieces(blackPlayer);
      whiteCount = countPieces(whitePlayer);
   		
      if(blackCount == whiteCount) {
         return null;
      } 
      else if (blackCount > whiteCount) {
         return blackPlayer;
      } 
      else {
         return whitePlayer;
      }
   }
   public int handleTurn(Display display)
   {
	   
      int status;
      Move move;
      curPlayer = getCurrentPlayer();
            
      display.displayStatus(curPlayer, ", your turn.\n");
      move = display.getMove(curPlayer);
      if (moveLegal(move)) {
         status = Board.LEGALMOVE;
         makeMove(move);
         setCurrentPlayer(getNextPlayer());
      } 
      else { // not a legal move
         status = BADMOVE;
      }
      return status;
   }
   public boolean moveLegal(Move move){
	   boolean valid = true;
	   for (int row = -1; row <= -1 && valid; ++row) {
	     for (int col = -1; col <= -1 && valid; ++col) {
	    	 int x=1;
      switch(x){
         // This switch statement checks the validity of a move
         //in 8 directions around the piece 
            case 1:
            	///down
               if(checkDir(move, + 1, 0))
                  return true;
               	
            case 2:
            	//down-right
               if(checkDir(move, + 1, + 1))
                  return true;
               	
            case 3:
            	//right
               if(checkDir(move, 0, + 1))
                  return true;
               
            case 4:
            	//up-right
               if(checkDir(move, - 1, + 1))
                  return true;
               
            case 5:
            	//up
               if(checkDir(move, - 1, 0))
                  return true;
               	
            case 6:
            	//up-left
               if(checkDir(move, - 1, -1))
                  return true;
               
            case 7:
            	//left
               if(checkDir(move, 0, - 1))
                  return true;
               
            case 8:
            	//down-left
               if(checkDir(move, + 1, - 1))
                  return true;
               
         }
      
	     }    
 
	   }
	return valid;
   }
		/** This method checks 8 directions around the place where you want to move
		(up. down, left, right and diagonals) and also checks if there are opposite colored pieces 
		leading away in any direction from where you place your piece
		* @param move move row col to check
		* @return true if there pieces of the opposite color leading away from your piece
		*/
   public boolean checkDir(Move move ,int row, int col){

       int moveRow= move.getRow();
       int moveCol=move.getColumn();
       Piece movePiece = new Piece(curPlayer.getPiece().getType());
      // checks if move is on the board	or if spot we want to move to is blank
      boolean notOnBoard = !onBoard(moveRow, moveCol);
      boolean isNotBlank = !(getPiece(moveRow, moveCol).getType() == Piece.BLANKPIECE);
      if(notOnBoard || isNotBlank){
    		 System.out.println("sorry you cant move here");
    		 return false;
      }
    	  moveRow+= row;
    	  moveCol += col;
              // stop when we end up off the board
            
              while(onBoard(moveRow, moveCol)) {
            		 while(getPiece(moveRow, moveCol).equals(movePiece.getOpposite())){
            			moveRow+= row;
                        moveCol+=col;
                        if(getPiece(moveRow, moveCol).getType() == 
                      		  Piece.BLANKPIECE){
                        	return false;
                        }
                        if(getPiece(moveRow, moveCol).equals(movePiece))
                    	  return true;
                        
   } 
     
       // return statement for checking if equal to the other player
      return false;
              }
              //return for checking if on board
			return false;
   }
 

   // end of my checkDir method
   public Player getBlackPlayer()
   {
      return blackPlayer;
   }
   public Player getWhitePlayer()
   {
      return whitePlayer;
   }
   public void setCurrentPlayer(Player player)
   {
     curPlayer = player;
   }

   public Player getCurrentPlayer()
   {
      return curPlayer;
   }
   public Player getNextPlayer()
   {	Player nextPlayer;
   		
      if(curPlayer == whitePlayer)
         nextPlayer = blackPlayer;
      else
         nextPlayer = whitePlayer;
      return nextPlayer;
   }
   public void saveGame(String fileName)
   {	
      StringBuilder str = new StringBuilder();
   		
      PrintWriter outFile; //= new PrinterWriter(outFile);
   		
      for(int row = 0; row < str.length() - 1; row++){
         for(int col = 0; col < str.length() -1; col++){
            if(getPiece(row, col).equals(new Piece(Piece.BLACKPIECE)))
               str.append(" 2");
            if(getPiece(row, col).equals(new Piece(Piece.WHITEPIECE)))
               str.append(" 1");
            else 
               str.append(" ");
         }
         str.append("\n");
      }
   	  
      str.insert(0, getBlackPlayer().getName() + "\n" + getWhitePlayer().getName() + "\n" +
              getCurrentPlayer() + "\n" +
              getSize() + "\n");
   	  
      try
      {
         outFile = new PrintWriter(fileName);
      		
         outFile.println(str);
      		
         outFile.close();
      }
      catch(Exception e)
      {
         System.err.println("Not a valid file name");
      }
   }
   public boolean moveExists(Player player)
   {
      for(int row = 0; row < super.getSize(); row++){
         for(int col = 0; col < super.getSize(); col++){
            if(moveLegal(new Move(player, row, col)))
               return true;
         }
      }
      return false;
   				
   }
   public boolean gameOver(){
      if(moveExists(blackPlayer) || moveExists(whitePlayer))
         return false;
      else
         return true;
   }
   public void makeMove(Move move){//removing extraneous comments
      int row = move.getRow();
      int col = move.getColumn();
      Player movePlayer = move.getPlayer();
      Piece movePiece = movePlayer.getPiece();
      
   		
      for (int moveRow = -1; moveRow <= 1; moveRow++) {
         for (int moveCol = -1; moveCol <= 1; moveCol++) {
            if (!(moveRow==0 && moveCol==0)) {	// don't check your own space*/
               if (checkDir(move, moveRow, moveCol)) {
                  while(super.getPiece(row, col) != movePiece &&
                		  super.getPiece(row, col) != new Piece(Piece.BLANKPIECE)){
                     row += moveRow;
                     col += moveCol;   
                     if(!(super.getPiece(row, col).equals(movePiece)) &&
                    	 	!(super.getPiece(row,  col).equals(new Piece(Piece.BLANKPIECE)))){
                    	 setPiece(move.getPlayer().getPiece(), row, col);
                 System.out.println("You made a move!");
                   }
                    	}
                  }
               }
            
         }
      
      } setPiece(move.getPlayer().getPiece(), move.getRow(), move.getColumn());   
   } 
}// end of reversiBoard class
    
