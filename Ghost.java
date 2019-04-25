
import java.awt.Color;
import java.util.Random;
import java.awt.Graphics;
import java.awt.Image;


import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Ghost {
	int x;
	int y;
	int directionX, directionY;
	//ImageIcon ghost = new ImageIcon("Images/Ghost1.jpeg");
	Color myblue = new Color (0, 15, 159); 
	//ImageIcon dots = new ImageIcon("Images/dots.jpg");
	public boolean dying = false;
	ImageIcon pacman1 = new ImageIcon("src/Images/PacMan1.gif");

	public Ghost(ImageIcon img, int X, int Y, int dX, int dY) {
		//ghost = img;
		x = X;
		y = Y;
		directionX = dX;
		directionY = dY;
	}


	public void move(JLabel[][] board, ImageIcon ghost, ImageIcon dots) {
		//move ghost
		Random random = new Random();

		int newX = x + directionX;
		int newY = y + directionY;
		if (board[newX][newY].getBackground() == Color.BLUE) {
			//do nothing
			if (directionX == 0) {
				directionY = 0;
				directionX = random.nextBoolean() ? 1 : -1 ;
				//                directionX = -1; // 1 or -1
			}
			else if(directionY == 0) {
				directionX = 0;
				directionY = random.nextBoolean() ? 1 : -1 ;
				//                directionY = 1; // 1 or -1
			}
		} 
		else  {
			if (board[x][y].getBackground()== Color.YELLOW) {
				//change color of the board to display new position of pacman
				//board[x][y].setBackground(Color.black);
				board[newX][newY].setIcon(ghost);
				board[x][y].setIcon(dots);
			} else {
				board[newX][newY].setIcon(ghost);
				board[x][y].setIcon(null);

			}
//			if (board[newX][newY].getIcon() == pacman1 || board[newX][newY].getIcon() == BoardGraphics.pacman2up || 
//					board[newX][newY].getIcon() == BoardGraphics.pacman2left || board[newX][newY].getIcon() == BoardGraphics.pacman2right
//					|| board[newX][newY].getIcon() == BoardGraphics.pacman2down || board[newX][newY].getIcon() == BoardGraphics.pacman3up
//					|| board[newX][newY].getIcon() == BoardGraphics.pacman3left || board[newX][newY].getIcon() == BoardGraphics.pacman3right
//					|| board[newX][newY].getIcon() == BoardGraphics.pacman3down || board[newX][newY].getIcon() == BoardGraphics.pacman4up ||
//					board[newX][newY].getIcon() == BoardGraphics.pacman4left || board[newX][newY].getIcon() == BoardGraphics.pacman4right ||
//					board[newX][newY].getIcon() == BoardGraphics.pacman4down) { 
				dying = true;

			}

			else if (dying) {
				Main.exitGame();
			}

			//update ghost new position
			x = newX;
			y = newY;
		}


	}
}
