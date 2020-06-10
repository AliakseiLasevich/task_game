package game;

import utils.SecurityUtils;

import java.security.InvalidKeyException;

import java.security.NoSuchAlgorithmException;
import java.util.Random;

import static utils.SecurityUtils.encodeSha256_HMAC;

public class ComputerMove {

    private String key;

    public int move(int argsLength) throws InvalidKeyException, NoSuchAlgorithmException {
        int number = generateNumber(argsLength);
        key = SecurityUtils.generateRandomKey();
        System.out.println("HMAC: " + encodeSha256_HMAC(key, String.valueOf(number + 1)));
        return number;
    }

    private int generateNumber(int argsLength) {
        Random random = new Random();
        return random.nextInt(argsLength);
    }

    public String getKey() {
        return key;
    }

}
