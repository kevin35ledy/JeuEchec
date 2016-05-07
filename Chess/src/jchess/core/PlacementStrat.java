package jchess.core;

import jchess.core.Chessboard;
import jchess.core.Player;

public interface PlacementStrat {
	public void setPieces(Player plWhite, Player plBlack, Chessboard chess);
}
