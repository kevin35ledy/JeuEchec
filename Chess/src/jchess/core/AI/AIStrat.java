package jchess.core.AI;

import java.util.ArrayList;
import java.util.List;

import jchess.core.Chessboard;
import jchess.core.Colors;
import jchess.core.Square;
import jchess.core.pieces.Piece;

public abstract class AIStrat {
	
	abstract public void move(Chessboard chess, Colors color);

	// On récupère toutes les pièces (qui ont le droit de bouger) de l'ordinateur
	protected List<Piece> getComputerPieces(Chessboard chess, Colors color) {
		List<Piece> res = new ArrayList<Piece>();
		Square[][] s = chess.getSquares();
		for (int i = 0; i < chess.getSettings().getSize(); i++) {
			for (int j = 0; j < chess.getSettings().getSize(); j++) {
				if (s[i][j].getPiece() != null) {
					Piece p = s[i][j].getPiece();
					if (p.getPlayer().getColor() == color && p.getAllMoves().size() != 0) {
						res.add(p);
					}
				}
			}
		}
		return res;
	}
}
