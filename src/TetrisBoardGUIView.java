import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.MenuContainer;
import java.io.Serializable;

public class TetrisBoardGUIView extends javax.swing.JComponent{
	
	/***fields***/
	private TetrisBoard board;
	
	/***constructor***/
	public TetrisBoardGUIView(TetrisBoard b)
	{
		board = b;
	}
	
	/***methods***/
	public void paint(java.awt.Graphics g)
	{		 
		//create an integer variable to store the size of block
		int  a = computeBlockSize();
		
		//invoke function to paint board outline
		paintBoardOutline( g,a);
		
		//set color of board to white
		g.setColor( Color.white);
		
		//draw a rectangle at (0,0); this represents the baord
		g.fillRect(0,0, a*board.NUM_COLS, a*board.NUM_ROWS);
		
		
		//for each square on the grid
		for (int x = 0; x < board.NUM_ROWS; x++)
		{
			for (int y = 0; y < board.NUM_COLS; y++)
			{
						
				//if it has a block, paint block at x and y gird position
				if (board.hasBlock(x,y))
					
					//multiply by a to get appropriate position in terms of pixels
					paintBlock(g, a*y,a*x, computeBlockSize());
			}
		}

	}

	//this function draws the board outline
	private void paintBoardOutline(java.awt.Graphics g, int blockSize)
	{
		//set color to black
		g.setColor(Color.black);
		
		//draw outline of rectangle
		g.drawRect(0,0, blockSize*board.NUM_COLS, blockSize*board.NUM_ROWS);
		
	}
	
	//this function paints blocks on the board
	private void paintBlock(java.awt.Graphics g,int row,int col,int blockSize)
	{
		//this shows up as outline of each block
		g.clearRect(row,col,blockSize,blockSize);
		
		//set color to blue
		g.setColor( Color.blue );
		
		//draw a filled rectangle at position (row, col)
		g.fillRect(row,col,blockSize-1,blockSize-1);	
		
	}
	
	//this function computes the block size
	private int computeBlockSize()
	{
		//divide the height the board by number of rows
		int x = this.getHeight()/board.NUM_ROWS;
		
		//divide the width the board by number of columns
		int y = this.getWidth()/board.NUM_COLS;
		
		//since each block is a square, return the smaller number
		return Math.min(x,y);
	}


}
