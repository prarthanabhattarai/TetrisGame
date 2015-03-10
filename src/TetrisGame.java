import java.lang.Object;

public class TetrisGame{

	/*** Fields ***/
	static final int RIGHT = 0;
	static final int LEFT = 1;
	static final int DOWN = 2;
	static final int CW = 3;
	static final int CCW = 4;
	private TetrisBoard tetrisBoard;
	private int numLines;
	private int numTetrises;

	/*** Constructor ***/
	//set up the board
	public TetrisGame() {
		tetrisBoard = new TetrisBoard();
	}

	/*** Methods ***/

	// Try to move the current piece with RIGHT, LEFT, DOWN, CW, CCW
	public void attemptMove(int moveType) {
		switch (moveType) {
		case LEFT:
			tetrisBoard.moveLeft();
			break;

		case RIGHT:
			tetrisBoard.moveRight();
			break;

		case DOWN:
			if (!tetrisBoard.moveDown()) {
				endRound();
			}
			break;

		case CW: 
			tetrisBoard.rotateCW();
			break;

		case CCW:
			tetrisBoard.rotateCCW();
			break;

		}
	}

	// Performed when a piece cannot move down anymore.
	// Ends the round by checking for newly formed lines and adding a new piece.
	private void endRound() {
		tetrisBoard.landPiece();
		numLines = tetrisBoard.numberOfFormedLines();
	}

	// Returns numLines
	public int getNumLines() {
		return numLines;
	}

	// Returns numTetris
	public int getNumTetrises() {
		
		//inovke getTetrisNum() method on tetris Board to get the number of pieces formed
		numTetrises = tetrisBoard.getTetrisNum();
		return numTetrises;
	}

	// Returns tetrisBoard
	public TetrisBoard getTetrisBoard() {
		return tetrisBoard;
	}

}
