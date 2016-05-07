package jchess.core.visitor;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import jchess.core.Chessboard;
import jchess.core.Colors;
import jchess.core.Square;
import jchess.core.pieces.Piece;

public class NumberVisitor implements Visitor {

	Map<String, Integer> white = new HashMap<String, Integer>();
	Map<String, Integer> black = new HashMap<String, Integer>();

	@Override
	public void visit(Chessboard chess) {
		String sW = "";
		String sB = " ";
		for (Entry<String, Integer> entry : white.entrySet()) {
			String piece = entry.getKey();
			int nb = entry.getValue();
			sW += piece + " : " + nb + " | ";
		}
		for (Entry<String, Integer> entry : black.entrySet()) {
			String piece = entry.getKey();
			int nb = entry.getValue();
			sB += piece + " : " + nb + " | ";
		}
		System.out.println("White : " + sW);
		System.out.println("Black : " + sB);
	}

	@Override
	public void visit(Square sq) {
		if (sq.getPiece() != null) {
			String pName = sq.getPiece().getName();
			String color = sq.getPiece().getPlayer().getColor().getColorName();
			if (color == "white") {
				if (white.containsKey(pName)) {
					int val = white.get(pName);
					white.put(pName, val + 1);
				} else {
					white.put(pName, 1);
				}
			} 
			else {
				if (black.containsKey(pName)) {
					int val = black.get(pName);
					black.put(pName, val + 1);
				} else {
					black.put(pName, 1);
				}
			}
		}
	}

}
