package game;

//this class hold all the attributes of piece and the information about a piece object
public  class Piece
{
	public static final int BLANKPIECE = 0;
	public static final int WHITEPIECE = 1;
	public static final int BLACKPIECE= 2;
	int row, col;
	
	
	   
		private  int pieceType;

	public  Piece(int in_Type)
	{
		
		pieceType = in_Type;
	}

	public boolean equals(Piece otherPiece ) 
	// this method determines weather or not two pieces are equal	
	{
		
		return(getType() == otherPiece.getType());
	}

	public Piece getOpposite()
	{
		Piece opPiece = new Piece(BLANKPIECE);
        switch(getType()){
            case BLACKPIECE:opPiece = new Piece(WHITEPIECE); 
               break;
            case WHITEPIECE: opPiece = new Piece(BLACKPIECE); 
               break;
            case BLANKPIECE:      
               break;
            default: System.err.println("Piece.toString() INVALID type = " + pieceType);
               System.exit(1);
         }
         return opPiece;
	}
    
     public boolean opposite(Piece otherPiece) {
         return equals(otherPiece.getOpposite());
      }

	public int getType()
	{
		return pieceType;
	}


	public char toChar()
	{
		char c = ' ';
         switch (getType()) {
            case BLACKPIECE: c = 'B'; 
               break;
            case WHITEPIECE: c = 'W'; 
               break;
            case BLANKPIECE:      
               break;
            default: System.err.println("Piece.toChar() INVALID type = " + pieceType);
               System.exit(1);
         }
         return c;
		
	}

	public String toString()
	{
		String sType = "";
         switch (getType()) {
            case BLACKPIECE: sType = "BLACKPIECE"; 
               break;
            case BLANKPIECE:      sType = "BLANK"; 
               break;
            case WHITEPIECE: sType = "WHITEPIECE"; 
               break;
            default: System.err.println("Piece.toString() INVALID type = " + pieceType);
               System.exit(1);
         }
         return sType;
      }
	}
