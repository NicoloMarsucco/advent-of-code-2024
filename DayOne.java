import java.nio.file.Paths;
import java.util.*;
import java.lang.Math;

public class DayOne {
    private List<Integer> firstColumn = new ArrayList<Integer>();
    private List<Integer> secondColumn = new ArrayList<Integer>();
    private Map<Integer,List<Integer>> frequencyCounts = new HashMap<>();
    private int sumOfDistances = 0;
    private int similarityScore = 0;

    public DayOne(String filename) {
        readDay1Text(filename);
    }

    public int getFirstExerciseSolution() {
        sortColumns();
        calculateTotalDistance();
        return this.sumOfDistances;
    }

    public int getSecondExerciseSolution() {
        calculateSimilarityScore();
        return this.similarityScore;
    }

    private void readDay1Text(String filename) {
        try (Scanner scanner = new Scanner(Paths.get(filename))) {
         
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("   ");

                // Put into arrayList for the first puzzle
                int firstLocationId = Integer.valueOf(parts[0]);
                int secondLocationId = Integer.valueOf(parts[1]);
                this.firstColumn.add(firstLocationId);
                this.secondColumn.add(secondLocationId);

                //Put into hashmap for the second puzzle
                
                // First, we check if the hash map has keys for firts and second location id
                if (! this.frequencyCounts.containsKey(firstLocationId)) {
                    this.frequencyCounts.put(firstLocationId,new ArrayList<Integer>() {{
                        add(0);
                        add(0);
                    }});
                }

                if (! this.frequencyCounts.containsKey(secondLocationId)) {
                    this.frequencyCounts.put(secondLocationId,new ArrayList<Integer>() {{
                        add(0);
                        add(0);
                    }});
                }

                // Then, add frequencies
                //First id
                List<Integer> frequenciesFirstId = this.frequencyCounts.get(firstLocationId);
                frequenciesFirstId.set(0,frequenciesFirstId.get(0)+1);
                this.frequencyCounts.put(firstLocationId,frequenciesFirstId);

                //Second id
                List<Integer> frequenciesSecondId = this.frequencyCounts.get(secondLocationId);
                frequenciesSecondId.set(1,frequenciesSecondId.get(1)+1);
                this.frequencyCounts.put(secondLocationId,frequenciesSecondId);

            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void sortColumns() {
        Collections.sort(this.firstColumn);
        Collections.sort(this.secondColumn);
    }

    private void calculateTotalDistance() {
        for (int i = 0; i < this.firstColumn.size(); i++) {
            int distance = Math.abs(this.firstColumn.get(i) - this.secondColumn.get(i));
            this.sumOfDistances += distance;
        }
    }

    private void calculateSimilarityScore() {
        for (Integer key : this.frequencyCounts.keySet()) {
            List<Integer> frequenciesOfKey = this.frequencyCounts.get(key);
            this.similarityScore += key*frequenciesOfKey.get(0)*frequenciesOfKey.get(1);
        }
    }
}