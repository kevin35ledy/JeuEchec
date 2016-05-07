package jchess.core;

import jchess.core.Chessboard;
import jchess.core.Player;

public class ContextStrat {
	private PlacementStrat _strat;
	
	public ContextStrat(PlacementStrat strat){
		this._strat = strat;
	}
	
	public void setPieces(Player plWhite, Player plBlack, Chessboard chess){
		_strat.setPieces(plWhite, plBlack, chess);
	}

}
