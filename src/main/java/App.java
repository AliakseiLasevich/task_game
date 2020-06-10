import game.Game;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class App {
    public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, IOException {
        Game game = new Game();
        game.start(args);
    }
}
