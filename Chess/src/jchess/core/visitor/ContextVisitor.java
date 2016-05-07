package jchess.core.visitor;

import jchess.core.Chessboard;
import jchess.core.Square;

public class ContextVisitor {
	private Visitor _vis;

	public ContextVisitor(Visitor vis) {
		this._vis = vis;
	}

	public void visit(Chessboard chess){
		_vis.visit(chess);
	}

	public void visit(Square sq){
		_vis.visit(sq);
	}
}
