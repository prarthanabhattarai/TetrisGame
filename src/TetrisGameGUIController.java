import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.MenuContainer;
import java.io.Serializable;
import java.util.EventListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.accessibility.Accessible;



public class TetrisGameGUIController extends javax.swing.JPanel implements java.awt.event.KeyListener{

	/***fields***/
	public static final int DEFAULT_DROP_RATE = 1000;
	private int dropRate;
	private TetrisGame game;
	private javax.swing.Timer gameTimer;
	private javax.swing.JLabel linesLabel = new JLabel ("Number of lines cleared");
	private javax.swing.JLabel tetrisesLabel = new JLabel("Number of Tetris Pieces");
	private TetrisBoardGUIView view;
	private JLabel numLines;
	private JLabel numT;
	
	/***constructor***/
	public TetrisGameGUIController()
	{
		//invoke the constructor of super class
		super(new BorderLayout());
		
		setFocusable(true);
		
		//add key listener
		addKeyListener(this);
		
		//initialize the game
		game = new TetrisGame();
		
		//invoke a function that creates view (of the board) and adds it to the Panel
		createView();
		
		//invoke a function that creates a panel for scores and adds it to the Panel
		createScore();

		//setup the timer
		setupTimer();
	}
	
	/***methods***/
	
	private void setupTimer()
	{
		gameTimer = new Timer (DEFAULT_DROP_RATE, new ActionListener()
		{
			/**
			 * Invoked every time the timer finishes.
			 */
			public void actionPerformed(ActionEvent e)
			{
				//attempt to move the piece down
				game.attemptMove(TetrisGame.DOWN);
 
				// update the view
				refreshDisplay();
			}
		});
 
		// start the game
		gameTimer.start();
	}
	
	private void createView()
	{
		//initiaze the GUI view, passing the board as parameter
		view = new TetrisBoardGUIView(game.getTetrisBoard());
		
		//add the component to the JPanel
		add(view, BorderLayout.CENTER);	
	}
	
	private void createScore()
	{
		//add a panel that shows information about the state of game
		add(createInfoPanel(),BorderLayout.NORTH);
	}
	
	public void refreshDisplay()
	{
		//update the info based on state of game
		numLines.setText(Integer.toString(game.getNumLines()));
		numT.setText(Integer.toString(game.getNumTetrises()));
		
		// update the view
		view.repaint();
		
	}
	
	public JPanel createInfoPanel()
	{
		//create a JPanel to hold a grid of JLabels
		JPanel infoPanel = new JPanel(new GridLayout(2,2));
		
		//initialze JLabels to store the number of lines cleared and the number of tetrises.
		numLines= new JLabel(Integer.toString(game.getNumLines()));
		numT = new JLabel(Integer.toString(game.getNumTetrises()));
		
		//add all the labels to the JPanel
		infoPanel.add(linesLabel);
		infoPanel.add(numLines);
		infoPanel.add(tetrisesLabel);
		infoPanel.add(numT);
		
		//return the JPanel
		return  infoPanel;
		
	}
	
	/***KeyListener Methods***/
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// different behavior depending on which key was released
        switch( e.getKeyCode() )
        {	        	
        	case KeyEvent.VK_RIGHT:
        		//attempt to move right
        		game.attemptMove(TetrisGame.RIGHT);
        		refreshDisplay();
        		break;
        		
        	case KeyEvent.VK_LEFT:
        		//attempt to move left
        		game.attemptMove(TetrisGame.LEFT);
        		refreshDisplay();
        		break;
        
        	case KeyEvent.VK_DOWN:
        		//attempt to move down
        		game.attemptMove(TetrisGame.DOWN);
        		refreshDisplay();
        		break;
        		
        	case KeyEvent.VK_Z:
        		//attempt to rotate cw
        		game.attemptMove(TetrisGame.CW);
        		refreshDisplay();
        		break;
	        	
        	case KeyEvent.VK_X:
        		//attempt to rotate ccw
        		game.attemptMove(TetrisGame.CCW);
        		refreshDisplay();
        		break;

	        default:
	        	System.out.println("KEY RELEASED: " + e.getKeyCode() );
	        
        }		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
