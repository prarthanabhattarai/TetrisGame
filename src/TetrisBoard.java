/***
 * The TetrisBoard class represents the model: a board on which Tetris is played; 
 * it maintains the grid and pieces on it.
 * @author bhatt23p
 *
 */
public class TetrisBoard {

	/***class-level fields***/
	public static final int NUM_COLS = 10;
	public static final int NUM_ROWS = 18;

	/***instance-level fields***/
	private boolean[][] board;
	private int[] currentGridPosition;
	private TetrisPiece currentPiece;
	private int numCols;
	private int numRows;
	private int linesRemoved = 0;
	private int TetrisNum = 0;

	/***constructor 
	 * sets up the board
	 ***/
	public TetrisBoard()
	{
		//initialize board by passing number of rows and columns as parameters
		board = new boolean[NUM_ROWS][NUM_COLS];
		
		//initialize the board
		initBoard();
		
		//add new tetris piece to the board
		addNewPiece();
		
		//initialize the current grid position for the newly added piece
		initCurrentGP();

	}

	/***Instance methods***/

	//Add a new random Tetris piece to the board at grid position (0, 3)
	public void addNewPiece()
	{
		//keep count of number of tetris pieces generated
		TetrisNum++;
		
		//randomly generate tetris pieces
		int p = (int) (Math.random()*7);
		
		switch (p){
		
		case 0: currentPiece = new TetrisStick();
				break;
		case 1: currentPiece = new TetrisSquare();
				break;
		case 2: currentPiece = new TetrisS1();
				break;
		case 3: currentPiece = new TetrisS2();
				break;
		case 4: currentPiece = new TetrisL1();
				break;
		case 5: currentPiece = new TetrisL2();
				break;
		case 6: currentPiece = new TetrisT();
				break;		
		}
		
		//once the piece is added, re-initialize the grid positions of the piece
		initCurrentGP();
	}

	//Checks if placing the piece at grid position (row, col) with the rotation rot (values can be 0, 90, 180, 270) would cause a collision 
	//(i.e., if there would be a block on an already-filled grid square)
	private boolean detectCollision(TetrisPiece piece, int rot, int gridRow, int gridCol)
	{
		//for each square in the tetris piece grid
		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 4 ; j++)
			{
				//if the square is filled
				if (currentPiece.isFilled(currentPiece.pieceRotation,i,j)) 
				{		
						//if the baord is filled at the corresponding grid position
						if(board[i+gridRow][j+gridCol]){
						//System.out.println("There is collision here!");
							
						//there is a collision
						return true;}
				}
				
			}
		}
		//System.out.println("No collision here!");
		
		//else, there is no collision
		return false;
	}

	//Checks if placing the piece at grid position (row, col) with the rotation rot (values can be 0, 90, 180, 270) would cause an out of bounds condition 
	//(i.e., if there would be a block falling off the board).
	private boolean detectOutOfBounds(TetrisPiece piece, int rot, int gridRow, int gridCol)
	{
		//for each square in the tetris piece grid
		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 4 ; j++)
			{
				//if the square is filled
				if (currentPiece.isFilled(currentPiece.pieceRotation,i,j)) 
				{
					//if the filled square falls outside of the baord
					if (i+gridRow >= NUM_ROWS || j+gridCol < 0 || j+gridCol >= NUM_COLS)
					{
					    //it is out of bounds
						return true;
					}	
				}
			}
		}
		//System.out.println("This is inside bounds!");	
		//else, it falls inside the board
		return false;
	}

	//Check if there is a full line at the row
	private boolean fullLine(int row)
	{
		//System.out.println("inside fullLine");
		boolean lineFilled = true;
		
		//for each column in the given row
		for (int col = 0; col < NUM_COLS; col++) 
		{
			//if there is a sqaure that is not filled
		    if (!board[row][col]) 
		    	//the line is not full
		    	lineFilled = false;
		}
		
		//else, the line is full
		return lineFilled;
	}

	//Returns true or false based on whether there's a block
	public boolean hasBlock(int row, int col)
	{
		//for each square in the tetris piece grid
		for (int i = 0; i<4; i++)
		{
			for (int j = 0; j<4; j++)
			{
				//check to see if the row and col fall inside current piece grid
				if (currentGridPosition[0]+i == row && currentGridPosition[1]+j == col )
				{
					//if the square is filled, it has block
					if (currentPiece.isFilled(currentPiece.pieceRotation,i,j))
						return true;
					//else, if the baord grid has a block in the tetris piece's grid area, it has a block
					else 
						return board[row][col];
				}
			}	
		}
		//else, if the baord grid has a block, it has a block
		return board[row][col];

	}
	


	//Initializes the board
	private void initBoard()
	{
		//for each square in the baord
		for (int i = 0; i <NUM_ROWS ; i++)
		{
			for (int j = 0; j <NUM_COLS ; j++)
			{
				//set the value at the square to false
				board[i][j]=false;
			}
		}
	}

	//Initialize an int array of length two 
	//to keep track of the grid position of the current piece (row, col)
	private void initCurrentGP()
	{
		currentGridPosition = new int[2];
		
		//all the new pieces are placed at the position (0,3)
		currentGridPosition[0]=0;
		currentGridPosition[1]=3;
	}

	//Check if moving down is valid. If so, move the current piece down.
	public boolean moveDown()
	{
		boolean canMoveDown=false;
		
		if (validMove(currentPiece, currentPiece.pieceRotation, currentGridPosition[0]+1, currentGridPosition[1]))
		{	
			currentGridPosition[0]++;
			canMoveDown=true;
			
		}	
		
		return canMoveDown;
	}

	//Check if moving left is valid. If so, move the current piece left.
	public void moveLeft()
	{		
		if (validMove(currentPiece, currentPiece.pieceRotation, currentGridPosition[0], currentGridPosition[1]-1))
			currentGridPosition[1]--;
	}

	//Check if moving right is valid. If so, move the current piece right.
	public void moveRight()
	{		
		if (validMove(currentPiece, currentPiece.pieceRotation, currentGridPosition[0], currentGridPosition[1]+1))
			currentGridPosition[1]++;		
	}

	//Detect and remove any lines formed.
	public int numberOfFormedLines()
	{
		//loop through the rows of board to see if any of the lines is filled
		for (int i = 0; i < NUM_ROWS; i ++)	{
			if (fullLine(i))
			{
				//remove the line at row i
				removeLine(i);
				
				//keep a count of lines removed from the baord
				linesRemoved++;
			}
		}	
		return linesRemoved;
	}

	//Update the board array to reflect the newly landed piece's filled squares 
	//using the currentGridPosition values and the currentPiece's rotation value.
	public void landPiece()
	{
		try {
			//for each square in the tetris piece grid
			for (int i = 0; i<4; i++)
			{
				for (int j = 0; j<4; j++)
				{
					//if the square is filled
					if (currentPiece.isFilled(currentPiece.pieceRotation,i,j))
					{	
						//change the boolean value of the baord to true
						board[currentGridPosition[0]+i][currentGridPosition[1]+j]= true;
						//System.out.println("The piece has landed");
					}
					
				}
			}
			//once the piece lands, add new piece to the board
			addNewPiece();

		} catch (ArrayIndexOutOfBoundsException e) {
			
		}
		
	}
	
	//Remove the line at row in the model. 
	//Shift all values for rows at a lower index to be at one row higher. 
	//Make row 0 full of false values.
	private void removeLine(int row)
	{
		//for each row in the grid
		for (int i = row; i >= 0; i--)
		{
			for (int j = 0; j < NUM_COLS; j++)
			{
				//if it is the first row, set the values to false
				if (i == 0)
					board[i][j]=false;
				
				//for all the remaining rows, shift all values for rows at a lower index to be at one row higher. 
				else
					board[i][j]=board[i-1][j];
			}
				
		}
	}
	
	//Check if rotating counter-clockwise is valid.
	//If so, rotate the current piece counter-clockwise by 90 degrees.
	public void rotateCCW()
	{				
		if (validMove(currentPiece, (currentPiece.pieceRotation)-1, currentGridPosition[0], currentGridPosition[1])) 
			//if the piece does not touch the boundary, rotation is allowed
			if (canRotate(currentPiece, (currentPiece.pieceRotation)-1, currentGridPosition[0], currentGridPosition[1]))
		currentPiece.rotateCCW();	
		
	}
	
	//Check if rotating clockwise is valid. 
	//If so, rotate the current piece clockwise by 90 degrees.
	public void rotateCW()
	{	
		if (validMove(currentPiece,(currentPiece.pieceRotation)+1, currentGridPosition[0], currentGridPosition[1]))
			//if the piece does not touch the boundary, rotation is allowed
			if (canRotate(currentPiece, (currentPiece.pieceRotation)-1, currentGridPosition[0], currentGridPosition[1]))
		currentPiece.rotateCW();	
	}
	
	//Checks if placing the piece at grid position (row, col) with the rotation rot (values can be 0, 90, 180, 270) is a valid move.
	private boolean validMove(TetrisPiece piece, int rot, int gridRow, int gridCol)
	{
		if (detectOutOfBounds(piece, rot, gridRow, gridCol))
				return false;
		else if (detectCollision(piece, rot, gridRow, gridCol))
				return false;
			
		else 
		return true;	
	}

	//Checks whether rotation is allowed; if a piece touches the boundary of grid, rotation is not allowed
	private boolean canRotate(TetrisPiece piece, int rot, int gridRow, int gridCol) {
		
	//for each square in the tetris piece grid
	for (int i = 0; i < 4; i++)
	{
		for (int j = 0; j < 4 ; j++)
		{
			//if the square is filled and the square touches the boundary of grid, rotation is not allowed
			if (currentPiece.isFilled(currentPiece.pieceRotation,i,j) &&  j+gridCol <= 0 || j+gridCol >= NUM_COLS)
				{
				    //it is out of bounds
					return false;
				}	
			}
		}
		
		return true;
	}

	//Returns the board
	public boolean[][] getBoard()
	{		
		return board;
	}

	//Returns the number of tetris pieces added to the board
	public int getTetrisNum()
	{
		return TetrisNum;
	}
	
	
	
	//Returns the currentPiece
	public TetrisPiece getCurrentPiece()
	{
		return currentPiece;
	}

	//Return the currentGridPosition
	public int[] getCurrentGridPosition()
	{
		return currentGridPosition;
	}

	//Return the numCols
	public int getNumCols()
	{
		return numCols;
	}

	//Return the numRows 
	public int getNumRows()
	{
		return numRows;
	}

}
