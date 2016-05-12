package jchess.core.moves;

import jchess.core.Chessboard;
import jchess.core.moves.move_plus_decorator.AbstractMove;

public abstract class AbstractMoveBuilder {
	

	protected int xFrom;
	protected int yFrom;
	protected int xTo;
	protected int yTo;
	protected AbstractMove move;
	protected Chessboard board;
	
	protected AbstractMoveBuilder(){
		xFrom = -1;
		yFrom = -1;
		xTo = -1;
		yTo = -1;
	}
	
	protected boolean areAllPosSet(){
		return (xFrom != -1 && yFrom != -1 && xTo != -1 && yTo != -1);
	}
	
	public void createMove(){
		this.move = new TestMove(board);
	}
	
	public AbstractMove getMove(){
		return this.move;
	}
	
	
	protected void move(){
		board.move(board.getSquare(xFrom, yFrom),board.getSquare(xTo, yTo) , true, true);
		xFrom = -1;
		yFrom = -1;
		xTo = -1;
		yTo = -1;
	}
	
	
	
	public abstract AbstractMoveBuilder xFrom(int xF);

	
	public abstract AbstractMoveBuilder yFrom(int yF);

	
	public abstract AbstractMoveBuilder xTo(int xTo);

	
	public abstract AbstractMoveBuilder yTo(int yTo);
	
	
}
