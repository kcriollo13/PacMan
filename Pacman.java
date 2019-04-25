
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Pacman {
	int x;
	int y;
	int directionX;
	int directionY;
	private boolean dying = false;

	ImageIcon pacman1 = new ImageIcon("src/Images/PacMan1.gif");
	ImageIcon ghost = new ImageIcon("src/Images/Ghost1.gif");
	ImageIcon pacman2up = new ImageIcon("src/Images/PacMan2up.gif");
	ImageIcon pacman2left = new ImageIcon("src/Images/PacMan2left.gif");
	ImageIcon pacman2right = new ImageIcon("src/Images/PacMan2right.gif");
	ImageIcon pacman2down = new ImageIcon("src/Images/PacMan2down.gif");
	ImageIcon pacmanRight = new ImageIcon("src/Images/pacmanRight.gif");
	ImageIcon pacman3up = new ImageIcon("src/Images/PacMan3up.gif");
	ImageIcon pacman3right = new ImageIcon("src/Images/PacMan3right.gif");
	ImageIcon pacman3left = new ImageIcon("src/Images/PacMan3left.gif");
	ImageIcon pacman3down = new ImageIcon("src/Images/PacMan3down.gif");
	ImageIcon pacman4up = new ImageIcon("src/Images/PacMan4up.gif");
	ImageIcon pacman4right = new ImageIcon("src/Images/PacMan4right.gif");
	ImageIcon pacman4left = new ImageIcon("src/Images/PacMan4left.gif");
	ImageIcon pacman4down = new ImageIcon("src/Images/PacMan4down.gif");



	public Pacman(ImageIcon img,int X, int Y, int dX, int dY) {
		img = pacman1;
		x = X;
		y = Y;
		directionX = dX;
		directionY = dY;
	}

	//	Color Color.blue = new Color (0, 15, 159); 

	public void drawPacmanRight(JLabel[][] board) { 
		int newX = x + directionX;
		int newY = y + directionY;
		if (directionX == 1 && directionY == 0) {
			board[x][y].setBackground(Color.BLACK);
			board[newX][newY].setIcon(pacman2right);
			board[newX][newY].setIcon(pacman4right);
			board[x][y].setIcon(null);
		}

	}

	public void move(JLabel[][] board, ImageIcon ghost) {
		//move pacman
		int newX = x + directionX;
		int newY = y + directionY;

//		if (boardInformation[newX][newY] == BoardGraphics.WALL) {
//			
//		}
		if (board[newX][newY].getBackground() == Color.blue) {
			//do nothing
		}

		else if (board[newX][newY].getIcon() == ghost) { 
			dying = true;
			
		}

		else if (dying) {
			Main.exitGame();
		}

		else {

			if (directionX == 1 && directionY == 0) {
				board[x][y].setBackground(Color.BLACK);
				board[x][y].setIcon(null);
				board[newX][newY].setIcon(pacman2right);
				//board[x][y].setIcon(pacman4right);
//				board[x][y].setIcon(blank);
				x = newX;
				y = newY;
			}
			if(directionX == 0 && directionY == -1) {
				board[x][y].setBackground(Color.BLACK);
				board[x][y].setIcon(null);
				board[newX][newY].setIcon(pacman2up);
				//board[x][y].setIcon(pacman4up);
//				board[x][y].setIcon(blank);
				x = newX;
				y = newY;

			}
			if (directionX == 0 && directionY == 1) {
				board[x][y].setBackground(Color.BLACK);
				board[x][y].setIcon(null);
				board[newX][newY].setIcon(pacman2down);
//				board[x][y].setIcon(blank);
				x = newX;
				y = newY;
			}
			if (directionX == -1 && directionY == 0) {
				board[x][y].setBackground(Color.BLACK);
				board[x][y].setIcon(null);
				board[newX][newY].setIcon(pacman2left);
//				board[x][y].setIcon(blank);
				x = newX;
				y = newY;
			}

			//			//change color of the board to display new position of pacman
//						board[x][y].setBackground(Color.BLACK);
//						board[newX][newY].setIcon(pacman1);
					//	board[x][y].setIcon(null);
			//			//update pacman new position
//						x = newX;
//						y = newY;
		}

	}
}







