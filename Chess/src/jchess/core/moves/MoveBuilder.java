package jchess.core.moves;

import jchess.core.Chessboard;

public abstract class MoveBuilder {
	

	protected int xFrom;
	protected int yFrom;
	protected int xTo;
	protected int yTo;
	protected NewMove move;
	protected Chessboard board;
	
//	public NewMove getMove(){
//		return this.move;
//	}
//	
//	public void createNewMove(){
//		this.move = new NewMove(board);		
//	}
	
	public void move(){
		board.move(board.getSquare(xFrom, yFrom),board.getSquare(xTo, yTo) , true, true);
	}
	
	
	
}
