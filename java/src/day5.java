import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by mats on 07/12/15.
 */
public class day5 {
    public static void main(String[] args) {
        try {
            List<String> input = Files.readAllLines(Paths.get("input/day5.txt"));
            doCalculations(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void doCalculations(List<String> input) {
        //Pattern p1 = Pattern.compile("(a|e|i|o|u).*(a|e|i|o|u).*(a|e|i|o|u)");
        //Pattern p2 = Pattern.compile("(.)\\1{1}");
        //Pattern p3 = Pattern.compile("(ab|cd|pq|xy)");
        Pattern p1 = Pattern.compile("(..).*\\1");
        Pattern p2 = Pattern.compile("(.).\\1");


        int nice = 0;
        for (String line : input) {
            boolean m1 = p1.matcher(line).find();
            boolean m2 = p2.matcher(line).find();
            if (m1 && m2) {
                nice++;
            }
        }
        System.out.println("Nice strings: " + nice);
    }
}
