package jchess.core.pieces.traits.behaviors.implementation;

import java.util.HashSet;
import java.util.Set;

import jchess.core.Chessboard;
import jchess.core.Square;
import jchess.core.pieces.Piece;
import jchess.core.pieces.implementation.King;
import jchess.core.pieces.traits.behaviors.Behavior;

public class SorcierBehavior extends Behavior{

	public SorcierBehavior(Piece piece) {
		super(piece);
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * this piece has the behavior of the knight -> cf Sorcier implementation
	 * and can eat or move on the three square in front of it (however it can eat the piece in front of it
	 * 
	 * //  _______________ Y:<br/>
        // |_|_|_|_|_|_|_|_|7<br/>
        // |_|_|_|_|_|_|_|_|6<br/>
        // |_|_|X|_|X|_|_|_|5<br/>
        // |_|X|X|X|X|X|_|_|4<br/>
        // |_|_|_|S|_|_|_|_|3<br/>
        // |_|X|_|_|_|X|_|_|2<br/>
        // |_|_|X|_|X|_|_|_|1<br/>
        // |_|_|_|_|_|_|_|_|0<br/>
        //X:0 1 2 3 4 5 6 7
	 */
	@Override
	public Set<Square> getSquaresInRange() {
		// TODO Auto-generated method stub
		Set<Square> list = new HashSet<>();
        Chessboard chessboard = piece.getChessboard();

        int ligneDevant = piece.getSquare().getPozY() - 1;
        if (piece.getPlayer().isGoDown()) //check if player "go" down or up
        {
            ligneDevant = piece.getSquare().getPozY() + 1;//if yes, change value
        }
        
        if (piece.isOut(ligneDevant, ligneDevant)) //out of bounds protection
        {
            return list;//return empty list
        }
        
        for (int i = -1; i<=1; i++){
        	//on check le carrédevant à gauche, devant, et devant à droite
        	if(!piece.isOut(piece.getSquare().getPozX() + i, ligneDevant)){
        		Square sq = chessboard.getSquare(piece.getSquare().getPozX() + i, ligneDevant);
        		list.add(sq);
        		
        	}
        	
        }
        
        return list;
	}

}
