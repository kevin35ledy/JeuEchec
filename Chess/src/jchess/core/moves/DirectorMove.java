package jchess.core.moves;

public class DirectorMove {

	private MoveBuilder moveBuilder;
	
	public void setMoveBuilder (MoveBuilder mb) {
		moveBuilder= mb;
	}
	
	public newMove getMove(){
		return moveBuilder.getMove();
	}
	
	public void constructMove(int xF,int yF, int xTo, int yTo){
		moveBuilder.xFrom(xF).yFrom(yF).xTo(xTo).yTo(yTo);
	}
}
