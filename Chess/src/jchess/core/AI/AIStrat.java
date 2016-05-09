package jchess.core.AI;

import jchess.core.Chessboard;
import jchess.core.Colors;

public interface AIStrat {	
	
	public void move(Chessboard chess, Colors color);
}
