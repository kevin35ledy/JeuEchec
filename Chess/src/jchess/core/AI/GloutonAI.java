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

		System.out.println("Hey je suis Glouton MAIS JE JOUE !!!!");

		// Retrieving Computer Pieces (that can moves)
		List<Piece> pieces = getComputerPieces(chess, color);
		Square begin = null, end = null;
		int bestValue = -1000;

		List<Pair<Square, Square>> bestMoves = new ArrayList<Pair<Square, Square>>();
		// Pour toutes les pièces de l'ordinateur
		for (Piece p : pieces) {
			begin = p.getSquare();
			// Pour tous les mouvements de chaque pièce
			for (Square sq : p.getAllMoves()) {
				// s'il y a une pièce adverse sur la case
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
		if (bestMoves.size() > 0) {
			Random r = new Random();
			int index = r.nextInt(bestMoves.size());
			begin = bestMoves.get(index).getFirst();
			end = bestMoves.get(index).getSecond();
			chess.move(begin, end);
		} else
			super.randomMove(chess, color);
	}

}