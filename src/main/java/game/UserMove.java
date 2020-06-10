package game;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserMove {


    public static int move(int argsCount) throws  NumberFormatException, IOException {
        return userInput(argsCount);
    }

    private static int userInput(int argsCount) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int userNumber = 0;
        try {
            userNumber = Integer.parseInt(bufferedReader.readLine());
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Wrong input. Select number in range: 1-" + argsCount + ". Or select 0 to exit.");
        }
        if (userNumber == 0) {
            System.exit(1);
        } else if (userNumber > argsCount || userNumber < 0) {
            throw new IOException("Wrong number. Select number in range 1-" + argsCount);
        }
        return userNumber;
    }

}
