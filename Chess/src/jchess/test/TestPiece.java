package jchess.test;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import jchess.core.Chessboard;
import jchess.core.Colors;
import jchess.core.Game;
import jchess.core.Square;
import jchess.core.AI.RandomAI;
import jchess.core.moves.ChainMoveBuilder;
import jchess.core.moves.AbstractMoveBuilder;
import jchess.core.pieces.Piece;
import jchess.core.pieces.implementation.Bishop;
import jchess.core.pieces.implementation.King;
import jchess.core.pieces.implementation.Knight;
import jchess.core.pieces.implementation.Pawn;
import jchess.core.visitor.ContextVisitor;
import jchess.core.visitor.NumberVisitor;
import jchess.core.visitor.PointsVisitor;
import jchess.utils.Settings;

/**
 * Created by macher1 on 12/04/2015.
 */
public class TestPiece {

    private  Settings settings;

    private  Chessboard board;

    @Before
    public void setUp() {
        //SingleFrameApplication.launch(JChessApp.class, new String[] {});

        settings = new Settings();
        board = new Game().getChessboard(); // new Chessboard(settings, new Moves(new Game()));


        // Game g = new Game();
        // #1 bad API design
        // g.newGame(); // fails because coupled to GUI concerns and tabs stuff
        // anyway
        board.setPieces("", settings.getPlayerWhite(), settings.getPlayerBlack());


        // #2 bad API design
        //  Moves moves = new Moves(g);
        // Chessboard board = new Chessboard(settings, moves);
        // g.getChessboard() != board :(
        // board.getMoves() != moves :(


    }
    
    
    @Test
    public void testScore(){
    	board.accept(new PointsVisitor());
    	board.accept(new NumberVisitor());
    }

    @Test
    public void testInitBoard() throws Exception {
        assertEquals(18, board.getAllPieces(Colors.WHITE).size());
        assertEquals(18, board.getAllPieces(Colors.BLACK).size());
        // #3 bad API design
        // assertNotNull(board.getMoves());
    }

    @Test
    public void testBasicMovement() throws Exception {


        Square sq = board.getSquare(5, 1); // 1st rown (black relative)
        Piece p = sq.getPiece();
        assertTrue(p instanceof Pawn);
        assertEquals(Colors.BLACK, p.getPlayer().getColor());

        Piece p2 = board.getSquare(5, 6).getPiece(); // 6th row (black relative)
        assertNotNull(p2);
        assertTrue(p2 instanceof Pawn);
        assertEquals(Colors.WHITE, p2.getPlayer().getColor());

        assertEquals(2, p2.getAllMoves().size()); // e2e3 or e2e4

        Piece p3 = board.getSquare(4, 7).getPiece(); // 7th row (black relative)
        assertNotNull(p3);
        assertTrue(p3 instanceof King);
        assertEquals(Colors.WHITE, p3.getPlayer().getColor());

        assertEquals(0, p3.getAllMoves().size()); // no legal move


        assertNull(board.getSquare(4, 4).getPiece()); // nothing there
        // e2 (4, 6) e4 (4, 4)
        AbstractMoveBuilder chainmb3 = new ChainMoveBuilder(board);
    	chainmb3.xFrom(4).yFrom(6).xTo(4).yTo(4);
        //board.move(4, 6, 4, 4);

        // #4 bad API design
        //assertEquals(1, board.getMoves().size());

        assertNull(board.getSquare(4, 6).getPiece()); // now the pawn is not present in e2
        Piece p4 = board.getSquare(4, 4).getPiece(); // and there is a pawn in e4
        assertTrue(p4 instanceof Pawn);
        assertEquals(Colors.WHITE, p4.getPlayer().getColor());





    }

    @Test
    public void testBishop1() throws Exception {
    	//TODO
    	ChainMoveBuilder chainmb = new ChainMoveBuilder(board);
    	System.out.println(board.getSquare(5, 6).getPiece().getName());
    	chainmb.xFrom(5).yFrom(6).xTo(5).yTo(5);
    	System.out.println(board.getSquare(5, 6).getPiece().getName());
    	System.out.println(board.getSquare(5, 6).getPiece().getName()+"\n");
        // f2 (5, 6) f4 (5, 4)
        //board.move(5, 6, 5, 4);
        
        // f7 (4, 1) f5 (4, 3)
    	AbstractMoveBuilder chainmb2 = new ChainMoveBuilder(board);
    	chainmb2.xFrom(5).yFrom(1).xTo(5).yTo(3);
        //board.move(5, 1, 5, 3);


        assertNull(board.getSquare(5, 1).getPiece()); // now the pawn is not present in e7
        Piece p1 = board.getSquare(5, 3).getPiece(); // and there is a pawn in e5
        assertTrue(p1 instanceof Pawn);
        assertEquals(Colors.BLACK, p1.getPlayer().getColor());

        // bishop in f1
        Piece b1 = board.getSquare(5, 7).getPiece();
        assertTrue(b1 instanceof Bishop);
        assertEquals(Colors.WHITE, b1.getPlayer().getColor());

        assertEquals(5, b1.getAllMoves().size());


    }

    @Test
    public void testBishop2() throws Exception {

        // d2 (3, 6) d4 (3, 4)
    	AbstractMoveBuilder chainmb2 = new ChainMoveBuilder(board);
    	chainmb2.xFrom(4).yFrom(1).xTo(4).yTo(3);
        //board.move(3, 6, 3, 4);

        // e7 (4, 1) e5 (4, 3)
    	AbstractMoveBuilder chainmb4 = new ChainMoveBuilder(board);
    	chainmb4.xFrom(4).yFrom(1).xTo(4).yTo(3);
    	//board.move(4, 1, 4, 3);

        // bishop in c1
        Piece b1 = board.getSquare(2, 7).getPiece();
        assertTrue(b1 instanceof Bishop);
        assertEquals(Colors.WHITE, b1.getPlayer().getColor());

        assertEquals(5, b1.getAllMoves().size());


    }
    
    @Test
    public void testKnight() throws Exception {
        Piece b1 = board.getSquare(1, 7).getPiece();
        assertTrue(b1 instanceof Knight);
        assertEquals(Colors.WHITE, b1.getPlayer().getColor());
       
        assertEquals(2, b1.getAllMoves().size());
    }
}
