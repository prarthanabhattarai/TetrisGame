
public class TetrisSquare extends TetrisPiece{

	public TetrisSquare()
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
		for (int i = 0; i<4; i++)
		{			
				for (int j = 0; j<4; j++)
				{
					for (int k = 0; k<4; k++)
					{
						if ((j == 0 || j ==1) && (k == 0 || k == 1))
								a[i][j][k]= true;
							else
								a[i][j][k]= false;
						
					}
				}
			}
	}
	
}
