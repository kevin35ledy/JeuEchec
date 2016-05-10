package jchess.core.moves;

import org.apache.log4j.Logger;

import jchess.core.Chessboard;
import jchess.core.Square;
import jchess.core.moves.move_plus_decorator.AbstractMove;


/** Class representing the players moves, it's also checking
 * that the moves taken by player are correct.
 * All moves which was taken by current player are saving as List of Strings
 * The history of moves is printing in a table
 * @param game The current game
 */
public class NewMove extends AbstractMove{
	private Square sFrom;
	private Square sTo;
	protected Chessboard board;
	
	
	private static final Logger LOG = Logger.getLogger(Moves.class);
    
    
    
	public NewMove(Chessboard cboard){
		super(null, null, "", "", cboard);
		this.board = cboard;
	}
	
	public void setXFrom(int xF){sFrom.setPozX(xF);}
	public void setYFrom(int yF){sFrom.setPozY(yF);}
	
	public void setXTo(int xTo){sTo.setPozX(xTo);}
	public void setYTo(int yTo){sTo.setPozY(yTo);}
	
	
	public void move(){
		if (this.board.getSquare(sFrom.getPozX(), sFrom.getPozY()).getPiece() == null){
			LOG.error("No piece on Square from : " + sFrom.getPozX() + ", " +sFrom.getPozY());
		}
		if (! this.board.getSquare(sFrom.getPozX(), sFrom.getPozY()).getPiece().canMove(sTo.getPozX(), sTo.getPozY())){
			LOG.error("No piece on Square from : " + sFrom.getPozX() + ", " +sFrom.getPozY());
		}
	}

	@Override
	protected void commentMove(String com) {
		// TODO Auto-generated method stub
		this.comment = com;
	}

	@Override
	protected void setTime(String time) {
		// TODO Auto-generated method stub
		this.time = time;
	}
}
