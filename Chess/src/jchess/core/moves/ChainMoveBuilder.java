package jchess.core.moves;

public class ChainMoveBuilder extends MoveBuilder {

	@Override
	public MoveBuilder xFrom(int xF) {
		move.setXFrom(xF);	
		return this;
	}

	@Override
	public MoveBuilder yFrom(int yF) {
		move.setXFrom(yF);	
		return this;
	}

	@Override
	public MoveBuilder xTo(int xTo) {
		move.setXFrom(xTo);	
		return this;
	}

	@Override
	public MoveBuilder yTo(int yTo) {
		move.setXFrom(yTo);	
		return this;	
	}

}
