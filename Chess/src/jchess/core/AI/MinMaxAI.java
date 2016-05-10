package jchess.core.AI;

import java.util.List;
import java.util.Random;

import jchess.core.Chessboard;
import jchess.core.Colors;
import jchess.core.Square;
import jchess.core.pieces.Piece;

public class MinMaxAI extends AIStrat {

	// « min-max » : une implémentation de l’algorithme min-max avec comme
	// fonction
	// d’évaluation la somme des valeurs de pièces dans chaque camp

	// pour chaque pièce du computer, on calcule la val max qui correspond à la
	// pièce qu'il peut manger

	// But = maximiser la diff entre MAX-MIN
	
	@Override
	public void move(Chessboard chess, Colors color) {
		int max = 0, min = 0, diff = max - min;
		Square begin = null, end = null, simu = null;
		List<Piece> piecesComputer = getComputerPieces(chess, color);
		for (Piece p : piecesComputer) {
			// cas où une pièce est atteignable
			simu = getSquareMax(chess, color, p);
			if (simu != null) {
				max = simu.getPiece().getValue();
				System.out.println("MAX = " + max);
				if (isThreatened(chess, color, simu))
					min = p.getValue();
				System.out.println("MIN = " + min);
			}
			if ((max - min) > diff) {
				diff = max - min;
				begin = p.getSquare();
				end = simu;
			}
		}
		if (diff == 0) {
			super.randomMove(chess, color);
			
		}
		chess.move(begin, end);

	}

	/**
	 * Calcule la val min
	 * 
	 * @param chessboard
	 * @param color
	 * @param p
	 * @return
	 */
	private boolean isThreatened(Chessboard chessboard, Colors color, Square s) {
		// Les pièces du joueur humain
		Colors colorHuman = Colors.BLACK;
		if (color == Colors.BLACK)
			colorHuman = Colors.WHITE;
		List<Piece> piecesHuman = getComputerPieces(chessboard, colorHuman);
		for (Piece p : piecesHuman) {
			for (Square square : p.getAllMoves()) {
				Piece temp = square.getPiece();
				if (temp != null) {
					if (temp.getPlayer().getColor() != color && square == s) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Calcule la valeur de la meilleure pièce atteignable par l'ordinateur
	 * 
	 * @param chessboard
	 * @param color
	 * @return
	 */
	private Square getSquareMax(Chessboard chessboard, Colors color, Piece p) {
		Square res = null;
		int val = 0;
		for (Square s : p.getAllMoves()) {
			Piece temp = s.getPiece();
			if (temp != null) {
				if (temp.getPlayer().getColor() != color) {
					if (temp.getValue() > val) {
						val = temp.getValue();
						res = temp.getSquare();
					}
				}
			}
		}
		return res;
	}

}
