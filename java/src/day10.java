/**
 * Created by mats on 10/12/15.
 */
public class day10 {
    public static void main(String[] args) {
        String input = "1113222113";

        String result = input;
        for (int i = 0; i < 50; i++) {
            result = lookAndSay(result);
        }

        int length = result.length();
        System.out.println("String length: " + length);
    }

    private static String lookAndSay(String input) {
        StringBuilder builder = new StringBuilder();

        char currentNumber = input.charAt(0);
        int count = 1;
        for (int i = 1; i < input.length(); i++) {
            char c = input.charAt(i);

            if (c == currentNumber) {
                count++;
            } else {
                builder.append(count);
                builder.append(currentNumber);
                currentNumber = c;
                count = 1;
            }
        }

        builder.append(count);
        builder.append(currentNumber);

        return builder.toString();
    }
}
