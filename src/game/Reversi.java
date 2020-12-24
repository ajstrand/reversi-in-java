package game;
import java.util.*;


/**
 * @author Alex James
 *
 */
public class Reversi {
 static Scanner console = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		String wPName, bPName;
		ReversiBoard reversiBoard;
		TextDisplay display = null;
		Player whitePlayer, blackPlayer;
		char loadGame;
		char instructY_N;
		String saveFileName;
		char graphics_text;
		int moveResult;
	
		

	
		System.out.println("Instructions");
		instructY_N = console.next().toLowerCase().charAt(0);
		if(instructY_N != 'n'){
	
			System.out.println("placeholder text");
		}
		//ask if the user wants to load a game
		System.out.println("load a game?");
		loadGame = console.next().toLowerCase().charAt(0);
		if(loadGame != 'n'){
			System.out.print("Enter file name of saved game? ");
        	saveFileName = console.next();
        	reversiBoard = new ReversiBoard(saveFileName);
		}
		//start a new game
		else{
			System.out.println("white player name?");
			wPName = console.next();
			 whitePlayer = new Player(wPName, new Piece(Piece.WHITEPIECE));
			System.out.println("black player name?");
			bPName = console.next();
            blackPlayer = new Player(bPName, new Piece(Piece.BLACKPIECE));
            reversiBoard = new ReversiBoard(whitePlayer, blackPlayer);
		}
		System.out.println("Graphics or Text?");
		graphics_text = console.next().toLowerCase().charAt(0);
		switch(graphics_text){
		case 'g':
			System.out.println("this does nothing");
			throw new RuntimeException("no graphics implemented yet");
		case 't':
			display = new TextDisplay(reversiBoard);
            // play game
            display.displayMessage(reversiBoard.getCurrentPlayer() + "\nYour turn...");
            display.displayBoard();
            

while (!reversiBoard.gameOver()) {
               moveResult = reversiBoard.handleTurn(display);
               if (moveResult == Board.ENDGAME) {
                  break;
               } else if (moveResult == Board.LEGALMOVE) {
                  display.displayBoard();
               }
            }   // end while play game
         } 

         // Game over? or quit
         if (reversiBoard.gameOver()) {
            display.displayMessage("And the winner is ... " + reversiBoard.getWinner());
         }
		}
	

}

