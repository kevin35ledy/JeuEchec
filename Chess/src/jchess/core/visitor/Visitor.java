package jchess.core.visitor;

import jchess.core.Chessboard;
import jchess.core.Square;

public interface Visitor {
	
	public void visit(Chessboard chess);
	
	public void visit(Square sq);

}
