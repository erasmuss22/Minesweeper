///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  Minesweeper.java
// File:             MineField.java
// Semester:         Fall 2010
//
// Author:           Erin Rasmussen
// CS Login:         rasmusse
// Lecturer's Name:  Jim Skrentny
// Lab Section:      313
//
//////////////////////////// 80 columns wide //////////////////////////////////


import java.util.Random;
/**
 * This class represents the playing field of Minesweeper.  It is an array of
 * integers and each integer represents a GUI feature based on locations of
 * mines, another feature created by this class.
 *
 * <p>Bugs: none known
 *
 * @author Erin Rasmussen
 */
public class MineField {
	private int[][] mineField;				//array of integers to change content
	private Random ranGen = new Random();	//random # for randomMines
	private int[][] randomMines;			//array of coordinates for mines
	int flags = 0;							//flags, used by middleClick

	/**
	  * This is the default constructor of a minefield when the program starts.
	  *
	  */
	
	public MineField(){
		this.mineField = new int[8][8];
		for (int r = 0; r < this.mineField.length; r++){
			for (int c = 0; c < this.mineField[0].length; c++){
				this.mineField[r][c] = -1;
			}
		}
		this.randomMines = randomMines();
	}

	/**
	  * This is the constructor of a minefield when the user selects a 
	  * new game.
	  *
	  * @param row The amount of rows for the minefield
	  * @param column The amount of columns for the minefield
	  * @param mines The amount of mines for the mineField
	  */
	
	public MineField(int row, int column, int mines){
		this.mineField = new int[row][column];
		for (int r = 0; r < this.mineField.length; r++){
			for (int c = 0; c < this.mineField[0].length; c++){
				this.mineField[r][c] = -1;
			}
		}
		this.randomMines = randomMines(mines);
	}

	/**
	  * This method determines whether or not a square has a mine
	  * or not.
	  *
	  * @param randomMines The coordinates of mines
	  * @param mineField the minefield itself
	  * @param row The row the user has clicked on
	  * @param column The column the user clicked on
	  */
	
	public boolean hasMine(int[][]randomMines, int[][]mineField, int row, 
			int column){
		for (int x = 0; x < randomMines.length; x++) {
			if (row == randomMines[x][0] && column == randomMines[x][1]){
				return true;
			}
		}
		return false;
	}

	/**
	  * This is the constructor changes the value of a square based
	  * on it's content from the GUI.
	  *
	  * @param row The row of the square to be changed
	  * @param column The column of the square to be changed
	  * @param value The value to be changed to
	  */
	
	public void changeContent(int row, int column, int value) {
		this.mineField[row][column] = value;
	}

	/**
	  * This method returns the data of the minefield.
	  *
	  * @return the mineField itself
	  */
	
	public int[][] getMineField(){
		return this.mineField;
	}

	/**
	  * This method returns the data of the mines
	  *
	  * @return the randomMines
	  */
	
	public int[][] getRandomMines(){
		return this.randomMines;
	}

	/**
	  * This method returns the value of a certain square
	  *
	  *@param row The row to be checked
	  *@param column The column to be checked
	  *@param mineField the mineField being checked
	  * @return the value of the square
	  */
	
	public int getFieldStatus (int[][] mineField, int row, int column){
		return mineField[row][column];
	}

	/**
	  * This method returns a list of random mines as the default
	  * constructor.
	  *
	  * @return a list of random mines
	  */
	
	public int[][] randomMines(){
		int[][] mines = new int[10][2];
		int x = 0;
		int y = 0;
		int totalMines = 0;
		boolean done = false;

		while (totalMines <= 10){
			for (int i = 0; i < mines.length; i++){
				x = ranGen.nextInt(8);
				y = ranGen.nextInt(8);
				mines[i][0] = x;
				mines[i][1] = y;
				totalMines++;
			}
		}
		while (!done){

			for (int r = 0; r < mines.length; r++){
				for (int c = r + 1; c < mines.length; c++){
					if (mines[r][0] == mines[c][0] && mines[r][1] == 
						mines[c][1]){
						x = ranGen.nextInt(8);
						y = ranGen.nextInt(8);
						mines[c][0] = x;
						mines[c][1] = y;
						r = 0;
						c = r + 1;
					}
				}
			}
			done = true;
		} 

		return mines;
	}
	
	/**
	  * This method returns a list of random mines based on user
	  * input of a new game
	  *
	  *@param numOfMines The amount of mines the user desires
	  * @return a list of random mines
	  */

	public int[][] randomMines(int numOfMines){
		int[][] mines = new int[numOfMines][2];
		int x = 0;
		int y = 0;
		int totalMines = 0;
		boolean done = false;

		while (totalMines <= numOfMines){
			for (int i = 0; i < mines.length; i++){
				x = ranGen.nextInt(this.getMineField().length);
				y = ranGen.nextInt(this.getMineField()[0].length);
				mines[i][0] = x;
				mines[i][1] = y;
				totalMines++;
			}
		}
		while (!done){

			for (int r = 0; r < mines.length; r++){
				for (int c = r + 1; c < mines.length; c++){
					if (mines[r][0] == mines[c][0] && mines[r][1] == 
						mines[c][1]){
						x = ranGen.nextInt(this.getMineField().length);
						y = ranGen.nextInt(this.getMineField()[0].length);
						mines[c][0] = x;
						mines[c][1] = y;
						r = 0;
						c = r + 1;
					}
				}
			}
			done = true;
		} 

		return mines;
	}
	
	/**
	  * This method returns the amount of surrounding mines based on the
	  * location of a square
	  *
	  *@param randomMines The list of randomMines
	  *@param mineField The mineField
	  *@param row The row to be checked
	  *@param column The column to be checked
	  * @return the amount of mines surrounding a square 0-8
	  */

	public int getSurroundingMines(int[][] randomMines, MineField mineField, 
			int row, int column){
		int mines = 0;
		if (row == 0 && column == 0){
			for (int r = 0; r <= 1; r++){
				for (int c = 0; c <= 1; c++){
					if (mineField.hasMine(mineField.getRandomMines(), 
							mineField.getMineField(), r, c)){
						mines++;
					}
				}
			}
		}
		else if (row == mineField.getMineField().length - 1 && column ==0){
			for (int r = mineField.getMineField().length - 2; r <= 
				mineField.getMineField().length - 1; r++){
				for (int c = 0; c <= 1; c++){
					if (mineField.hasMine(mineField.getRandomMines(), 
							mineField.getMineField(), r, c)){
						mines++;
					}
				}
			}
		}
		else if (row == 0 && column == mineField.getMineField()[0].length - 1){
			for (int r = 0; r <= 1; r++){
				for (int c = mineField.getMineField()[0].length - 2; c <= 
					mineField.getMineField()[0].length - 1; c++){
					if (mineField.hasMine(mineField.getRandomMines(), 
							mineField.getMineField(), r, c)){
						mines++;
					}
				}
			}
		}
		else if (row == mineField.getMineField().length - 1 && column == 
			mineField.getMineField()[0].length - 1){
			for (int r = mineField.getMineField().length - 2; r <= 
				mineField.getMineField().length - 1; r++){
				for (int c = mineField.getMineField()[0].length - 2; c <= 
					mineField.getMineField()[0].length - 1; c++){
					if (mineField.hasMine(mineField.getRandomMines(), 
							mineField.getMineField(), r, c)){
						mines++;
					}
				}
			}
		}
		else if (column == 0){
			for (int r = row - 1; r <= row + 1; r++){
				for (int c = 0; c <= 1; c++){
					if (mineField.hasMine(mineField.getRandomMines(), 
							mineField.getMineField(), r, c)){
						mines++;
					}
				}
			}
		}
		else if (column == mineField.getMineField()[0].length - 1){
			for (int r = row - 1; r <= row + 1; r++){
				for (int c = mineField.getMineField()[0].length - 2; c <= 
					mineField.getMineField()[0].length - 1; c++){
					if (mineField.hasMine(mineField.getRandomMines(), 
							mineField.getMineField(), r, c)){
						mines++;
					}
				}
			}
		}
		else if (row == 0){
			for (int r = 0; r <= 1; r++){
				for (int c = column - 1; c <= column + 1; c++){
					if (mineField.hasMine(mineField.getRandomMines(), 
							mineField.getMineField(), r, c)){
						mines++;
					}
				}
			}
		}
		else if (row == mineField.getMineField().length - 1){
			for (int r = mineField.getMineField().length - 2; r <= 
				mineField.getMineField().length - 1; r++){
				for (int c = column - 1; c <= column + 1; c++){
					if (mineField.hasMine(mineField.getRandomMines(), 
							mineField.getMineField(), r, c)){
						mines++;
					}
				}
			}
		}
		else {
			for (int r = row - 1; r <= row + 1; r++){
				for (int c = column - 1; c <= column + 1; c++){
					if (mineField.hasMine(mineField.getRandomMines(), 
							mineField.getMineField(), r, c)){
						mines++;
					}
				}
			}
		}



		return mines;
	}

	/**
	  * This method performs the middle click function by flooding the field
	  * based on whether or not the amount of flags around matches the square
	  * content.
	  *
	  * @param randomMines The list of randomMines
	  * @param mineField The mineField
	  * @param row The row being checked
	  * @param column The column being checked
	  */

	public void middleClick(int[][] randomMines, MineField mineField, int row, 
			int column){
		if (row == 0 && column == 0){
			for (int r = 0; r <= 1; r++){
				for (int c = 0; c <= 1; c++){
					if (mineField.getFieldStatus(mineField.getMineField(), r, 
							c) == -2){
						flags++;
					}
				}
			}
			if (flags == mineField.getFieldStatus(mineField.getMineField(), 
					row, column)){
				for (int r = 0; r <= 1; r++){
					for (int c = 0; c <= 1; c++){
						if (mineField.getFieldStatus(mineField.getMineField(),
								r, c) != -2 && !mineField.hasMine(randomMines,
										mineField.getMineField(), r, c)){
							mineField.changeContent(r, c, mineField.
									getSurroundingMines(randomMines, mineField, r, c));  
						}
					}
				}
			}
		}
		else if (row == mineField.getMineField().length - 1 && column ==0){
			for (int r = mineField.getMineField().length - 2; r <= mineField.
			getMineField().length - 1; r++){
				for (int c = 0; c <= 1; c++){
					if (mineField.getFieldStatus(mineField.getMineField(), r,
							c) == -2){
						flags++;
					}
				}
			}
			if (flags == mineField.getFieldStatus(mineField.getMineField(), 
					row, column)){
				for (int r = mineField.getMineField().length - 2; r <= 
					mineField.getMineField().length - 1; r++){
					for (int c = 0; c <= 1; c++){
						if (mineField.getFieldStatus(mineField.getMineField(), 
								r, c) != -2 && !mineField.hasMine(randomMines,
										mineField.getMineField(), r, c)){
							mineField.changeContent(r, c, mineField.
									getSurroundingMines(randomMines, 
											mineField, r, c));  
						}
					}
				}
			}
		}
		else if (row == 0 && column == mineField.getMineField()[0].length - 1){
			for (int r = 0; r <= 1; r++){
				for (int c = mineField.getMineField()[0].length - 2; c <= 
					mineField.getMineField()[0].length - 1; c++){
					if (mineField.getFieldStatus(mineField.getMineField(),
							r, c) == -2){
						flags++;
					}
				}
			}
			if (flags == mineField.getFieldStatus(mineField.getMineField(),
					row, column)){
				for (int r = 0; r <= 1; r++){
					for (int c = mineField.getMineField()[0].length - 2; c <=
						mineField.getMineField()[0].length - 1; c++){
						if (mineField.getFieldStatus(mineField.getMineField(),
								r, c) != -2 && !mineField.hasMine(randomMines,
										mineField.getMineField(), r, c)){
							mineField.changeContent(r, c, mineField.getSurroundingMines
									(randomMines, mineField, r, c));  
						}
					}
				}
			}
		}
		else if (row == mineField.getMineField().length - 1 && column == 
			mineField.getMineField()[0].length - 1){
			for (int r = mineField.getMineField().length - 2; r <= mineField.
			getMineField().length - 1; r++){
				for (int c = mineField.getMineField()[0].length - 2; c <= 
					mineField.getMineField()[0].length - 1; c++){
					if (mineField.getFieldStatus(mineField.getMineField(), r,
							c) == -2){
						flags++;
					}
				}
			}
			if (flags == mineField.getFieldStatus(mineField.getMineField(),
					row, column)){
				for (int r = 0; r <= 1; r++){
					for (int c = mineField.getMineField()[0].length - 2; c <=
						mineField.getMineField()[0].length - 1; c++){
						if (mineField.getFieldStatus(mineField.getMineField(),
								r, c) != -2 && !mineField.hasMine(randomMines,
										mineField.getMineField(), r, c)){
							mineField.changeContent(r, c, mineField.
									getSurroundingMines(randomMines, mineField, r, c));  
						}
					}
				}
			}
		}
		else if (column == 0){
			for (int r = row - 1; r <= row + 1; r++){
				for (int c = 0; c <= 1; c++){
					if (mineField.getFieldStatus(mineField.getMineField(), r,
							c) == -2){
						flags++;
					}
				}
			}
			if (flags == mineField.getFieldStatus(mineField.getMineField(),
					row, column)){
				for (int r = row - 1; r <= row + 1; r++){
					for (int c = 0; c <= 1; c++){
						if (mineField.getFieldStatus(mineField.getMineField(),
								r, c) != -2 && !mineField.hasMine(randomMines,
										mineField.getMineField(), r, c)){
							mineField.changeContent(r, c, mineField.
									getSurroundingMines(randomMines, mineField, r, c));  
						}
					}
				}
			}
		}
		else if (column == mineField.getMineField()[0].length - 1){
			for (int r = row - 1; r <= row + 1; r++){
				for (int c = mineField.getMineField()[0].length - 2; c <=
					mineField.getMineField()[0].length - 1; c++){
					if (mineField.getFieldStatus(mineField.getMineField(),
							r, c) == -2){
						flags++;
					}
				}
			}
			if (flags == mineField.getFieldStatus(mineField.getMineField(),
					row, column)){
				for (int r = row - 1; r <= row + 1; r++){
					for (int c = mineField.getMineField()[0].length - 2; c <=
						mineField.getMineField()[0].length - 1; c++){
						if (mineField.getFieldStatus(mineField.getMineField(),
						r, c) != -2 && !mineField.hasMine(randomMines,
						mineField.getMineField(), r, c)){
								mineField.changeContent(r, c, mineField.
								getSurroundingMines(randomMines, mineField, r, c));  
						}
					}
				}
			}
		}
		else if (row == 0){
			for (int r = 0; r <= 1; r++){
				for (int c = column - 1; c <= column + 1; c++){
					if (mineField.getFieldStatus(mineField.getMineField(), r,
							c) == -2){
						flags++;
					}
				}
			}
			if (flags == mineField.getFieldStatus(mineField.getMineField(),
					row, column)){
				for (int r = 0; r <= 1; r++){
					for (int c = column - 1; c <= column + 1; c++){
						if (mineField.getFieldStatus(mineField.getMineField(),
								r, c) != -2 && !mineField.hasMine(randomMines,
										mineField.getMineField(), r, c)){
							mineField.changeContent(r, c, mineField.
									getSurroundingMines(randomMines, mineField,
											r, c));  
						}
					}
				}
			}
		}
		else if (row == mineField.getMineField().length - 1){
			for (int r = mineField.getMineField().length - 2; r <= mineField.
			getMineField().length - 1; r++){
				for (int c = column - 1; c <= column + 1; c++){
					if (mineField.getFieldStatus(mineField.getMineField(), r,
							c) == -2){
						flags++;
					}
				}
			}
			if (flags == mineField.getFieldStatus(mineField.getMineField(),
					row, column)){
				for (int r = mineField.getMineField().length - 2; r <=
					mineField.getMineField().length - 1; r++){
					for (int c = column - 1; c <= column + 1; c++){
						if (mineField.getFieldStatus(mineField.getMineField(),
								r, c) != -2 && !mineField.hasMine(randomMines,
										mineField.getMineField(), r, c)){
							mineField.changeContent(r, c, mineField.
									getSurroundingMines(randomMines, mineField, r, c));  
						}
					}
				}
			}
		}
		else {
			for (int r = row - 1; r <= row + 1; r++){
				for (int c = column - 1; c <= column + 1; c++){
					if (mineField.getFieldStatus(mineField.getMineField(), r,
							c) == -2){
						flags++;
					}
				}
			}
			if (flags == mineField.getFieldStatus(mineField.getMineField(),
					row, column)){
				for (int r = row - 1; r <= row + 1; r++){
					for (int c = column - 1; c <= column + 1; c++){
						if (mineField.getFieldStatus(mineField.getMineField(),
								r, c) != -2 && !mineField.hasMine(randomMines,
										mineField.getMineField(), r, c)){
							mineField.changeContent(r, c, mineField.
									getSurroundingMines(randomMines, mineField,
											r, c));  
						}
					}
				}
			}

		}

		flags = 0;
	}

	/**
	  * This method changes the content of the squares to match the GUI in
	  * the case of a loss.  Incorrect flags are crossed out, and the clicked
	  * mine is highlighted.
	  *
	  * @param randomMines The list of randomMines
	  * @param mineField The mineField
	  */
	
	public void showAllLose(int[][] randomMines, MineField mineField){
		for (int r = 0; r < mineField.getMineField().length; r++){
			for (int c = 0; c < mineField.getMineField()[0].length; c++){
				if (mineField.hasMine(mineField.getRandomMines(), mineField.
						getMineField(),
						r, c)){
					if (mineField.getFieldStatus(mineField.getMineField(), r,
							c) != - 2)
						mineField.changeContent(r, c, -5);
				}
				else {
					if (mineField.getFieldStatus(mineField.getMineField(), r,
							c) == -2) {
						mineField.changeContent(r, c, -3);
					}
					else {mineField.changeContent(r, c, mineField.
							getSurroundingMines(mineField.getRandomMines(),
									mineField, r, c));
					}
				}
			}
		}
	}
	
	/**
	  * This method changes the content of the squares to match the GUI in
	  * the case of a win. All flags are left showing.
	  *
	  * @param randomMines The list of randomMines
	  * @param mineField The mineField
	  */
	
	public void showAllWin(int[][] randomMines, MineField mineField){
		for (int r = 0; r < mineField.getMineField().length; r++){
			for (int c = 0; c < mineField.getMineField()[0].length; c++){
				if (mineField.hasMine(mineField.getRandomMines(), mineField.
						getMineField(),
						r, c)){
					mineField.changeContent(r, c, -2);
				}
				else {
					mineField.changeContent(r, c, mineField.
							getSurroundingMines(mineField.getRandomMines(),
									mineField, r, c));
				}
			}
		}
	}
}
