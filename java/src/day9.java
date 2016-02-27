import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by mats on 10/12/15.
 */
public class day9 {
    public static void main(String[] args) throws IOException {
        List<String> instructions = Files.readAllLines(Paths.get("input/day9.txt"));
        doCalculations(instructions);
    }

    private static void doCalculations(List<String> instructions) {
        Set<String> cities = new HashSet<>();
        Map<Set<String>, Integer> distances = new HashMap<>();

        // parse instructions
        for (String instruction : instructions) {
            String[] parts = instruction.split(" ");
            Set<String> cityPair = new HashSet<>();
            cityPair.add(parts[0]);
            cityPair.add(parts[2]);
            int distance = Integer.parseInt(parts[4]);

            distances.put(cityPair, distance);
            cities.addAll(cityPair);
        }

        // generate all routes
        Set<List<String>> routes = generatePermutations(cities);

        // calculate all route lengths
        Set<Integer> routeLengths = new HashSet<>();
        for (List<String> route : routes) {
            int routeLength = 0;
            for (int i = 0; i < route.size() - 1; i++) {
                Set<String> cityPair = new HashSet<>();
                cityPair.add(route.get(i));
                cityPair.add(route.get(i + 1));
                try {
                    routeLength += distances.get(cityPair);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
            routeLengths.add(routeLength);
        }

        // get shortest and longest route
        int shortestRoute = routeLengths.stream().min(Comparator.naturalOrder()).get();
        int longestRoute = routeLengths.stream().max(Comparator.naturalOrder()).get();
        System.out.println("Shortest route: " + shortestRoute);
        System.out.println("Longest route: " + longestRoute);
    }

    private static <E> Set<List<E>> generatePermutations(Set<E> set) {
        return recursiveGeneratePermutations(new ArrayList<>(), set);
    }

    private static <E> Set<List<E>> recursiveGeneratePermutations(List<E> prefix, Set<E> set) {
        if (set.size() == 0) {
            Set<List<E>> s = new HashSet<>();
            s.add(prefix);
            return s;
        }

        Set<List<E>> permutations = new HashSet<>();
        for (E element : set) {
            List<E> newPrefix = new ArrayList<>(prefix);
            newPrefix.add(element);

            Set<E> newSet = new HashSet<>(set);
            newSet.remove(element);

            permutations.addAll(recursiveGeneratePermutations(newPrefix, newSet));
        }
        return permutations;
    }

    private static <E> void printSet(Set<E> set) {
        for (E entry : set) {
            System.out.println(entry);
        }
    }

    private static <E> void printList(List<E> list) {
        for (E entry : list) {
            System.out.print(entry + " ");
        }
    }
}
