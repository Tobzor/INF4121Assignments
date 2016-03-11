import java.util.Scanner;

public class Minesweeper {
	private static MineField field;
	private static Ranking rank;	
	public static void main(String[] args) {
		rank=new Ranking();
		mainMessage();
		while(gameCountinue());
		System.out.println("\nThank you for playing :) Have a nice day!");
	}
    private static boolean gameCountinue() {
		field = new MineField();
		Scanner in = new Scanner(System.in);
		int result = 0;
		while (true) {
			field.show();
			System.out.print("\nPlease enter your move(row col): ");
			String input = in.nextLine();
			if (input.equals("top")) {
				rank.show();
				continue;
			}
			if (input.equals("restart")) {
				rank.recordName(result);
				return true;
			}
			if (input.equals("exit")) {
				rank.recordName(result);
				return false;
			}
			if (field.legalMoveString(input)) {
				result++;
				// Removed unecessary curly brackets.
				if (result == 35) {
					System.out.println("Congratulations you WON the game!");
					rank.recordName(result);
					return true;
				}
			// Removed dead code.
			continue;
            }
            else if (field.getBoom()) {
                System.out.println("\nBooooooooooooooooooooooooooooom!You stepped on a mine!You survived " + result + " turns");
                rank.recordName(result);
                return true;
            }
        }
    }
	private static void mainMessage(){
		System.out.println("Welcome to Minesweeper!");
		System.out.println("To play just input some coordinates and try not to step on the mine :)");
		System.out.println("Useful commands:");
		System.out.println("restart - Starts a new game.");
		System.out.println("exit - Quits the game.");
		System.out.println("top - Reveals the top scoreboard.");
		System.out.println("Have Fun!");
	}
}