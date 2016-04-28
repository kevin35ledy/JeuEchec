package model.pieces;

import java.util.logging.Logger;
import model.*;

public abstract class Piece {
	
	
	//private static final Logger LOG = Logger.getLogger(Piece.class);

	protected ChessBoard chessboard; 
 
	protected Square square;
    
    protected Player player;
    
    protected String name;
    
    protected String symbol;
    
    protected static short value = 0;

    
    public Piece(ChessBoard chessboard, Player player)
    {
        this.chessboard = chessboard;
        this.player = player;
        this.name = this.getClass().getSimpleName();

    }
    
    
    
    
    public ChessBoard getChessboard() {
    	return chessboard;
    }
    
	public Square getSquare() {
		return square;
	}

	public void setSquare(Square square) {
		this.square = square;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public static short getValue() {
		return value;
	}

	public static void setValue(short value) {
		Piece.value = value;
	}

	
	

}
