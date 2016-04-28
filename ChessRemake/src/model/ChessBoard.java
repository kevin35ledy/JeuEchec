package model;



public class ChessBoard {

	//private static final Logger LOG = Logger.getLogger(ChessBoard.class);
    
    protected static final int TOP = 0;
    
    protected static final int BOTTOM = 7;
    
    protected Square squares[][];
    
    
    protected Square activeSquare;
    
    
    public ChessBoard(int nbLigne, int nbColonne){
    	this.squares = new Square[nbLigne][nbColonne];
    }
}
