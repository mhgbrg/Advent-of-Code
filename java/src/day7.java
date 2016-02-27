import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by mats on 08/12/15.
 */
public class day7 {
    private static Map<String, Long> wireValues = new HashMap<>();

    public static void main(String[] args) {
        try {
            List<String> instructions = Files.readAllLines(Paths.get("input/day7.txt"));
            doCalculations(instructions);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void doCalculations(List<String> instructions) {
        wireValues.put("b", 46065L);

        while (!wireValues.containsKey("a")) {
            for (String instruction : instructions) {
                String[] parts = instruction.split(" ");

                String receiver = parts[parts.length - 1];
                if (!wireValues.containsKey(receiver)) {
                    long value = -1;

                    if (parts.length == 3) { // assignment
                        String p = parts[0];
                        value = assign(p);
                    } else if (parts.length == 4) { // NOT
                        String p = parts[1];
                        value = not(p);
                    } else if (parts.length == 5) {
                        String operation = parts[1];
                        String p1 = parts[0];
                        String p2 = parts[2];

                        switch (operation) {
                            case "AND":
                                value = and(p1, p2);
                                break;
                            case "OR":
                                value = or(p1, p2);
                                break;
                            case "LSHIFT":
                                value = lshift(p1, p2);
                                break;
                            case "RSHIFT":
                                value = rshift(p1, p2);
                                break;
                        }
                    }

                    if (value != -1) {
                        wireValues.put(receiver, value);
                    }
                }
            }
        }
        System.out.println("Value of wire a: " + wireValues.get("a"));
    }

    private static long assign(String p) {
        if (isInt(p)) {
            return Integer.parseInt(p);
        }

        if (wireValues.containsKey(p)) {
            return wireValues.get(p);
        }

        return -1;
    }

    private static long not(String p) {
        if (wireValues.containsKey(p)) {
            return ~wireValues.get(p) & 65535;
        }
        return -1;
    }

    private static long and(String p1, String p2) {
        if (isInt(p1)) {
            if (wireValues.containsKey(p2)) {
                return wireValues.get(p2) & Integer.parseInt(p1);
            }
            return -1;
        }

        if (wireValues.containsKey(p1) && wireValues.containsKey(p2)) {
            return wireValues.get(p1) & wireValues.get(p2);
        }

        return -1;
    }

    private static long or(String p1, String p2) {
        if (wireValues.containsKey(p1) && wireValues.containsKey(p2)) {
            return wireValues.get(p1) | wireValues.get(p2);
        }

        return -1;
    }

    private static long lshift(String p1, String p2) {
        if (wireValues.containsKey(p1)) {
            return wireValues.get(p1) << Integer.parseInt(p2);
        }

        return -1;
    }

    private static long rshift(String p1, String p2) {
        if (wireValues.containsKey(p1)) {
            return wireValues.get(p1) >>> Integer.parseInt(p2);
        }

        return -1;
    }

    private static boolean isInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
