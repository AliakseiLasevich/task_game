package game;

import utils.ArgsValidator;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Game {

    public void start(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, IOException {
        if (ArgsValidator.isArgumentsValid(args)) {
            while (true) {
                MovesPrinter.printMovesInfo(args);
                ComputerMove computerMove = new ComputerMove();
                int computerNumber = computerMove.move(args.length);

                int userNumber;
                try {
                    userNumber = UserMove.move(args.length);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    continue;
                }

                MovesPrinter.printSelectedItems(computerNumber, userNumber - 1, args);
                System.out.println(GameLogic.findWinner(computerNumber, userNumber - 1, args));
                System.out.println("HMAC KEY: " + computerMove.getKey());

            }
        }
    }
}
