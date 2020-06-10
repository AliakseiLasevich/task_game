package game;

public class MovesPrinter {

    public static void printMovesInfo(String[] args) {
        System.out.println("######GAME MOVES######");
        for (int i = 0; i < args.length; i++) {
            System.out.println(i + 1 + " - " + args[i]);
        }
        System.out.println(0 + " - exit");
    }


    public static void printSelectedItems(int computerNumber, int userNumber, String[] args) {
        System.out.println("Your move: " + args[userNumber]);
        System.out.println("Computer move: " + args[computerNumber]);
    }
}
