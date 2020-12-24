package game;
//This program lets you play the game textually by displaying the board with System.out

import java.util.*;

public class TextDisplay implements Display
{
  
	char input;
	String str;
	
	
  protected static Scanner console = new Scanner(System.in);
  protected ReversiBoard rboard;
  
  public TextDisplay(ReversiBoard rboard){
  		this.rboard = rboard;
  }
  // method to display the board
  public void displayBoard(){
   int row, col, size;
      	
         size = rboard.getSize();
            
            // print column heading line
         System.out.print("    ");
         for (col = 0; col < size; col++) {
            System.out.print("" + col + " ");
         }
         System.out.println();
         printDashes(size);
      
            // loop over each row and col showing correct PieceType            
         for (row = 0; row < size; row++) {
            for (col = 0; col < size; col++) {
               if (col == 0) {
                  System.out.print(" " + row + " |");
               }
               switch (rboard.getPiece(row, col).getType())
               {
                  case Piece.BLANKPIECE:
                     System.out.print(" |");
                     break;
                  case Piece.BLACKPIECE:
                     System.out.print("B|");
                     break;
                  case Piece.WHITEPIECE:
                     System.out.print("W|");
                     break;
                  default:
                     System.out.println("ERROR: TextDisplay.displayBoard() ... invalid Piece type on board");
                     System.exit(0);
               }
            }
            System.out.println();   // end of row
            printDashes(size);
         }

  }
  // method to display a simple message
  public void displayMessage(String str){
  		System.out.println(str);
  }
  // method to display the status of the current player
  public void displayStatus(Player curPlayer, String message){
		System.out.println(curPlayer + message);
  }
  // method to display the winner
  public void displayWinner(Player winner){
	   System.out.println(winner.getName() + "Wins");
	  }
	  // method to get a move
  public Move getMove(Player player){
  		int row = 0 , col = 0;
  				
		System.out.println("What row? Do you want to (s)ave y/n ?");
		row = console.nextInt();
		input = console.next().charAt(0);
      System.out.println("What column? Do you want to (q)uit y/n ?");
		col = console.nextInt();
		input = console.next().charAt(0);
			
		Move move = new Move(player, row, col);

		
		return move;
	}
	// counts the # of pieces of a certain player
 public int countPieces(Player player) {
   int count = 0;
	for(int row = 0; row < rboard.getSize(); row++){
		 for(int col = 0; col < rboard.getSize(); col++){
			 	if (rboard.getPiece(row, col).getType() == player.getPiece().getType())
				 	count++;
	    }
	  }
	return count;
  }
  // method to get the winner
  public Player getWinner() {
  	Player winner, white, black;
	white = rboard.getWhitePlayer();
	black = rboard.getBlackPlayer();
  	
  	if(countPieces(white) == countPieces(black))
	  	winner = null;
	else if(countPieces(black) > countPieces(white))
  		winner = black;
	else
		winner = white;
	return winner;
  		
  	}
 // method to handle a single turn
public int handleTurn(Display display){
	int status;
	Move move;
	List piecesToFlip = new ArrayList<>();
  	displayStatus(rboard.getCurrentPlayer(), "it's your turn");
	move = getMove(rboard.getCurrentPlayer()); 
   if(rboard.moveLegal(move)){
  		status = Board.LEGALMOVE; 
		rboard.makeMove(move);
		rboard.setCurrentPlayer(rboard.getNextPlayer());
	}
   else
		status = Board.BADMOVE;
	   return status;

	}
    
    // print a row of dashes for size columns helps displayBoard()
      private void printDashes(int size) {
         System.out.print("   -");
         for (int i = 0; i < size; i++) {
            System.out.print("--");
         }
         System.out.println();
      }

   
} // end of TextDisplay.class