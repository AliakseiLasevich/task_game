package utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ArgsValidator {

    public static boolean isArgumentsValid(String[] args) {
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


}
