package jchess.core.AI;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import jchess.core.Chessboard;
import jchess.core.Colors;
import jchess.core.Square;
import jchess.core.pieces.Piece;

public class MinMaxAI extends AIStrat {

	@Override
	public void move(Chessboard chess, Colors color) {
		List<Piece> pieces = getComputerPieces(chess, color);
		Square begin = null, end = null;
		Colors cHuman = switchColor(color);
		int cpt = 0;
		int bestDiff = -1000, max = 0, min = 0, diff = 0;
		List<Pair<Square, Square>> l = new ArrayList<Pair<Square, Square>>();
		// pour chaque piece de l'ordinateur qui peut bouger
		for (Piece p : pieces) {
			cpt++;
			int cptMove = 0;
			// pour tous les mouvements possibles de la piece
			for (Square sq : p.getAllMoves()) {
				cptMove++;
				// s'il y a une piece sur la case
				if (sq.getPiece() != null)
					max = sq.getPiece().getValue();
				else
					max = 0;
				chess.move(p.getSquare(), sq);
				// on cherche le meilleur coup pour le joueur humain
				min = getMaxHuman(chess, cHuman);
				chess.undo();
				diff = max - min;
								
				if (diff == bestDiff) {
					l.add(new Pair<Square, Square>(p.getSquare(), sq));
				} else if (diff > bestDiff) {
					bestDiff = diff;
					l.clear();
					l.add(new Pair<Square, Square>(p.getSquare(), sq));
				}
			}

		}
		// on joue le coup
		Random r = new Random();
		int index = r.nextInt(l.size());
		begin = l.get(index).getFirst();
		end = l.get(index).getSecond();
		chess.move(begin, end);
	}

	public int getMaxHuman(Chessboard chess, Colors color) {
		List<Piece> piecesH = getComputerPieces(chess, color);
		int val = 0;
		for (Piece p : piecesH) {
			for (Square s : p.getAllMoves()) {
				Piece temp = s.getPiece();
				if (temp != null) {
					if (temp.getPlayer().getColor() != color) {
						if (temp.getValue() > val) {
							val = temp.getValue();
						}
					}
				}
			}
		}
		return val;

	}

	public Colors switchColor(Colors c) {
		Colors colorHuman = Colors.BLACK;
		if (c == Colors.BLACK)
			colorHuman = Colors.WHITE;
		return colorHuman;
	}

}
