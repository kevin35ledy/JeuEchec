/*
	#    This program is free software: you can redistribute it and/or modify
#    it under the terms of the GNU General Public License as published by
#    the Free Software Foundation, either version 3 of the License, or
#    (at your option) any later version.
#
#    This program is distributed in the hope that it will be useful,
#    but WITHOUT ANY WARRANTY; without even the implied warranty of
#    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
#    GNU General Public License for more details.
#
#    You should have received a copy of the GNU General Public License
#    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/*
 * Author: Mateusz Sławomir Lach ( matlak, msl )
 */
package jchess.core.moves.move_plus_decorator;

import jchess.core.Chessboard;
import jchess.core.pieces.Piece;
import jchess.core.moves.Castling;
import jchess.core.Square;;

public class Move extends AbstractMove
{
	
    protected Piece movedPiece = null;
    protected Piece takenPiece = null;
    protected Piece promotedTo = null;
    protected boolean wasEnPassant = false;
    protected Castling castlingMove = Castling.NONE;
    protected boolean wasPawnTwoFieldsMove = false;

    public Move(Square from, Square to, Piece movedPiece, Piece takenPiece, Castling castlingMove, boolean wasEnPassant, Piece promotedPiece, String duree, String com, Chessboard chess)
    {
        super(from,to,duree,com, chess);
        
        
        this.movedPiece = movedPiece;
        this.takenPiece = takenPiece;

        this.castlingMove = castlingMove;
        this.wasEnPassant = wasEnPassant;

        if (movedPiece.getName().equals("Pawn") && Math.abs(to.getPozY() - from.getPozY()) == 2)
        {
            this.wasPawnTwoFieldsMove = true;
        }
        else if (movedPiece.getName().equals("Pawn") && to.getPozY() == Chessboard.getBottom() || to.getPozY() == Chessboard.getTop() && promotedPiece != null)
        {
            this.promotedTo = promotedPiece;
        }
    }

    public Piece getMovedPiece()
    {
        return this.movedPiece;
    }

    public Piece getTakenPiece()
    {
        return this.takenPiece;
    }

    public boolean wasEnPassant()
    {
        return this.wasEnPassant;
    }

    public boolean wasPawnTwoFieldsMove()
    {
        return this.wasPawnTwoFieldsMove;
    }

    public Castling getCastlingMove()
    {
        return this.castlingMove;
    }

    public Piece getPromotedPiece()
    {
        return this.promotedTo;
    }

	@Override
	protected void commentMove(String com) {
		// TODO Auto-generated method stub
		this.comment = com;
	}

	@Override
	protected void setTime(String time) {
		// TODO Auto-generated method stub
		this.time = time;
	}

	@Override
	public void setXFrom(int xF) {
		// TODO Auto-generated method stub
		this.from.setPozX(xF);
	}

	@Override
	public void setYFrom(int yF) {
		// TODO Auto-generated method stub
		this.from.setPozY(yF);
	}

	@Override
	public void setXTo(int xTo) {
		// TODO Auto-generated method stub
		this.to.setPozX(xTo);
	}

	@Override
	public void setYTo(int yTo) {
		// TODO Auto-generated method stub
		this.to.setPozY(yTo);
	}
}
