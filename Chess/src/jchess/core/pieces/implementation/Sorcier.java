package jchess.core.pieces.implementation;

import jchess.core.Chessboard;
import jchess.core.Player;
import jchess.core.pieces.Piece;
import jchess.core.pieces.traits.behaviors.implementation.BishopBehavior;
import jchess.core.pieces.traits.behaviors.implementation.KnightBehavior;
import jchess.core.pieces.traits.behaviors.implementation.RookBehavior;
import jchess.core.pieces.traits.behaviors.implementation.SorcierBehavior;

public class Sorcier extends Piece{

	public Sorcier(Chessboard chessboard, Player player) {
		super(chessboard, player);
		// TODO Auto-generated constructor stub
		
		this.symbol = "S";
        this.addBehavior(new KnightBehavior(this));
        this.addBehavior(new SorcierBehavior(this));
	}
	@Override
	public int getValue() {
		return value;
	}
}
