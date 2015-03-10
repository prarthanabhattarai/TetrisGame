
public class TetrisStick extends TetrisPiece{
	
	public TetrisStick()
	{
		//invoke the superclass
		super();
		
		//create a 3D array
		filledSquares = new boolean [4][4][4];
		
		//set contents of the array
		setContents(filledSquares);

	}

	public void setContents(boolean[][][] a)
	{
		a[0][0][0]=true;
		a[0][1][0]=true;
		a[0][2][0]=true;
		a[0][3][0]=true;
		
		a[1][0][0]=true;
		a[1][0][1]=true;
		a[1][0][2]=true;
		a[1][0][3]=true;
		
		a[2][0][0]=true;
		a[2][1][0]=true;
		a[2][2][0]=true;
		a[2][3][0]=true;
		
		a[3][0][0]=true;
		a[3][0][1]=true;
		a[3][0][2]=true;
		a[3][0][3]=true;
		
	}

}
