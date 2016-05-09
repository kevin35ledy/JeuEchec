package jchess.core;

import java.util.Random;

import jchess.core.Chessboard;
import jchess.core.Colors;
import jchess.core.Player;
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

	@Override
	public void setPieces(Player plWhite, Player plBlack, Chessboard chess) {
		this._chess = chess;
		//Création des deux joueurs
		Player player = plBlack;
		player.goDown=true;
		
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
			Pawn pawn = new Pawn(_chess, pl);
			Pawn pawn1 = new Pawn(_chess, pl);
			Pawn pawn2 = new Pawn(_chess, pl);
			Pawn pawn3 = new Pawn(_chess, pl);
			Pawn pawn4 = new Pawn(_chess, pl);
			Pawn pawn5 = new Pawn(_chess, pl);
			Pawn pawn6 = new Pawn(_chess, pl);
			Pawn pawn7 = new Pawn(_chess, pl);
			Piece knight = new Knight(_chess, pl);
			Piece knight1 = new Knight(_chess, pl);
			Piece[] pieces = { sorcier, sorcier1, king, queen, rook, rook1, bishop, bishop1, pawn, pawn1,pawn2, pawn3,pawn4,pawn5,pawn6,pawn7, knight,
					knight1 };
			//Pour chaque pièce du tableau ci-dessus, on lui trouve un emplacement alétoire sur l'échéquier

			randomPlacing(random, pl, king, pieces);
		}
	}

	
	
	
	private void randomPlacing(Random random, Player pl, Piece king, Piece[] pieces) {
		for (int cpt = 0; cpt < pieces.length; cpt++) {
			Piece p = pieces[cpt];
			//Génération aléatoire des coordonnées x et y
			int x;
			int y;
			do {
				x = random.nextInt(8);
				y = random.nextInt(8);
			}while (_chess.getSquare(x, y).getPiece() != null);
			
			//Cas particulier pour le roi
			if (p == king) {
				if (pl.getColor() == Colors.WHITE) {
					_chess.kingWhite = (King) king;
				} else {
					_chess.kingBlack = (King) king;
				}

			}
			
			_chess.getSquare(x, y).setPiece(p);
				//si menace on replace la piece
				
			System.out.println("youhou");
			
			if(threatingOtherKing(p, pl, _chess) || (p == king && !((King)p).isSafe())){
				_chess.getSquare(x, y).setPiece(null);
				cpt--;
			}
		}
	}

	/**
	 * regarde si le placement de la piece cause la mise en echec du roi adverse
	 * @param p : piece placee
	 * @param pl : joueur a qui pl appartient
	 * @param chess : chessboard
	 * @return true si la piece menace le roi adverse
	 */
	private boolean threatingOtherKing(Piece p, Player pl, Chessboard chess){
		King otherKing;
		if (pl.getColor() == Colors.WHITE) {
			otherKing = chess.getKingBlack();
		} else {
			otherKing = chess.getKingWhite();
		}
		
		if(otherKing != null && p.getSquaresInRange().contains(otherKing.getSquare())){
			return true;
		}
//		if(otherKing != null && otherKing.canMove(otherKing.getSquare().getPozX(), otherKing.getSquare().getPozY())){
//			return true;
//		}
		return false;
	}
}
