package game;

public class GameLogic {

    public static String findWinner(int computerNumber, int userNumber, String[] args) {
        if (computerNumber == userNumber) {
            return "Tie!";
        }
        int middle = args.length / 2;
        for (int i = 1; i < middle + 1; i++) {
            int beatenIndex = (userNumber + i) % args.length;
            if (computerNumber == beatenIndex) {
                return "You win!";
            }
        }
        return "You lost!";
    }
}
