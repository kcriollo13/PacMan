
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class BoardGraphics {


	public static final int WALL = 1 << 0;
	public static final int FREE = 1 << 1;
	public static final int DOT = 1 << 2;
	public static final int ENERGIZER = 1 << 3;
	public static final int PACMAN = 1 << 4;
	public static final int GHOST = 1 << 5;
	public static final int OUT = 1 << 6;

	private int boardSize = 15;
	private int size;
	private Pacman pacman;
	private Ghost ghost1, ghost2, ghost3, ghost4, ghost5;
	private JFrame frame;
	private JLabel[][] board;
	private int[][] boardInformation;
	private boolean dying = false;
	ImageIcon ghost = new ImageIcon("src/Images/Ghost1.gif");
	ImageIcon pacman1 = new ImageIcon("src/Images/PacMan1.gif");

	ImageIcon pacman2up = new ImageIcon("src/Images/PacMan2up.gif");

	ImageIcon pacman2left = new ImageIcon("src/Images/PacMan2left.gif");
	ImageIcon pacman2right = new ImageIcon("src/Images/PacMan2right.gif");
	ImageIcon pacman2down = new ImageIcon("src/Images/PacMan2down.gif");
	ImageIcon pacman3up = new ImageIcon("src/Images/PacMan3up.gif");
	ImageIcon pacman3right = new ImageIcon("src/Images/PacMan3right.gif");
	ImageIcon pacman3left = new ImageIcon("src/Images/PacMan3left.gif");
	ImageIcon pacman3down = new ImageIcon("src/Images/PacMan3down.gif");
	ImageIcon pacman4up = new ImageIcon("src/Images/PacMan4up.gif");
	ImageIcon pacman4right = new ImageIcon("src/Images/PacMan4right.gif");
	ImageIcon pacman4left = new ImageIcon("src/Images/PacMan4left.gif");
	ImageIcon pacman4down = new ImageIcon("src/Images/PacMan4down.gif");
	ImageIcon dots = new ImageIcon("src/Images/dots.jpg");


	//private Graphics2D theG;



	public BoardGraphics() {
		//sets size of the board to be 50 spaces
		this.size = 50;
		pacman = new Pacman(pacman1, 24, 24, 0, 0); //pacman starts at 20,20 moving right
		ghost1 = new Ghost(ghost,47, 3, 0, 1); 
		ghost2 = new Ghost(ghost, 4, 45, 0, -1); 
		ghost3 = new Ghost(ghost,47, 47, 0, -1); 
		ghost4 = new Ghost(ghost, 3, 1, 1, 0); 
		ghost5 = new Ghost(ghost, 47, 45, -1, 0);

		//sets up the frame and each of the 50 spaces that the snake can be in
		frame = new JFrame("Pacman");
		frame.setLocation(0,  0);
		frame.setSize(boardSize*size+10, boardSize*size+20);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//listen to keys typed
		frame.setFocusable(true);

		frame.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {			}

			@Override
			public void keyReleased(KeyEvent e) {			}

			@Override
			public void keyTyped(KeyEvent e) {
				char typedChar = e.getKeyChar();
				if (typedChar == 'a') {
					pacman.directionX = -1;
					pacman.directionY = 0;
				}
				if (typedChar == 'd') {
					pacman.directionX = 1;
					pacman.directionY = 0;
				}
				if (typedChar == 'w') {
					pacman.directionX = 0;
					pacman.directionY = -1;
				}
				if (typedChar == 's') {
					pacman.directionX = 0;
					pacman.directionY = 1;
				}
			}
		});



//	public void drawSquares() {
//		for (int i = 0; i < board.length; i++) {
//			for (int y = 0; y < board[i].length; y++) {
//				switch (boardInformation[i][y]) {
//				case WALL:
//					theG.setColor(Color.BLUE);
//					theG.fillRect(y * boardSize, i * boardSize, boardSize, boardSize);
//					break;
//
//				case FREE:
//					drawBlackSquare(i, y);
//					break;
//
//				case DOT:
//					drawBlackSquare(i, y);
//					theG.setColor(Color.WHITE);
//					theG.fillOval(y * boardSize + 5, i * boardSize + 7, 5, 5);
//					break;
//
//				case ENERGIZER:		
//					drawBlackSquare(i, y);
//					theG.setColor(Color.WHITE);
//					theG.fillOval(y * boardSize + 5, i * boardSize + 7, 12, 12);
//					break;
//
//				case PACMAN:
//				drawBlackSquare(i, y);
//					theG.setColor(Color.YELLOW);
//					theG.fillOval(y * boardSize, i * boardSize, 8, 8);
//					break;
//
//				}
//			}
//
//
//		}
//
//
//
//		private void drawBlackSquare(int x, int y) {
//			theG.setColor(Color.BLACK);
//			theG.fillRect(y * boardSize, x * boardSize, boardSize, boardSize);
//		}


		//setup board
		board = new JLabel[size][size];
		boardInformation = new int[size][size];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = new JLabel(" ");
				board[i][j].setSize(22,22); //TODO might need to change size of each grid
				board[i][j].setLocation(i*22, j*22);
				board[i][j].setBackground(Color.YELLOW);
				board[i][j].setOpaque(true);
				board[i][j].setIcon(dots);
				boardInformation[i][j] = FREE;
				frame.add(board[i][j]);
			}
		}


		//sets up the "pacman" on the board

		board[pacman.x][pacman.y].setIcon(pacman1);


		//create ghost(s)
		board[ghost1.x][ghost1.y].setIcon(ghost);
		board[ghost2.x][ghost2.y].setIcon(ghost);
		board[ghost3.x][ghost3.y].setIcon(ghost);
		board[ghost4.x][ghost4.y].setIcon(ghost);
		board[ghost5.x][ghost5.y].setIcon(ghost);

		//		place random wall
		//		Random random = new Random();
		//		int wallx = random.nextInt(size);
		//		int wally = random.nextInt(size);
		//		while (board[wallx][wally].getBackground() == Color.BLUE) {
		//			wallx = random.nextInt(size);
		//			wally = random.nextInt(size);
		//		}
		//		board[wallx][wally].setBackground(Color.BLACK);

		//place walls around board


		//		Color Color.BLUE = new Color (0, 15, 159);  //creates your new color




		for (int j = 0; j < board.length; j++) {
			board[0][j].setIcon(null);
			board[0][j].setBackground(Color.blue);
			board[size -1][j].setBackground(Color.blue);
		}
		for (int i = 0; i < board.length; i++) {
			board[i][0].setIcon(null);
			board[i][0].setBackground(Color.blue);
			board[i][size -1].setBackground(Color.blue);
		}

		for (int q =23; q<26;q++) {
			board[22][q].setIcon(null);
			board[22][q].setBackground(Color.BLUE);
			boardInformation[22][q] = WALL;
		}
		for (int q =23; q<26;q++) {
			board[27][q].setIcon(null);
			board[27][q].setBackground(Color.BLUE);
			boardInformation[0][q] = WALL;
		}

		for (int q =22; q<28;q++) {

			board[q][26].setBackground(Color.BLUE);
			board[q][26].setIcon(null);
			boardInformation[0][q] = WALL;
		}
		for (int q =22; q<24;q++) {

			board[q][22].setBackground(Color.BLUE);
			board[q][22].setIcon(null);
		}
		for (int q =26; q<28;q++) {

			board[q][22].setBackground(Color.BLUE);
			board[q][22].setIcon(null);
		}
		for (int q =0; q<10;q++) {
			board[24][q].setIcon(null);
			board[24][q].setBackground(Color.BLUE);
		}
		for (int q =0; q<10;q++) {
			board[25][q].setIcon(null);
			board[25][q].setBackground(Color.BLUE);
		}
		for (int q =0; q<10;q++) {
			board[26][q].setIcon(null);
			board[26][q].setBackground(Color.BLUE);
		}
		for (int q =0; q<5;q++) {
			board[q][18].setIcon(null);
			board[q][18].setBackground(Color.BLUE);
		}
		for (int q =0; q<5;q++) {
			board[q][19].setIcon(null);
			board[q][19].setBackground(Color.BLUE);
		}
		for (int q =0; q<5;q++) {
			board[q][20].setIcon(null);
			board[q][20].setBackground(Color.BLUE);
		}
		for (int q =0; q<5;q++) {
			board[q][21].setIcon(null);
			board[q][21].setBackground(Color.BLUE);
		}
		for (int q =0; q<5;q++) {
			board[q][22].setIcon(null);
			board[q][22].setBackground(Color.BLUE);
		}
		for (int q =0; q<5;q++) {
			board[q][26].setIcon(null);
			board[q][26].setBackground(Color.BLUE);
		}
		for (int q =0; q<5;q++) {
			board[q][27].setIcon(null);
			board[q][27].setBackground(Color.BLUE);
		}
		for (int q =0; q<5;q++) {
			board[q][28].setIcon(null);
			board[q][28].setBackground(Color.BLUE);
		}
		for (int q =0; q<5;q++) {
			board[q][29].setIcon(null);
			board[q][29].setBackground(Color.BLUE);
		}
		for (int q =0; q<5;q++) {
			board[q][30].setIcon(null);
			board[q][30].setBackground(Color.BLUE);
		}
		for (int q =45; q<50;q++) {
			board[q][18].setIcon(null);
			board[q][18].setBackground(Color.BLUE);
		}
		for (int q =45; q<50;q++) {
			board[q][19].setIcon(null);
			board[q][19].setBackground(Color.BLUE);
		}
		for (int q =45; q<50;q++) {
			board[q][20].setIcon(null);
			board[q][20].setBackground(Color.BLUE);
		}
		for (int q =45; q<50;q++) {
			board[q][21].setIcon(null);
			board[q][21].setBackground(Color.BLUE);
		}
		for (int q =45; q<50;q++) {
			board[q][22].setIcon(null);
			board[q][22].setBackground(Color.BLUE);
		}
		for (int q =45; q<50;q++) {
			board[q][26].setIcon(null);
			board[q][26].setBackground(Color.BLUE);
		}
		for (int q =45; q<50;q++) {
			board[q][27].setIcon(null);
			board[q][27].setBackground(Color.BLUE);
		}
		for (int q =45; q<50;q++) {
			board[q][28].setIcon(null);
			board[q][28].setBackground(Color.BLUE);
		}
		for (int q =45; q<50;q++) {
			board[q][29].setIcon(null);
			board[q][29].setBackground(Color.BLUE);
		}
		for (int q =45; q<50;q++) {
			board[q][30].setIcon(null);
			board[q][30].setBackground(Color.BLUE);
		}
		for (int q =12; q<24;q++) {
			board[18][q].setIcon(null);
			board[18][q].setBackground(Color.BLUE);
		}
		for (int q =12; q<24;q++) {
			board[30][q].setIcon(null);
			board[30][q].setBackground(Color.BLUE);
		}
		for (int q =19; q<22;q++) {
			board[q][16].setIcon(null);
			board[q][16].setBackground(Color.BLUE);
		}
		for (int q =27; q<30;q++) {
			board[q][16].setIcon(null);
			board[q][16].setBackground(Color.BLUE);
		}
		for (int q =21; q<28;q++) {
			board[q][13].setIcon(null);
			board[q][13].setBackground(Color.BLUE);
		}
		for (int q =13; q<20;q++) {
			board[24][q].setIcon(null);
			board[24][q].setBackground(Color.BLUE);
		}
		for (int q =26; q<33;q++) {
			board[18][q].setIcon(null);
			board[18][q].setBackground(Color.BLUE);
		}
		for (int q =26; q<33;q++) {
			board[30][q].setIcon(null);
			board[30][q].setBackground(Color.BLUE);
		}
		for (int q =22; q<27;q++) {
			board[q][35].setIcon(null);
			board[q][35].setBackground(Color.BLUE);
		}
		for (int q =29; q<35;q++) {
			board[24][q].setIcon(null);
			board[24][q].setBackground(Color.BLUE);
		}
		for (int q =4; q<12;q++) {
			board[q][3].setIcon(null);
			board[q][3].setBackground(Color.BLUE);
		}
		for (int q =4; q<12;q++) {
			board[q][10].setIcon(null);
			board[q][10].setBackground(Color.BLUE);
		}
		for (int q =14; q<22;q++) {
			board[q][3].setIcon(null);
			board[q][3].setBackground(Color.BLUE);
		}
		for (int q =14; q<22;q++) {
			board[q][10].setIcon(null);
			board[q][10].setBackground(Color.BLUE);
		}
		for (int q =10; q<16;q++) {
			board[q][6].setIcon(null);
			board[q][6].setBackground(Color.BLUE);
		}
		for (int q =28; q<36;q++) {
			board[q][3].setIcon(null);
			board[q][3].setBackground(Color.BLUE);
		}
		for (int q =38; q<46;q++) {
			board[q][3].setIcon(null);
			board[q][3].setBackground(Color.BLUE);
		}
		for (int q =28; q<36;q++) {
			board[q][10].setIcon(null);
			board[q][10].setBackground(Color.BLUE);
		}
		for (int q =38; q<46;q++) {
			board[q][10].setIcon(null);
			board[q][10].setBackground(Color.BLUE);
		}
		for (int q =34; q<40;q++) {
			board[q][6].setIcon(null);
			board[q][6].setBackground(Color.BLUE);
		}
		for (int q =40; q<50;q++) {
			board[24][q].setIcon(null);
			board[24][q].setBackground(Color.BLUE);
		}
		for (int q =40; q<50;q++) {
			board[25][q].setIcon(null);
			board[25][q].setBackground(Color.BLUE);
		}
		for (int q =40; q<50;q++) {
			board[26][q].setIcon(null);
			board[26][q].setBackground(Color.BLUE);
		}
		for (int q =4; q<12;q++) {
			board[q][40].setIcon(null);
			board[q][40].setBackground(Color.BLUE);
		}
		for (int q =4; q<12;q++) {
			board[q][47].setIcon(null);
			board[q][47].setBackground(Color.BLUE);
		}
		for (int q =14; q<22;q++) {
			board[q][40].setIcon(null);
			board[q][40].setBackground(Color.BLUE);
		}
		for (int q =14; q<22;q++) {
			board[q][47].setIcon(null);
			board[q][47].setBackground(Color.BLUE);
		}
		for (int q =10; q<16;q++) {
			board[q][44].setIcon(null);
			board[q][44].setBackground(Color.BLUE);
		}
		for (int q =28; q<36;q++) {
			board[q][40].setIcon(null);
			board[q][40].setBackground(Color.BLUE);
		}
		for (int q =38; q<46;q++) {
			board[q][40].setIcon(null);
			board[q][40].setBackground(Color.BLUE);
		}
		for (int q =28; q<36;q++) {
			board[q][47].setIcon(null);
			board[q][47].setBackground(Color.BLUE);
		}
		for (int q =38; q<46;q++) {
			board[q][47].setIcon(null);
			board[q][47].setBackground(Color.BLUE);
		}
		for (int q =34; q<40;q++) {
			board[q][44].setIcon(null);
			board[q][44].setBackground(Color.BLUE);
		}
		for (int q =14; q<20;q++) {
			board[9][q].setIcon(null);
			board[9][q].setBackground(Color.BLUE);
		}
		for (int q =14; q<20;q++) {
			board[13][q].setIcon(null);
			board[13][q].setBackground(Color.BLUE);
		}
		for (int q =9; q<13;q++) {
			board[q][14].setIcon(null);
			board[q][14].setBackground(Color.BLUE);
		}
		for (int q =9; q<14;q++) {
			board[q][20].setIcon(null);
			board[q][20].setBackground(Color.BLUE);
		}
		for (int q =22; q<28;q++) {
			board[9][q].setIcon(null);
			board[9][q].setBackground(Color.BLUE);
		}
		for (int q =22; q<28;q++) {
			board[13][q].setIcon(null);
			board[13][q].setBackground(Color.BLUE);
		}
		for (int q =9; q<13;q++) {
			board[q][22].setIcon(null);
			board[q][22].setBackground(Color.BLUE);
		}
		for (int q =9; q<14;q++) {
			board[q][28].setIcon(null);
			board[q][28].setBackground(Color.BLUE);
		}
		for (int q =30; q<36;q++) {
			board[9][q].setIcon(null);
			board[9][q].setBackground(Color.BLUE);
		}
		for (int q =30; q<36;q++) {
			board[13][q].setIcon(null);
			board[13][q].setBackground(Color.BLUE);
		}
		for (int q =9; q<13;q++) {
			board[q][30].setIcon(null);
			board[q][30].setBackground(Color.BLUE);
		}
		for (int q =9; q<14;q++) {
			board[q][36].setIcon(null);
			board[q][36].setBackground(Color.BLUE);
		}
		for (int q =14; q<20;q++) {
			board[36][q].setIcon(null);
			board[36][q].setBackground(Color.BLUE);
		}
		for (int q =14; q<20;q++) {
			board[40][q].setIcon(null);
			board[40][q].setBackground(Color.BLUE);
		}
		for (int q =36; q<40;q++) {
			board[q][14].setIcon(null);
			board[q][14].setBackground(Color.BLUE);
		}
		for (int q =36; q<41;q++) {
			board[q][20].setIcon(null);
			board[q][20].setBackground(Color.BLUE);
		}
		for (int q =22; q<28;q++) {
			board[36][q].setIcon(null);
			board[36][q].setBackground(Color.BLUE);
		}
		for (int q =22; q<28;q++) {
			board[40][q].setIcon(null);
			board[40][q].setBackground(Color.BLUE);
		}
		for (int q =36; q<40;q++) {
			board[q][22].setIcon(null);
			board[q][22].setBackground(Color.BLUE);
		}
		for (int q =36; q<41;q++) {
			board[q][28].setIcon(null);
			board[q][28].setBackground(Color.BLUE);
		}
		for (int q =30; q<36;q++) {
			board[36][q].setIcon(null);
			board[36][q].setBackground(Color.BLUE);
		}
		for (int q =30; q<36;q++) {
			board[40][q].setIcon(null);
			board[40][q].setBackground(Color.BLUE);
		}
		for (int q =36; q<40;q++) {
			board[q][30].setIcon(null);
			board[q][30].setBackground(Color.BLUE);
		}
		for (int q =36; q<41;q++) {
			board[q][36].setIcon(null);
			board[q][36].setBackground(Color.BLUE);
		}
		

		//ADD MORE WALLS

				frame.validate();
				frame.repaint();


		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		} //wait one second for everything to draw

		//infinite loop that moves every object
		move();
	}




	public void move() {
		while (true) {
			try {
				Thread.sleep(100);//sleep for that many milliseconds
				if (dying) {
					break;
				} else {
					pacman.move(board, ghost);
					ghost1.move(board, ghost, dots);
					ghost2.move(board, ghost, dots);
					ghost3.move(board, ghost, dots);
					ghost4.move(board, ghost, dots);
					ghost5.move(board, ghost, dots);


				}	} catch (InterruptedException e) {
					e.printStackTrace();
				}
		}

	}
}


