import java.lang.Object;

public abstract class TetrisPiece extends Object{
	
	/***fields***/
	//3 dimensional array maintaining which grid squares are filled 
	//first dimension is rotation (index 0 -> 0 degrees, index 1 -> 90 degrees, index 2 -> 180 degrees, index 3 -> 270 degrees)
	//second and third dimensions create 4x4 grid with true entries indicating a filled square
	protected boolean[][][] filledSquares;

	//Maintains the current rotation. 0 -> 0 degrees, 1 -> 90 degrees, 2 -> 180 degrees, 3 -> 270 degrees.
	protected int pieceRotation = 0;
	
	/***Constructor***/
	public TetrisPiece()
	{
		
	}
	
	/***Methods***/
	//Get the rotation of this piece.
	public int getPieceRotation()
	{	
		int a = pieceRotation-1;
		int b = 4;

		//return pieceRotation;
		return (int) (((a % b) + b) % b);	
		
		//return Math.floorDiv(pieceRotation-1, 4);	
	}
	
	//Checks if there is a TetrisBlock at the (row, col) position for the rotation rot, 
	//where rot is 0, 90, 180 or 270 degrees.
	public boolean isFilled(int rot, int row, int col)
	{
		if(filledSquares[getPieceRotation()][row][col])
			return true;
		else
			return false;
	}
	
	//Rotate the piece counter-clockwise by 90 degrees.
	public void rotateCCW()
	{
		pieceRotation--;
	}
	
	//Rotate the piece clockwise by 90 degrees.
	public void rotateCW()
	{
		pieceRotation++;
	}
}
