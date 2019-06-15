import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import java.applet.Applet;

//@SuppressWarnings("serial")
public class tictactoe extends java.applet.Applet implements ActionListener {
	
	Button[] grid;
	Button newGameButton;
	int emptysquares = 9;
	int xscore = 0;
	int oscore = 0;
	JLabel score;
	
	
	public String lookforwinner ()
	{
		String thewinner = "";
		emptysquares --;
		if(emptysquares == 0)
			return "Tie";
		else {
			if(!grid[0].getLabel().equals("")&& grid[0].getLabel().equals (grid[1].getLabel()) && grid[0].getLabel().equals(grid[2].getLabel()))
			{
				thewinner = grid[0].getLabel();
				highlightWinner (0,1,2);
			}
			else if(!grid[0].getLabel().equals("")&& grid[0].getLabel().equals (grid[3].getLabel()) && grid[0].getLabel().equals(grid[6].getLabel()))
			{
				thewinner = grid[0].getLabel();
				highlightWinner (0,3,6);
			}
			else if(!grid[6].getLabel().equals("")&& grid[6].getLabel().equals (grid[7].getLabel()) && grid[6].getLabel().equals(grid[8].getLabel()))
			{
				thewinner = grid[6].getLabel();
				highlightWinner (6,7,8);
			}	
			else if(!grid[8].getLabel().equals("")&& grid[8].getLabel().equals (grid[5].getLabel()) && grid[8].getLabel().equals(grid[2].getLabel()))
			{
				thewinner = grid[8].getLabel();
				highlightWinner (8,5,2);
			}
			else if(!grid[1].getLabel().equals("")&& grid[1].getLabel().equals (grid[4].getLabel()) && grid[1].getLabel().equals(grid[7].getLabel()))
			{
				thewinner = grid[1].getLabel();
				highlightWinner (1,4,7);
			}
			else if(!grid[3].getLabel().equals("")&& grid[3].getLabel().equals (grid[4].getLabel()) && grid[3].getLabel().equals(grid[5].getLabel()))
			{
				thewinner = grid[3].getLabel();
				highlightWinner (3,4,5);
			}
			else if(!grid[0].getLabel().equals("")&& grid[0].getLabel().equals (grid[4].getLabel()) && grid[0].getLabel().equals(grid[8].getLabel()))
			{
				thewinner = grid[0].getLabel();
				highlightWinner (0,4,8);
			}
			else if(!grid[6].getLabel().equals("")&& grid[6].getLabel().equals (grid[4].getLabel()) && grid[6].getLabel().equals(grid[2].getLabel()))
			{
				thewinner = grid[6].getLabel();
				highlightWinner (6,4,2);
			}
		}
		
		if(thewinner.equals("X"))
			xscore ++;
		else if(thewinner.equals("O"))
			oscore ++;
		return thewinner;
	}
	
	public void highlightWinner (int win1 , int win2 , int win3)
	{
		grid[win1].setBackground(Color.YELLOW);
		grid[win2].setBackground(Color.YELLOW);
		grid[win3].setBackground(Color.YELLOW);
	}
	
	public int getRandomSquare()
	{
		boolean getEmptySquare = false;
		int selectedSquare;
		do 
		{
		selectedSquare = (int)(Math.random()*9);
		if(grid[selectedSquare].getLabel().equals(""))
			getEmptySquare = true;
		}
		while(!getEmptySquare);
		return selectedSquare;
	}
	public int findEmptySquare (String player) 
	{
		int[]weight = new int [9];
		for(int i=0; i<9; i++)
		{
			if(grid[i].getLabel().equals("O"))
				weight [i] = -1;
			else if (grid[i].getLabel().equals("X"))
				weight [i] = 1;
			else
				weight[i]= 0;
			
		}
		
		int twoweights;
		if (player.equals("O"))
			twoweights = -2;
		else
			twoweights = 2;
		if (weight[0] + weight[1] + weight[2] == twoweights)
		{
			if(weight[0]==0)
				return 0;
			else if (weight[1]==0)
				return 1;
			else 
				return 2;
		}
		if (weight[3] + weight[4] + weight[5] == twoweights)
		{
			if(weight[3]==0)
				return 3;
			else if (weight[4]==0)
				return 4;
			else 
				return 5;
		}
		if (weight[6] + weight[7] + weight[8] == twoweights)
		{
			if(weight[6]==0)
				return 6;
			else if (weight[7]==0)
				return 7;
			else 
				return 8;
		}
		if (weight[0] + weight[3] + weight[6] == twoweights)
		{
			if(weight[0]==0)
				return 0;
			else if (weight[3]==0)
				return 3;
			else 
				return 6;
		}
		if (weight[1] + weight[4] + weight[7] == twoweights)
		{
			if(weight[1]==0)
				return 1;
			else if (weight[4]==0)
				return 4;
			else 
				return 7;
		}
		if (weight[2] + weight[5] + weight[8] == twoweights)
		{
			if(weight[2]==0)
				return 2;
			else if (weight[5]==0)
				return 5;
			else 
				return 8;
		}
		if (weight[0] + weight[4] + weight[8] == twoweights)
		{
			if(weight[0]==0)
				return 0;
			else if (weight[4]==0)
				return 4;
			else 
				return 8;
		}
		if (weight[6] + weight[4] + weight[2] == twoweights)
		{
			if(weight[6]==0)
				return 6;
			else if (weight[4]==0)
				return 4;
			else 
				return 2;
		}
	
		// ALL ROWS COMPLETED
		
		return -1;
	}
	
	public void ComputerMove() {
		int selectedSquare;
		selectedSquare = findEmptySquare("O");
		if(selectedSquare == -1)
			selectedSquare = findEmptySquare("x");
		if(selectedSquare== -1 && grid[4].getLabel().equals(""))
			selectedSquare=4;
		if(selectedSquare==-1)
			selectedSquare=getRandomSquare();
		grid[selectedSquare].setLabel("O");
		grid [selectedSquare].setEnabled(false);
	}
	
	public void paint(java.awt.Graphics graphics) {
		graphics.drawString("loading...", 75, 100);
	}
	public static void main(String[] args)
	{
		
	}
	public void init() {
		grid = new Button [9];
		this.setLayout(new BorderLayout());
		this.setBackground(Color.CYAN);
		Font myFont = new Font ("Arial", Font.BOLD,20);
		Font font1 = new Font ("Arial", Font.BOLD,75);
		this.setFont(myFont);
		
		newGameButton = new Button("New game");
		newGameButton.addActionListener(this);
		Panel topPanel = new Panel(); 
		topPanel.add (newGameButton);
		this.add (topPanel,"North");
		
		Panel centrePanel = new Panel();
		centrePanel.setLayout(new GridLayout(3,3));
		this.add(centrePanel,"Center");
		
		for(int i=0; i<9; i++) {
			grid[i]= new Button();
			grid[i].addActionListener (this);
			//grid[i].setBackground(Color.BLUE);
			grid[i].setLabel("");
			grid[i].setFont(font1);
			centrePanel.add(grid[i]);
		}
		
		Panel J = new Panel();
		score = new JLabel("your turn!");
		Font f2 = new Font("Arial", Font.BOLD,40);
		score.setFont(f2);
		J.add(score);
		
		this.add(J,"South");
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Button gridClicked = (Button)e.getSource();
		String winner;
		
		if(gridClicked==newGameButton) {
			for(int k=0; k<9; k++) {
				grid[k].setLabel("");
				grid [k].setEnabled(true);
				grid[k].setBackground(Color.WHITE);
				winner = "";
			}
			score.setText("New game started!");
			emptysquares = 9;
			return;
		}
		
		for(int i=0; i < 9; i++) {
		if (gridClicked == grid[i]) {
			grid[i].setLabel("X");
			grid[i].setEnabled(false);
			winner = lookforwinner();
			
		if(!"".equals(winner))
			endtheGame(winner);
		else {
			ComputerMove();
			winner = lookforwinner();
			if(!"".equals(winner))
				endtheGame(winner);
			}
		
		break;
		}
			
		}
	  }

	private void endtheGame(String winner) {
		// TODO Auto-generated method stub
		if (winner.equals ("X")) {
			score.setText("You win!" + " " + "X: " + xscore + " | O: " + oscore);
		}
		else if (winner.equals ("O"))
			score.setText("You lose" + " " + "X: " + xscore + " | O: " + oscore);
		else if (winner.equals("Tie"))
			score.setText("It is a tie!" + " " + "X: " + xscore + " | O: " + oscore);
			
		for(int i=0; i<9 ; i++) {
		grid [i].setEnabled(false);
		}
	  }
		
	}
	
	