import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;

/**
 * Created by mats on 07/12/15.
 */
public class day3 {
    public static void main(String[] args) {
        try {
            String input = new String(Files.readAllBytes(Paths.get("input/day3.txt")));
            doCalculations(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void doCalculations(String input) {
        Santa santa = new Santa();
        Santa roboSanta = new Santa();

        HashSet<House> visitedHouses = new HashSet<>();
        visitedHouses.add(new House(0, 0));

        int index = 0;
        for (char instruction : input.toCharArray()) {
            if (index % 2 == 0) {
                santa.goToNextHouse(instruction);
                visitedHouses.add(santa.getCurrentHouse());
            } else {
                roboSanta.goToNextHouse(instruction);
                visitedHouses.add(roboSanta.getCurrentHouse());
            }
            index++;
        }
        int numberOfHousesVisited = visitedHouses.size();
        System.out.println("Houses visited: " + numberOfHousesVisited);
    }

    private static class Santa {
        private House currentHouse = new House(0, 0);

        public void goToNextHouse(char direction) {
            switch (direction) {
                case '^':
                    this.currentHouse = currentHouse.getNorthNeighbour();
                    break;
                case '>':
                    this.currentHouse = currentHouse.getEastNeighbour();
                    break;
                case 'v':
                    this.currentHouse = currentHouse.getSouthNeighbour();
                    break;
                case '<':
                    this.currentHouse = currentHouse.getWestNeighbour();
                    break;
            }
        }

        public House getCurrentHouse() {
            return this.currentHouse;
        }
    }

    private static class House {
        private int x;
        private int y;

        public House(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public House getNorthNeighbour() {
            return new House(x, y + 1);
        }

        public House getEastNeighbour() {
            return new House(x + 1, y);
        }

        public House getSouthNeighbour() {
            return new House(x, y - 1);
        }

        public House getWestNeighbour() {
            return new House(x - 1, y);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            } else if (obj == null || !obj.getClass().equals(this.getClass())) {
                return false;
            }

            House other = (House) obj;
            return other.x == this.x && other.y == this.y;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }
    }
}
