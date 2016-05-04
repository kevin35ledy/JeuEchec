package jchess.core.moves;

import jchess.core.Chessboard;

public abstract class MoveBuilder {
	

	protected int xFrom;
	protected int yFrom;
	protected int xTo;
	protected int yTo;
	protected NewMove move;
	protected Chessboard board;
	
	protected MoveBuilder(){
		xFrom = -1;
		yFrom = -1;
		xTo = -1;
		yTo = -1;
	}
	
	protected boolean areAllPosSet(){
		return (xFrom != -1 && yFrom != -1 && xTo != -1 && yTo != -1);
	}
	
	public void createMove(){
		this.move = new NewMove(board);
	}
	
	public NewMove getMove(){
		return this.move;
	}
	
	
	protected void move(){
		board.move(board.getSquare(xFrom, yFrom),board.getSquare(xTo, yTo) , true, true);
		xFrom = -1;
		yFrom = -1;
		xTo = -1;
		yTo = -1;
	}
	
	
	
	public abstract MoveBuilder xFrom(int xF);

	
	public abstract MoveBuilder yFrom(int yF);

	
	public abstract MoveBuilder xTo(int xTo);

	
	public abstract MoveBuilder yTo(int yTo);
	
	
}
