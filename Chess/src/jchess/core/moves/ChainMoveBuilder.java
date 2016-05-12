package jchess.core.moves;

import jchess.core.Chessboard;

public class ChainMoveBuilder extends AbstractMoveBuilder {

	
	public ChainMoveBuilder(Chessboard cb){
		this.board = cb;
	}
	
	public ChainMoveBuilder xFrom(int xF) {
		move.setXFrom(xF);
		if (areAllPosSet()){
			move();
		}
		return this;
	}

	
	public ChainMoveBuilder yFrom(int yF) {
		move.setXFrom(yF);	
		if (areAllPosSet()){
			move();
		}
		return this;
	}

	
	public ChainMoveBuilder xTo(int xTo) {
		move.setXFrom(xTo);	
		if (areAllPosSet()){
			move();
		}
		return this;
	}

	
	public ChainMoveBuilder yTo(int yTo) {
		move.setXFrom(yTo);	
		if (areAllPosSet()){
			move();
		}
		return this;	
	}
	

}
