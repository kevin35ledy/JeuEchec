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
	public void move(Chessboard chessboard, Colors color) {
		// System.out.println("RANDOM AI");
		List<Piece> pieces = getComputerPieces(chessboard, color);
		Random random = new Random();
		if(pieces.size() <0){
			System.out.println(pieces.size());
		}
		int index = random.nextInt(pieces.size());
		Piece p = pieces.get(index);
		Set<Square> movesSet = p.getAllMoves();
		List<Square> moves = new ArrayList<Square>(movesSet);
		int index2 = random.nextInt(moves.size());
		Square end = moves.get(index2);
		chessboard.move(p.getSquare(), end);
	}

	
	

}
