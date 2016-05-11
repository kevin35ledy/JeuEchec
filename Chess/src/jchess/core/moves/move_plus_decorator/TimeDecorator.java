package jchess.core.moves.move_plus_decorator;

import jchess.core.Chessboard;
import jchess.core.Square;

public class TimeDecorator extends AbstractDecoratorMove{

	protected TimeDecorator(Square from, Square to, String duree, String com, AbstractMove moveToDecorate,
			Chessboard chess) {
		super(from, to, duree, com, moveToDecorate, chess);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void commentMove(String com) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setTime(String time) {
		// TODO Auto-generated method stub
		this.abMove.setTime(time);
	}

	

}
