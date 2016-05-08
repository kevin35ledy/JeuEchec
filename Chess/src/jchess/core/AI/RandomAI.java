package jchess.core.AI;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.Iterator;

import jchess.core.Chessboard;
import jchess.core.Colors;
import jchess.core.Square;
import jchess.core.moves.Move;
import jchess.core.pieces.Piece;

public class RandomAI implements AIStrat {

	@Override
	public void move(Chessboard chessboard, Colors color) {
		// System.out.println("RANDOM AI");
		List<Piece> pieces = getComputerPieces(chessboard, color);
		Random random = new Random();
		int index = random.nextInt(pieces.size());
		Piece p = pieces.get(index);
		Set<Square> movesSet = p.getAllMoves();
		List<Square> moves = new ArrayList<Square>(movesSet);
		int index2 = random.nextInt(moves.size());
		Square end = moves.get(index2);
		chessboard.move(p.getSquare(), end);
	}

	// On récupère toutes les pièces (qui ont le droit de bouger) de l'ordinateur
	private List<Piece> getComputerPieces(Chessboard chess, Colors color) {
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
