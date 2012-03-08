
///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  Minesweeper.java
// File:             Game.java
// Semester:         Fall 2010
//
// Author:           Erin Rasmussen
// CS Login:         rasmusse
// Lecturer's Name:  Jim Skrentny
// Lab Section:      313
//
//////////////////////////// 80 columns wide //////////////////////////////////
/**
  *This class represents a game of Minesweeper.  The user can play a default
  *game, or create a new game with their own specifications.  For this game,
  *both right click and middle click are usable, and the first click doesn't
  *result in a loss.
  *
  * <p>Bugs: creating a new game after loss results in two game windows open
  *
  * @author Erin Rasmussen
  */
import java.util.Scanner;
public class Game {
	private MineField mineField;		//1 mineField of a specified size
	private MinesweeperGUI gui; 		//The GUI of the game
	private boolean gameOver;			//true if game is ended
	private boolean gameWon;			//true if game over and won
	MinesweeperAction action;			//action input from the mouse
	private int flagsLeft;				//amount of flags left, initialized to mines
	private int correct = 0;			//amount of flags covering mines
	private int rows;   //max 40		//rows in the minefield
	private int columns;  //max 80		//columns in the minefield
	private int mines;		//max 500	//mines in the game
	private boolean firstClick;			//true if the game just started
	Scanner in = new Scanner(System.in);


	/**
	  * This is the default constructor of a game when the program starts.
	  *
	  * @param MineField mineField the integer array of fields
	  * @param MinesweeperGUI gui the graphical user interface of the program
	  */
	
	public Game (MineField mineField, MinesweeperGUI gui){
		this. mineField = mineField;
		this.gui = gui;
		this.flagsLeft = 10;
		this.correct = 0;
		this.gameOver = false;
		this.gameWon = true;
		this.firstClick = true;

	}
	/**
	  * This is the constructor for when a user wants a new game
	  *
	  * @param int rows the amount of rows the user desires
	  * @param int columns the amount of columns the user desires
	  * @param int mines the amount of mines the user desires
	  */
	
	public Game (int rows, int columns, int mines){
		this.mineField = new MineField(rows, columns, mines);
		this.gui = new MinesweeperGUI("Minesweeper" + rows + columns);
		this.flagsLeft = mines;
		this.correct = 0;
		this.gameOver = false;
		this.gameWon = true;
		this.firstClick = true;
	}

	/**
	  * This is sets the amount of rows for creating a new game
	  *
	  * @param Scanner in the input device
	  */
	
	public void setRows(Scanner in){
		this.rows = in.nextInt();
	}

	/**
	  * This is sets the amount of columns for creating a new game
	  *
	  * @param Scanner in the input device
	  */
	public void setColumns(Scanner in){
		this.columns = in.nextInt();
	}
	
	/**
	  * This is sets the amount of mines for creating a new game
	  *
	  * @param Scanner in the input device
	  */
	
	public void setMines(Scanner in){
		this.mines = in.nextInt();
		flagsLeft = this.mines;
	}

	/**
	  * This is returns the amount of rows for creating a new game
	  *
	  * @return amount of rows
	  */
	
	public int getRows(){
		return this.rows;
	}
	
	/**
	  * This is returns the amount of columns for creating a new game
	  *
	  * @return amount of columns
	  */
	
	public int getColumns(){
		return this.columns;
	}
	
	/**
	  * This is returns the amount of mines for creating a new game
	  *
	  * @return amount of mines
	  */
	
	public int getMines(){
		return this.mines;
	}

	/**
	  * This method receives valid input for the user to determine the
	  * dimensions of the minefield created in a new game.
	  *
	  * @param Scanner in the keyboard input for numbers
	  */
	
	public void getInput(Scanner in){	

		System.out.println("Enter the amount of rows you would like [5-40] :");
		this.setRows(in);
		if (this.rows > 40){
			throw new IllegalArgumentException("Too many rows!");
		}
		else if (this.rows < 5){
			throw new IllegalArgumentException("Not enough rows!");
		}
		System.out.println("Enter the amount of columns you would like [5-80]" +
				" :");
		this.setColumns(in);
		if (this.columns > 80){
			throw new IllegalArgumentException("Too many columns!");
		}
		else if (this.columns < 5){
			throw new IllegalArgumentException("Not enough columns!");
		}
		System.out.println("Enter the amount of mines you would like [ 5-max:" +
				" rows * columns - 15]:");
		this.setMines(in);
		if (this.mines > (this.columns * this.rows) - 15){
			throw new IllegalArgumentException("Too many mines!");
		}
		else if (this.mines < 5){
			throw new IllegalArgumentException("Not enough mines!");
		}



	}

	/**
	  * This method carries out the algorithm for playing the game
	  * including receiving input from the mouse and updating the GUI.
	  */
	
	public void playGame(){
		boolean quit = false;
		while (!gameOver){
			gui.update(mineField.getMineField(), gameOver, gameWon, flagsLeft);
			action = gui.getMouseInput();
			if (action.ACTION_TYPE == MinesweeperAction.LEFT_CLICK){
				if (firstClick){
					while (mineField.hasMine(mineField.getRandomMines(), 
							mineField.getMineField(),
							action.ROW, action.COLUMN)){
						mineField = new MineField();
					}
					this.firstClick = false;
					gui.update(mineField.getMineField(), gameOver, gameWon,
							flagsLeft);
				}
				if (mineField.hasMine(mineField.getRandomMines(), mineField.
						getMineField(),
						action.ROW, action.COLUMN)){
					mineField.showAllLose(mineField.getRandomMines(), 
							mineField);
					mineField.changeContent(action.ROW,action.COLUMN, -6);
					this.gameWon = false;  
					this.gameOver = true;

				}

				else {
					mineField.changeContent(action.ROW,action.COLUMN, 
							mineField.getSurroundingMines(mineField.
									getRandomMines(),
									mineField, action.ROW, action.COLUMN));
				}
			}
			else if (action.ACTION_TYPE == MinesweeperAction.RIGHT_CLICK){
				if (mineField.getMineField()[action.ROW][action.COLUMN] == -1){
					mineField.changeContent(action.ROW,action.COLUMN, -2);
					flagsLeft--;
				}
				else if (mineField.getMineField()[action.ROW][action.COLUMN] ==
					-2){
					mineField.changeContent(action.ROW,action.COLUMN, -4);
					flagsLeft++;
				}
				else{
					mineField.changeContent(action.ROW,action.COLUMN, -1);
				}
			}
			else if (action.ACTION_TYPE == MinesweeperAction.NEW_GAME){
				boolean done = false;
				while (!done){
					try {
						this.getInput(in);
						done = true;
					}
					catch (IllegalArgumentException e){
						System.out.println("ERROR! " + e.getMessage() + " " +
								"Try again!");
					}
					catch (NullPointerException e){
						System.out.println("Invalid object. Try again:");
					}

				}
				this.mineField = new MineField(this.getRows(), this.
						getColumns(), this.getMines());


				gui.update(mineField.getMineField(), gameOver, gameWon,
						flagsLeft);
			}
			else if (action.ACTION_TYPE == MinesweeperAction.QUIT){
				System.exit(1);
				quit = true;
			}
			else if (action.ACTION_TYPE == MinesweeperAction.MIDDLE_CLICK){
				mineField.middleClick(mineField.getRandomMines(), mineField,
						action.ROW, action.COLUMN);
			}


			for (int r = 0; r < mineField.getMineField().length; r++){
				for (int c = 0; c < mineField.getMineField()[0].length; c++){
					if (mineField.getFieldStatus(mineField.getMineField(), r,
							c) != -1 && !mineField.hasMine(mineField.
									getRandomMines(), mineField.getMineField(),
							r, c) && mineField.getFieldStatus(mineField.
									getMineField(), r, c) != -4 ){
						correct++;
						if (correct == (mineField.getMineField().length * 
								mineField.getMineField()[0].length) - mineField.getRandomMines().length){
							this.gameOver = true;
						}
					}
				}
			}
			correct = 0;
		}
		gui.update(mineField.getMineField(), gameOver, gameWon, flagsLeft);

		while (!quit){
			action = gui.getMouseInput();
			if (action.ACTION_TYPE == MinesweeperAction.NEW_GAME){
				this.getInput(in);
				Game newGame = new Game (this.getRows(), this.getColumns(), 
						this.getMines());
				newGame.playGame();
			}
			else if (action.ACTION_TYPE == MinesweeperAction.QUIT){
				System.exit(1);
				quit = true;
			}
		}
	}

}

