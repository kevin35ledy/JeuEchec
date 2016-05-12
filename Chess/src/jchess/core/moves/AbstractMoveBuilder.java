package jchess.core.moves;

import jchess.core.Chessboard;
import jchess.core.moves.move_plus_decorator.AbstractMove;

public abstract class AbstractMoveBuilder {
	

	protected AbstractMove move;
	protected Chessboard board;
	
	
	protected boolean areAllPosSet(){
		return (move.getFrom().getPozX() != -1 
				&& move.getFrom().getPozY() != -1 
				&& move.getTo().getPozX() != -1 
				&& move.getTo().getPozY() != -1);
	}
	
	public void createMove(){
		this.move = new TestMove(board);
	}
	
	public AbstractMove getMove(){
		return this.move;
	}
	
	
	protected void move(){
		int xFrom = this.move.getFrom().getPozX();
		int yFrom = this.move.getFrom().getPozY();
		int xTo = this.move.getTo().getPozX();
		int yTo = this.move.getTo().getPozY();
		
		board.move(board.getSquare(xFrom, yFrom),board.getSquare(xTo, yTo) , true, true);
		this.move.getFrom().setPozX(-1);
		this.move.getFrom().setPozY(-1);
		this.move.getTo().setPozX(-1);
		this.move.getTo().setPozY(-1);
	}
	
	
	
	public abstract AbstractMoveBuilder xFrom(int xF);
	public abstract AbstractMoveBuilder yFrom(int yF);
	public abstract AbstractMoveBuilder xTo(int xTo);
	public abstract AbstractMoveBuilder yTo(int yTo);
	
	public abstract AbstractMoveBuilder from(String s);
	public abstract AbstractMoveBuilder to(String s);
	
}
