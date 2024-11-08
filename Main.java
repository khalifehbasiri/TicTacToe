public class Main {
    public static void main(String[] args) {
        // Create the splash screen and show it
        GamePopUp splash = new GamePopUp();
        splash.showSplashWindow();

        // Now start the Tic-Tac-Toe game after splash
        new TicTacToe();
    }
}
