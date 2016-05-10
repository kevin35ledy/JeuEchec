package jchess.core.moves.move_plus_decorator;

import org.apache.log4j.Logger;

import jchess.core.Chessboard;
import jchess.core.Square;
import jchess.core.moves.Moves;

public abstract class AbstractMove {

	protected Square from = null;
	protected Square to = null;
	protected String time;
	protected String comment;
	protected Chessboard _chess;

	private static final Logger LOG = Logger.getLogger(Moves.class);

	protected AbstractMove (Square from, Square to, String duree, String com, Chessboard chess){
		this.from = from;
		this.to = to;

		this.time = duree;
		this.comment = com;

		this._chess = chess;
	}

	public Square getFrom()
	{
		return this.from;
	}

	public Square getTo()
	{
		return this.to;
	}

	protected abstract void commentMove(String com);
	protected abstract void setTime(String time);
	public String getComment(){
		return this.comment;
	}
	public String getTime(){
		return this.time;
	}
	
	public abstract void setXFrom(int xF);
	public abstract void setYFrom(int yF);
	
	public abstract void setXTo(int xTo);
	public abstract void setYTo(int yTo);
	
	
	public void move(){
		if (this._chess.getSquare(from.getPozX(), from.getPozY()).getPiece() == null){
			LOG.error("No piece on Square from : " + from.getPozX() + ", " +from.getPozY());
		}
		if (! this._chess.getSquare(from.getPozX(), from.getPozY()).getPiece().canMove(to.getPozX(), to.getPozY())){
			LOG.error("No piece on Square from : " + from.getPozX() + ", " +from.getPozY());
		}
	}
	
}
