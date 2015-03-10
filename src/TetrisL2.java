
public class TetrisL2 extends TetrisPiece{

	public TetrisL2()
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
		a[0][0][1]=true;
		a[0][1][1]=true;
		a[0][2][0]=true;
		a[0][2][1]=true;
		
		a[1][1][0]=true;
		a[1][2][0]=true;
		a[1][2][1]=true;
		a[1][2][2]=true;
		
		a[2][0][0]=true;
		a[2][0][1]=true;
		a[2][1][0]=true;
		a[2][2][0]=true;
		
		a[3][1][0]=true;
		a[3][1][1]=true;
		a[3][1][2]=true;
		a[3][2][2]=true;
		
	}

}
