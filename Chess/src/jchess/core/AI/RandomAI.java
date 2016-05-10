package jchess.core.AI;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.Iterator;

import jchess.core.Chessboard;
import jchess.core.Colors;
import jchess.core.Square;
import jchess.core.moves.move_plus_decorator.Move;
import jchess.core.pieces.Piece;

public class RandomAI extends AIStrat {

	@Override
	public void move(Chessboard chess, Colors color) {
		super.randomMove(chess, color);
		
	}

	
	

}
