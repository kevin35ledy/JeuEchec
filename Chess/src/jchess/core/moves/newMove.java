package jchess.core.moves;

import jchess.core.Square;

public class newMove {
	private Square sFrom;
	private Square sTo;
	
	public void setXFrom(int xF){sFrom.setPozX(xF);}
	public void setYFrom(int yF){sFrom.setPozY(yF);}
	
	public void setXTo(int xTo){sTo.setPozX(xTo);}
	public void setYTo(int yTo){sTo.setPozY(yTo);}
}
