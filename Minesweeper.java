///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            Minesweeper
// Files:            Minesweeper.java, MineField.java, Game.java
// Semester:         Fall 2010
//
// Author:           Erin Rasmussen
// CS Login:         rasmusse
// Lecturer's Name:  Jim Skrentny
// Lab Section:      313
//
//////////////////////////// 80 columns wide //////////////////////////////////

public class Minesweeper {

	public static void main(String[] args) {
		MineField mineField = new MineField();
		MinesweeperGUI gui = new MinesweeperGUI("Minesweeper"); 
		Game game1 = new Game(mineField, gui);
		game1.playGame();

	}
}
