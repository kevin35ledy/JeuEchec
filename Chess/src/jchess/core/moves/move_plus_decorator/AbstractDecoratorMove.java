package jchess.core.moves.move_plus_decorator;

import jchess.core.Chessboard;
import jchess.core.Square;
import jchess.core.moves.Castling;
import jchess.core.pieces.Piece;

public abstract class AbstractDecoratorMove extends AbstractMove{

	private AbstractMove abMove;
	
	protected AbstractDecoratorMove(Square from, Square to, String duree, String com, AbstractMove moveToDecorate, Chessboard chess) {
		super(from, to, duree, com, chess);
		// TODO Auto-generated constructor stub
		this.abMove = moveToDecorate;
	}
	
	


}
