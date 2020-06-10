import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class GameRunner {

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, IOException {

        while (true) {
            if (isArgumentsValid(args)) {
                printMovesInfo(args);

                int computerMove = generateComputerNumber(args.length);

                String key = generateRandomKey();
                System.out.println("HMAC: " + encodeSha256_HMAC(key, String.valueOf(computerMove + 1)));

                int userNumber = userMove(args.length);

                System.out.println("Your move: " + args[userNumber - 1]);
                System.out.println("Computer move: " + args[computerMove]);
                System.out.println(findWinner(computerMove, userNumber - 1, args));
                System.out.println("HMAC key: " + key);
            }
        }
    }

    private static boolean isArgumentsValid(String[] args) {
        if (!isArgumentsCountValid(args)) {
            System.out.println("Wrong count of arguments.");
            return false;
        }
        if (!hasUniqueArgs(args)) {
            System.out.println("Arguments array has duplicates.");
            return false;
        }
        return true;
    }


    private static boolean isArgumentsCountValid(String[] args) {
        return (args.length % 2 != 0) && (args.length >= 3);
    }

    private static boolean hasUniqueArgs(String[] args) {
        Set<String> stringSet = new HashSet<>(Arrays.asList(args));
        return args.length == stringSet.size();
    }

    private static void printMovesInfo(String[] args) {
        System.out.println("######GAME MOVES######");
        for (int i = 0; i < args.length; i++) {
            System.out.println(i + 1 + " - " + args[i]);
        }
        System.out.println(0 + " - exit");

    }

    private static int generateComputerNumber(int argsLength) {
        Random random = new Random();
        return random.nextInt(argsLength);
    }

    private static String encodeSha256_HMAC(String key, String data) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        sha256_HMAC.init(secret_key);
        return Hex.encodeHexString(sha256_HMAC.doFinal(data.getBytes(StandardCharsets.UTF_8)));
    }

    private static String generateRandomKey() {
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[32];
        random.nextBytes(key);
        return Hex.encodeHexString(key);
    }

    private static int userMove(int argsCount) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int userNumber = Integer.parseInt(bufferedReader.readLine());
        if (userNumber == 0) {
            System.exit(1);
        } else if (userNumber > argsCount + 1) {
            System.out.println("wrong number");
        }
        return userNumber;
    }

    private static String findWinner(int computerNumber, int userNumber, String[] args) {
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
