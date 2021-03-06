package jchess.core.AI;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import jchess.core.Chessboard;
import jchess.core.Colors;
import jchess.core.Square;
import jchess.core.pieces.Piece;

public class GloutonAI extends AIStrat {

	@Override
	public void move(Chessboard chess, Colors color) {

		List<Piece> pieces = getComputerPieces(chess, color);
		Square begin = null, end = null;
		int bestValue = -1000;

		List<Pair<Square, Square>> bestMoves = new ArrayList<Pair<Square, Square>>();
		// Pour toutes les pi�ces de l'ordinateur
		for (Piece p : pieces) {
			begin = p.getSquare();
			// Pour tous les mouvements de chaque pi�ce
			for (Square sq : p.getAllMoves()) {
				// s'il y a une pi�ce adverse sur la case
				if (sq.getPiece() != null && sq.getPiece().getPlayer().getColor() != color) {
					if (sq.getPiece().getValue() == bestValue)
						bestMoves.add(new Pair<Square, Square>(begin, sq));
					else if (sq.getPiece().getValue() > bestValue) {
						bestValue = sq.getPiece().getValue();
						bestMoves.clear();
						bestMoves.add(new Pair<Square, Square>(begin, sq));
					}
				}
			}
		}
		// dans le cas o� des pi�ces sont atteignables, on choisit parmi les
		// meilleurs moves
		if (bestMoves.size() > 0) {
			Random r = new Random();
			int index = r.nextInt(bestMoves.size());
			begin = bestMoves.get(index).getFirst();
			end = bestMoves.get(index).getSecond();
			chess.move(begin, end);
		}
		// sinon on fait un move random
		else
			super.randomMove(chess, color);
	}

}