package jchess.core.moves;

import jchess.core.Chessboard;
import jchess.core.AI.Pair;

public class AlgebricMoveBuilder extends AbstractMoveBuilder{

	public AlgebricMoveBuilder(Chessboard chess) {
		this.board = chess;
		createMove();
	}
	private Pair<Character, Integer> from;
	private Pair<Character, Integer> to;
	
	
	@Override
	public AbstractMoveBuilder xFrom(int xF) {
		// TODO Auto-generated method stub
		System.out.println("wrong class, you should use ChainMoveBuilder");
		return null;
	}

	@Override
	public AbstractMoveBuilder yFrom(int yF) {
		// TODO Auto-generated method stub
		System.out.println("wrong class, you should use ChainMoveBuilder");
		return null;
	}

	@Override
	public AbstractMoveBuilder xTo(int xTo) {
		// TODO Auto-generated method stub
		System.out.println("wrong class, you should use ChainMoveBuilder");
		return null;
	}

	@Override
	public AbstractMoveBuilder yTo(int yTo) {
		// TODO Auto-generated method stub
		System.out.println("wrong class, you should use ChainMoveBuilder");
		return null;
	}

	@Override
	public AbstractMoveBuilder from(String s) {
		// TODO Auto-generated method stub
		if (s.length() == 2) {
			s = s.toLowerCase(); //on garantit la valeur ascii

			//Set the pair objects
			Character x = (char) (s.charAt(0) - 97); //valeur ascii
			from.setFirst(x); 
			int y = 8 - (s.charAt(1) - 48); //fait la correspondance entre la valeur ascii et le int
			//les x sont inversés
			from.setSecond(y);

			this.move.getFrom().setPozX((int) x);
			this.move.getFrom().setPozY(y);

			//Actually move
			this.move();
			return this;
		}
		return null;
	}

	@Override
	public AbstractMoveBuilder to(String s) {
		if (s.length() == 2) {
			s = s.toLowerCase(); //on garantit la valeur ascii

			//Set the pair objects
			Character x = (char) (s.charAt(0) - 97);//valeur ascii
			to.setFirst(x); 
			int y = 8 - (s.charAt(1) - 48); //fait la correspondance entre la valeur ascii et le int
			//les x sont inversés
			to.setSecond(y);


			this.move.getTo().setPozX((int) x);
			this.move.getTo().setPozY(y);

			//Actually move
			this.move();
			return this;
		}
		return null;
	}

}
