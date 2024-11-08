# Tic-Tac-Toe Game

This is a Java-based Tic-Tac-Toe game with a graphical user interface, implemented using Java Swing. It includes a splash screen and a main game window.

## Game Overview

The Tic-Tac-Toe game allows two players to take turns placing their markers (X and O) on a 3x3 grid. The game ends when one player has three in a row, or all cells are filled without a winner (tie).

## Prerequisites

- Java Development Kit (JDK) installed.
- Ensure `javac` and `jar` commands are in your system's PATH.

## How to Compile and Create an Executable `.jar`

1. Compile the Java files:
   - Open a terminal in the project directory containing `.java` files.
   ```sh
   javac *.java
   ```

2. Create a Manifest File:
   - In the project directory, create a file named `manifest.txt` with the following content:
     ```
     Main-Class: Main
     ```
   - Make sure there is a newline at the end of this line.

3. Create the `.jar` File:
   - Run the following command to package everything, including the splash image:
   ```sh
   jar cfm TicTacToeGame.jar manifest.txt *.class TicTaacToeStartImage.gif
   ```

## Running the Game

Once the `.jar` file is created, you can run the game with the following command:

```sh
java -jar TicTacToeGame.jar
```

Or by double-clicking `TicTacToeGame.jar` if your system is configured to run `.jar` files with Java.

## Files Included

- `TicTacToe.java`: Main game logic and GUI.
- `GamePopUp.java`: Handles the splash screen display.
- `Main.java`: Entry point to start the game.
- `TicTaacToeStartImage.gif`: Image used in the splash screen.

## License

This project was developed by Khalifeh Basiri as part of a learning project.

---

This README includes everything you need to set up, compile, and run the application. Let me know if you need further customization!