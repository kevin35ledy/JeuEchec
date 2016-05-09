package jchess.core.AI;

import jchess.core.Chessboard;
import jchess.core.Colors;

public class GloutonAI implements AIStrat {

	@Override
	public void move(Chessboard chess, Colors color) {
		
			System.out.println("Hey je suis Glouton MAIS JE JOUE !!!!");

			//Retrieving Computer Pieces (that can moves)
			List<Piece> pieces = getComputerOwnPieces(ch, pl);

			//Get the first piece that can take an opponent piece
			Piece p = getTakingPiece(pieces, pl);
			Square end = getEndingSquare(p, pl);

			//Actually move
			ch.move(p.getSquare(), end);
		}

		private Square getEndingSquare(Piece p, Player pl) {
			Square result = null;
			short pieceValue = 0;
			
			for (Square s : p.getAllMoves())
				if (s.getPiece() != null)
					if (s.getPiece().getPlayer() != pl)
						if (s.getPiece().getValue() > pieceValue) {
							pieceValue = s.getPiece().getValue();
							result = s;
						}

			if (pieceValue != 0)
				return result;
			
			//If no square found with an opponent piece, choose randomly the move
			List<Square> moves = new ArrayList<Square>(p.getAllMoves());
			Random r = new Random();
			int i = r.nextInt(moves.size());
			
			return moves.get(i);
		}

		private Piece getTakingPiece(List<Piece> pieces, Player pl){
			Piece result = null;
			int pieceValue = 0;

			//For each piece of pieces
			for (Piece p : pieces) {
				//For each square that p can move
				for(Square s : p.getAllMoves()) {
					//If we have a piece on this Square
					if (s.getPiece() != null)
						//And if this piece is an opponent piece
						if (s.getPiece().getPlayer() != pl) {
							//If the value of the piece is superior of the current value
							if (s.getPiece().getValue() > pieceValue) {
								pieceValue = s.getPiece().getValue();
								result = p;
							}
						}
				}
			}
			//If we have found a piece to taken
			if (pieceValue != 0)
				return result;
			
			//In case where no opponent pieces can be taken, randomly move like Random Strategy
			Random r = new Random();
			int i = r.nextInt(pieces.size());
			result = pieces.get(i);

			return result;
		}
	}
}

}
