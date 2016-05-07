package jchess.core;

import java.util.Random;

import jchess.core.pieces.Piece;
import jchess.core.pieces.implementation.Bishop;
import jchess.core.pieces.implementation.King;
import jchess.core.pieces.implementation.Knight;
import jchess.core.pieces.implementation.Pawn;
import jchess.core.pieces.implementation.Queen;
import jchess.core.pieces.implementation.Rook;
import jchess.core.pieces.implementation.Sorcier;

public class RandomPlacement implements PlacementStrat {

	private Chessboard _chess;
	// @Override
	// public void setPieces(Player plWhite, Player plBlack, Chessboard chess) {
	// this._chess = chess;
	// Player player = plBlack;
	// Player player1 = plWhite;
	// this.setFigures4NewGame(0, player);
	// this.setPawns4NewGame(1, player);
	// this.setFigures4NewGame(7, player1);
	// this.setPawns4NewGame(6, player1);
	//
	// //TODO
	// this.setSorcier4NewGame(2, player);
	// this.setSorcier4NewGame(5, player1);
	//
	// }

	@Override
	public void setPieces(Player plWhite, Player plBlack, Chessboard chess) {
		this._chess = chess;
		Player player = plBlack;
		Player player1 = plWhite;
		Player[] players = { player, player1};
		Random random = new Random();
		for (Player pl : players) {
			// Création de toutes les pièces
			Piece sorcier = new Sorcier(_chess, pl );
			Piece sorcier1 = new Sorcier(_chess, pl);
			Piece king = new King(_chess, pl);
			Piece queen = new Queen(_chess, pl);
			Piece rook = new Rook(_chess, pl);
			Piece rook1 = new Rook(_chess, pl);
			Piece bishop = new Bishop(_chess,pl);
			Piece bishop1 = new Bishop(_chess, pl);
			Piece pawn = new Pawn(_chess, pl);
			Piece pawn1 = new Pawn(_chess, pl);
			Piece knight = new Knight(_chess, pl);
			Piece knight1 = new Knight(_chess, pl);
			Piece[] pieces = { sorcier, sorcier1, king, queen, rook, rook1, bishop, bishop1, pawn, pawn1, knight,
					knight1 };
			for (Piece p : pieces) {
				int x = random.nextInt(8);
				int y = random.nextInt(8);
				while (_chess.getSquare(x, y).getPiece() != null) {
					x = random.nextInt(8);
					y = random.nextInt(8);
				}
				if (p == king) {
					if (pl.getColor() == Colors.WHITE) {
						_chess.kingWhite = (King) king;
						_chess.getSquare(x, y).setPiece(_chess.kingWhite);
					} else {
						_chess.kingBlack = (King) king;
						_chess.getSquare(x, y).setPiece(_chess.kingBlack);
					}
					
				}
				else{
					_chess.getSquare(x, y).setPiece(p);
				}
			}
		}
	}

	// TODO put a Sorcier
	/**
	 * 
	 */
	private void setSorcier4NewGame(int i, Player player) {
		if (i != 2 && i != 5) {
			// LOG.error("error setting sorcier");
			return;
		} else if (i == 2) {
			player.goDown = true;
		}

		_chess.getSquare(1, i).setPiece(new Sorcier(_chess, player));
		_chess.getSquare(2, i).setPiece(new Sorcier(_chess, player));
		
	}

	/**
	 * Method to set Figures in row (and set Queen and King to right position)
	 * 
	 * @param i
	 *            row where to set figures (Rook, Knight etc.)
	 * @param player
	 *            which is owner of pawns
	 * @param upsideDown
	 *            if true white pieces will be on top of chessboard
	 */
	private void setFigures4NewGame(int i, Player player) {
		if (i != 0 && i != 7) {
			// LOG.error("error setting figures like rook etc.");
			return;
		} else if (i == 0) {
			player.goDown = true;
		}

		_chess.getSquare(0, i).setPiece(new Rook(_chess, player));
		_chess.getSquare(7, i).setPiece(new Rook(_chess, player));
		_chess.getSquare(1, i).setPiece(new Knight(_chess, player));
		_chess.getSquare(6, i).setPiece(new Knight(_chess, player));
		_chess.getSquare(2, i).setPiece(new Bishop(_chess, player));
		_chess.getSquare(5, i).setPiece(new Bishop(_chess, player));

		_chess.getSquare(3, i).setPiece(new Queen(_chess, player));
		if (player.getColor() == Colors.WHITE) {
			_chess.kingWhite = new King(_chess, player);
			_chess.getSquare(4, i).setPiece(_chess.kingWhite);
		} else {
			_chess.kingBlack = new King(_chess, player);
			_chess.getSquare(4, i).setPiece(_chess.kingBlack);
		}
	}

	/**
	 * method set Pawns in row
	 * 
	 * @param i
	 *            row where to set pawns
	 * @param player
	 *            player which is owner of pawns
	 */
	private void setPawns4NewGame(int i, Player player) {
		if (i != 1 && i != 6) {
			// LOG.error("error setting pawns etc.");
			return;
		}
		for (int x = 0; x < 8; x++) {
			_chess.getSquare(x, i).setPiece(new Pawn(_chess, player));
		}
	}

}
