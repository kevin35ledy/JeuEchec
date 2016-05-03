package jchess.core.moves;

import jchess.core.Chessboard;

public abstract class MoveBuilder {
	protected NewMove move;
	protected Chessboard board;
	
	public NewMove getMove(){
		return this.move;
	}
	
	public void createNewMove(){
		this.move = new NewMove(board);		
	}
	
	public abstract MoveBuilder xFrom(int xF);
	public abstract MoveBuilder yFrom(int yF);
	public abstract MoveBuilder xTo(int xTo);
	public abstract MoveBuilder yTo(int yTo);
	
	
	
}
