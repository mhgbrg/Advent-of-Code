import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by mats on 05/12/15.
 */
public class day1 {
    public static void main(String[] args) {
        try {
            String input = new String(Files.readAllBytes(Paths.get("input/day1.txt")));
            doCalculations(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void doCalculations(String instructions) {
        long goUp = instructions.chars().filter(c -> c == '(').count();
        long goDown = instructions.chars().filter(c -> c == ')').count();
        long floor = goUp - goDown;
        System.out.println("Went to floor " + floor);

        int onFloor = 0;
        int index = 1;
        for (char instruction : instructions.toCharArray()) {
            if (instruction == '(') {
                onFloor++;
            } else if (instruction == ')') {
                onFloor--;
            }

            if (onFloor == -1) {
                break;
            }

            index++;
        }
        System.out.println("Went to basement on instruction " + index);
    }
}
