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
		

		// Get the first piece that can take an opponent piece
		Square begin = choosePiece(pieces, color);
		Square end = getEndingSquare(begin, color);

		// Actually move
		chess.move(begin, end);
	}

	private Square getEndingSquare(Square begin, Colors color) {
		Square result = null;
		int pieceValue = 0;

		for (Square s : begin.getPiece().getAllMoves())
			if (s.getPiece() != null)
				if (s.getPiece().getPlayer().getColor() != color)
					if (s.getPiece().getValue() > pieceValue) {
						pieceValue = s.getPiece().getValue();
						result = s;
					}

		if (pieceValue != 0)
			return result;

		// If no square found with an opponent piece, choose randomly the move
		List<Square> moves = new ArrayList<Square>(begin.getPiece().getAllMoves());
		Random r = new Random();
		int i = r.nextInt(moves.size());

		return moves.get(i);
	}

	private Square choosePiece(List<Piece> pieces, Colors color) {
		Square result = null;
		int pieceValue = 0;

		// For each piece of pieces
		for (Piece p : pieces) {
			// For each square that p can move
			for (Square s : p.getAllMoves()) {
				// If we have a piece on this Square
				if (s.getPiece() != null)
					// And if this piece is an opponent piece
					if (s.getPiece().getPlayer().getColor() != color) {
					// If the value of the piece is superior of the current
					// value
					if (s.getPiece().getValue() > pieceValue) {
					pieceValue = s.getPiece().getValue();
					result = p.getSquare();
					}
					}
			}
		}
		// If we have found a piece to taken
		if (pieceValue != 0)
			return result;

		// In case where no opponent pieces can be taken, randomly move like
		// Random Strategy
		Random r = new Random();
		int i = r.nextInt(pieces.size());
		result = pieces.get(i).getSquare();

		return result;
	}

}
