package jchess.core.moves;

public abstract class MoveBuilder {
	private Move move;
	
	public Move getMove(){
		return this.move;
	}
	
	public void newMove(){
		this.move = new Move();		
	}
	
	public abstract MoveBuilder xFrom(int xF);
	public abstract MoveBuilder yFrom(int yF);
	public abstract MoveBuilder xTo(int xTo);
	public abstract MoveBuilder yTo(int yTo);
}
