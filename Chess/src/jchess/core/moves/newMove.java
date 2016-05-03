package jchess.core.moves;

import jchess.core.Chessboard;
import jchess.core.Square;

public class newMove {
	private Square sFrom;
	private Square sTo;
	protected Chessboard board;
	
	public newMove(Chessboard cboard){
		this.board = cboard;
	}
	
	public void setXFrom(int xF){sFrom.setPozX(xF);}
	public void setYFrom(int yF){sFrom.setPozY(yF);}
	
	public void setXTo(int xTo){sTo.setPozX(xTo);}
	public void setYTo(int yTo){sTo.setPozY(yTo);}
	
	
	public void move(){
		
	}
}
