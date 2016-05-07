package jchess.core.visitor;

import jchess.core.Chessboard;
import jchess.core.Colors;
import jchess.core.Square;
import jchess.core.pieces.Piece;

public class PointsVisitor implements Visitor {

	// pion : 1, fou/cavalier : 3, tour : 5, reine : 10, roi : 1000

	private int scoreWhite = 0;
	private int scoreBlack = 0;

	@Override
	public void visit(Chessboard chess) {
		System.out.println("Score : Blanc = " + scoreWhite + " | Noir = " + scoreBlack);
	}

	@Override
	public void visit(Square sq) {
		Piece p = sq.getPiece();
		int score = 0;
		// si il y a une pièce sur la case, on regarde à qui appartient la pièce
		// et ensuite on ajoute les points
		if (p != null) {
			switch (p.getName()) {
			case "King":
				score += 1000;
				break;
			case "Queen":
				score += 10;
				break;
			case "Bishop":
				score += 3;
				break;
			case "Rook":
				score += 5;
				break;
			case "Pawn":
				score += 1;
				break;
			case "Knight":
				score += 3;
				break;
			case "Sorcier":
				score += 1;
				break;
			}
			if (sq.getPiece().getPlayer().getColor() == Colors.WHITE)
				scoreWhite += score;
			else
				scoreBlack += score;
		}
	}

}
