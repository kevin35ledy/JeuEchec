package jchess.core.AI;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import jchess.core.Chessboard;
import jchess.core.Colors;
import jchess.core.Square;
import jchess.core.pieces.Piece;

public abstract class AIStrat {

	abstract public void move(Chessboard chess, Colors color);

	// On r√©cup√®re toutes les pi√®ces (qui ont le droit de bouger) de
	// l'ordinateur
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

	/**
	 * recupere
	 * 
	 * @param chess
	 * @param color
	 * @return
	 */
	protected List<Piece> getPieces(Chessboard chess, Colors color) {
		List<Piece> res = new ArrayList<Piece>();
		Square[][] s = chess.getSquares();
		for (int i = 0; i < chess.getSettings().getSize(); i++) {
			for (int j = 0; j < chess.getSettings().getSize(); j++) {
				if (s[i][j].getPiece() != null) {
					Piece p = s[i][j].getPiece();
					if (p.getPlayer().getColor() == color) {
						res.add(p);
					}
				}
			}
		}
		return res;
	}
	
	/**
	 * Effectue un dÈplacement alÈtoire parmi les dÈplacements possibles
	 * @param chessboard
	 * @param color
	 */
	protected void randomMove(Chessboard chessboard, Colors color) {
		// System.out.println("RANDOM AI");
		Square begin = null, end = null;
		// On rÈcupËre toutes les piËces du computer qui bougent
		List<Piece> pieces = getComputerPieces(chessboard, color);
		List<Pair<Square, Square>> possibleMoves = new ArrayList<Pair<Square, Square>>();
		for (Piece p : pieces) {
			begin = p.getSquare();
			// Pour tous les mouvements de chaque piËce
			for (Square sq : p.getAllMoves()) {
				possibleMoves.add(new Pair<Square, Square>(begin, sq));
			}

		}
		if (possibleMoves.size() > 0) {
			Random r = new Random();
			int index = r.nextInt(possibleMoves.size());
			begin = possibleMoves.get(index).getFirst();
			end = possibleMoves.get(index).getSecond();
			chessboard.move(begin, end);
		}
	}
}
