import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mats on 08/12/15.
 */
public class day6 {
    private static int[][] lightsMatrix = new int[1000][1000];

    public static void main(String[] args) {
        try {
            List<String> input = Files.readAllLines(Paths.get("input/day6.txt"));
            doCalculations(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void doCalculations(List<String> input) {
        Pattern p1 = Pattern.compile("^.*?(?=\\d)");
        Pattern p2 = Pattern.compile("\\d+,\\d+");

        for (String instruction : input) {
            Matcher m1 = p1.matcher(instruction);
            m1.find();
            String action = m1.group().trim();

            Matcher m2 = p2.matcher(instruction);
            m2.find();
            int[] from = Arrays.stream(m2.group().split(",")).mapToInt(Integer::parseInt).toArray();
            m2.find();
            int[] to = Arrays.stream(m2.group().split(",")).mapToInt(Integer::parseInt).toArray();

            modifyLights(action, from, to);
        }

        int count = Arrays.stream(lightsMatrix).mapToInt(x -> Arrays.stream(x).sum()).sum();
        System.out.println("lights on: " + count);
    }

    private static void modifyLights(String action, int[] from, int[] to) {
        for (int x = from[0]; x <= to[0]; x++) {
            for (int y = from[1]; y <= to[1]; y++) {
                switch (action) {
                    case "turn on":
                        lightsMatrix[x][y]++;
                        break;
                    case "turn off":
                        if (lightsMatrix[x][y] > 0) {
                            lightsMatrix[x][y]--;
                        }
                        break;
                    case "toggle":
                        lightsMatrix[x][y] += 2;
                        break;
                }
            }
        }
    }
}
