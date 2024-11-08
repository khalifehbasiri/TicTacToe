import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * A class modelling a tic-tac-toe (noughts and crosses, Xs and Os) game.
 * 
 * @author Lynn Marshall
 * @version November 8, 2012
 * 
 * @author Khalifeh Basiri 101195770
 * @version April 2, 2022
 */

public class TicTacToe implements ActionListener
{
   public static final String PLAYER_X = "X"; // player using "X"
   public static final String PLAYER_O = "O"; // player using "O"
   public static final String EMPTY = " ";  // empty cell
   public static final String TIE = "T"; // game ended in a tie
 
   private String player;   // current player (PLAYER_X or PLAYER_O)

   private String winner;   // winner: PLAYER_X, PLAYER_O, TIE, EMPTY = in progress

   private int numFreeSquares; // number of squares still free
   
   private JButton board[][]; // 3x3 array representing the board
   
   /* A JTextField displays the current progress of the game. */
   private JTextField gameProgress;
   
   /* The New menu item */
   private JMenuItem newGame;
   
   /* The quit menu item */
   private JMenuItem quitGame;
   
   private GamePopUp windowSplash;
   
   /** 
    * Constructs a new Tic-Tac-Toe board.
    */
   public TicTacToe()
   {
      board = new JButton[3][3];
      
      newBoard();
      
      JFrame frame = new JFrame("TicTacToe");
      
      windowSplash = new GamePopUp();
      
      windowSplash.showSplashWindow();
      
      frame.setPreferredSize(new Dimension(334, 175));
      
      frame.setLocationRelativeTo(null);
      Container contentPane = frame.getContentPane(); 
      contentPane.setLayout(new BorderLayout()); // use border layout (default)
      
      frame.setLocationRelativeTo(null);
      
      JMenuBar menubar = new JMenuBar();
      frame.setJMenuBar(menubar); // add menu bar to our frame

      JMenu fileMenu = new JMenu("Options"); // create a menu
      menubar.add(fileMenu); // and add to our menu bar

      newGame = new JMenuItem("New game"); // create a menu item called "New game"
      fileMenu.add(newGame); // and add to our menu

      quitGame = new JMenuItem("Quit game"); // create a menu item called "Quit game"
      fileMenu.add(quitGame); // and add to our menu
      
      // this allows us to use shortcuts (e.g. Ctrl-R and Ctrl-Q)
      final int SHORTCUT_MASK = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask(); // to save typing
      newGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, SHORTCUT_MASK));
      quitGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, SHORTCUT_MASK));
      
      // listen for menu selections
      newGame.addActionListener(this); 
      quitGame.addActionListener(new ActionListener() // create an anonymous inner class
        { // start of anonymous subclass of ActionListener
          // this allows us to put the code for this action here  
            public void actionPerformed(ActionEvent event)
            {
                System.exit(0); // quit
            }
        } // end of anonymous subclass
      ); // end of addActionListener parameter list and statement
      
      // add a label
      JLabel label = new JLabel("Progress: ");
      label.setHorizontalAlignment(JLabel.RIGHT); // right justified
      contentPane.add(label,BorderLayout.WEST); // west side 
      
      // Middle area gameProgress: current state of game
      gameProgress = new JTextField();
      gameProgress.setEditable(false); // we cannot edit this field
      gameProgress.setHorizontalAlignment(JTextField.RIGHT); // right justified
      contentPane.add(gameProgress, BorderLayout.EAST); // east side
      
      /* Update the view to reflect the initial state of the model. */
      gameProgress.setText(toString()); 
      
      // Top Area (buttonPanel): buttons
      
      // The Up, Down and Reset buttons are arranged horizontally in a JPanel
      JPanel buttonPanel = new JPanel();
      buttonPanel.setLayout(new GridLayout(3, 3)); // 3 x 3 grid
      
      for (int i = 0; i < 3; i++) {
         for (int j = 0; j < 3; j++) {
             board[i][j].addActionListener(this);
             buttonPanel.add(board[i][j]);
         }
      }

      /* Place the buttons at the top. */
      contentPane.add(buttonPanel, BorderLayout.NORTH); // north side
     
      // finish setting up the frame
      frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // exit when we hit the "X"
      frame.pack(); // pack everthing into our frame
      frame.setResizable(true); // we can resize it
      frame.setVisible(true); // it's visible
   }
   
   /** This action listener is called when the user clicks on 
    * any of the GUI's buttons. 
    * 
    * @param e - ActionEvent object
    */
    public void actionPerformed(ActionEvent e)
    {
        Object o = e.getSource(); // get the action 
        
        // see if it's a JButton
        if (o instanceof JButton) {
        
            JButton button = (JButton)o;
            
            if (winner==EMPTY) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (button == board[i][j]){
                            board[i][j].setEnabled(false);
                            numFreeSquares--;
                            buttonClicked(board[i][j]);
                            if (haveWinner(i,j)) {
                                winner = player;
                            }else if (numFreeSquares==0) {
                                winner = TIE;
                            }
                        }
                    }
                }
            }
        } else { // it's a JMenuItem
            
            JMenuItem item = (JMenuItem)o;
            
            if (item == newGame) { //new game
                clearBoard();
                if (player==PLAYER_X) 
                    player=PLAYER_O;
                else 
                    player=PLAYER_X;
            }
        }
           
        // update the display
        gameProgress.setText(toString() ); 
        
        if (!(winner.equals(EMPTY))){
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    board[i][j].setEnabled(false);;
                }
            }
        }
        
        if (player==PLAYER_X) 
            player=PLAYER_O;
         else 
            player=PLAYER_X;
   }    

    /**
    * Sets everything up for a new game.  Marks all squares in the Tic Tac Toe board as empty,
    * and indicates no winner yet, 9 free squares and the current player is player X.
    */
   private void newBoard()
   {
      for (int i = 0; i < 3; i++) {
         for (int j = 0; j < 3; j++) {
             board[i][j] = new JButton();
             board[i][j].setText(EMPTY);
             board[i][j].setEnabled(true);
         }
      }
      winner = EMPTY;
      numFreeSquares = 9;
      player = PLAYER_X;     // Player X always has the first turn.
   }
   
   /**
    * resets board and sets everything up for a new game.
    */
   private void clearBoard()
   {
      for (int i = 0; i < 3; i++) {
         for (int j = 0; j < 3; j++) {
             board[i][j].setText(EMPTY);
             board[i][j].setEnabled(true);
         }
      }
      winner = EMPTY;
      numFreeSquares = 9;
      player = PLAYER_X;     // Player X always has the first turn.
   }

   /**
    * Returns true if filling the given square gives us a winner, and false
    * otherwise.
    *
    * @param int row of square just set
    * @param int col of square just set
    * 
    * @return true if we have a winner, false otherwise
    */
   private boolean haveWinner(int row, int col) 
   {
       // unless at least 5 squares have been filled, we don't need to go any further
       // (the earliest we can have a winner is after player X's 3rd move).

       if (numFreeSquares>4) return false;

       // Note: We don't need to check all rows, columns, and diagonals, only those
       // that contain the latest filled square.  We know that we have a winner 
       // if all 3 squares are the same, as they can't all be blank (as the latest
       // filled square is one of them).

       // check row "row"
       if ( board[row][0].getText().equals(board[row][1].getText()) &&
            board[row][0].getText().equals(board[row][2].getText()) ) return true;
       
       // check column "col"
       if ( board[0][col].getText().equals(board[1][col].getText()) &&
            board[0][col].getText().equals(board[2][col].getText()) ) return true;

       // if row=col check one diagonal
       if (row==col)
          if ( board[0][0].getText().equals(board[1][1].getText()) &&
               board[0][0].getText().equals(board[2][2].getText()) ) return true;

       // if row=2-col check other diagonal
       if (row==2-col)
          if ( board[0][2].getText().equals(board[1][1].getText()) &&
               board[0][2].getText().equals(board[2][0].getText()) ) return true;

       // no winner yet
       return false;
   }
    
   /**
    * Returns a string representing the current state of the game.  This should look like
    * a regular tic tac toe board, and be followed by a message if the game is over that says
    * who won (or indicates a tie).
    *
    * @return String representing the tic tac toe game state
    */
    public String toString() 
    {
        String tempWinner = "";
        if (winner == PLAYER_X){
            tempWinner = "X wins";
        }else if(winner == PLAYER_O){
            tempWinner = "O wins";
        }else if(winner == TIE){
            tempWinner = "Tie";
        }else{
            tempWinner = "in progress.";
        }
        return tempWinner;
    }
    
    /**
    * change text on button clicked to current user.
    *
    * @param button - button just clicked 
    */
    public void buttonClicked(JButton button) 
    {       
        if (player == PLAYER_X){
            button.setText(PLAYER_X);
        }else{
            button.setText(PLAYER_O);
        }
    }
}

