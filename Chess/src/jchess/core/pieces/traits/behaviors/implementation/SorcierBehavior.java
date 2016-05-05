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
        Square sqToMove;//square we want to move to
        Chessboard chessboard = piece.getChessboard();

        int first = piece.getSquare().getPozY() - 1;
        if (piece.getPlayer().isGoDown()) //check if player "go" down or up
        {
            first = piece.getSquare().getPozY() + 1;//if yes, change value
        }
        
        if (piece.isOut(first, first)) //out of bounds protection
        {
            return list;//return empty list
        }
        
        sqToMove = chessboard.getSquare(piece.getSquare().getPozX(), first);
        
        
        
        
        if (sqToMove.getPiece() == null) //if next is free
        {

            list.add(chessboard.getSquares()[piece.getSquare().getPozX()][first]);
            
            if(piece.getSquare().getPozX() != chessboard.getTop()){
            	list.add(chessboard.getSquares()[piece.getSquare().getPozX()-1][first]);
            }
            
            if(piece.getSquare().getPozX() != chessboard.getBottom())
            {
            	list.add(chessboard.getSquares()[piece.getSquare().getPozX()+1][first]);            	
            }
            

            
        }
        
        
        return list;
	}

}
