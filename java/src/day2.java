import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by mats on 07/12/15.
 */
public class day2 {
    public static void main(String[] args) {
        try {
            List<String> input = Files.readAllLines(Paths.get("input/day2.txt"));
            doCalculations(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void doCalculations(List<String> input) {
        List<Present> presents = input.stream().map(Present::new).collect(Collectors.toList());
        int paperNeeded = presents.stream().mapToInt(Present::getPaperNeeded).sum();
        int ribbonNeeded = presents.stream().mapToInt(Present::getRibbonNeeded).sum();
        System.out.println("Paper needed: " + paperNeeded);
        System.out.println("Ribbon needed: " + ribbonNeeded);
    }

    private static class Present {
        int l;
        int w;
        int h;

        private Present(String input) {
            int[] dimensions = Arrays.stream(input.split("x")).mapToInt(Integer::parseInt).sorted().toArray();
            this.l = dimensions[0];
            this.w = dimensions[1];
            this.h = dimensions[2];
        }

        public int getPaperNeeded() {
            int area1 = l*w;
            int area2 = w*h;
            int area3 = h*l;
            return 2*area1 + 2*area2 + 2*area3 + Math.min(area1, Math.min(area2, area3));
        }

        public int getRibbonNeeded() {
            return 2*l + 2*w + l*w*h;
        }
    }
}
