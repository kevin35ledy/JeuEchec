package jchess.core.moves;

import jchess.core.Chessboard;

public class ChainMoveBuilder extends MoveBuilder {

	
	public ChainMoveBuilder(Chessboard cb){
		this.board = cb;
	}
	
	public ChainMoveBuilder xFrom(int xF) {
		move.setXFrom(xF);	
		return this;
	}

	
	public ChainMoveBuilder yFrom(int yF) {
		move.setXFrom(yF);	
		return this;
	}

	
	public ChainMoveBuilder xTo(int xTo) {
		move.setXFrom(xTo);	
		return this;
	}

	
	public ChainMoveBuilder yTo(int yTo) {
		move.setXFrom(yTo);	
		return this;	
	}
	

}
