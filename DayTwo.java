import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class DayTwo {

    private int minAbsoluteChangeInLevel = 1;
    private int maxAbsoluteChangeInLevel = 3;
    private int safeReportsCount = 0;
    private int safeReportsCountAfterDampener = 0;
    private String filename;

    public DayTwo(String filename) {
        this.filename = filename;
        solvePuzzles();
    }

    public int getFirstExerciseSolution() {
        return this.safeReportsCount;
    }

    private int solvePuzzles() {
        
        try (Scanner scanner = new Scanner(Paths.get(this.filename))) {

            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();
                String[] partsAsStrings = line.split(" ");

                //Convert to int array
                int[] partsAsInt = Arrays.asList(partsAsStrings).stream().mapToInt(Integer::parseInt).toArray();
               
                boolean isRowSafe = checkIfSafe(partsAsInt);

                if (isRowSafe) {
                    this.safeReportsCount++;
                }

                


                
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return this.safeReportsCount;
    }

    private boolean checkIfPositive(int difference) {
        return difference > 0;
    }

    private boolean checkIfSafe(int[] arrayOfNumbers) {
        boolean isThisValidRow = true;
        
        //Look at first difference
        int firstDifference = arrayOfNumbers[1] - arrayOfNumbers[0];
        boolean isPositive = checkIfPositive(firstDifference);
        int absoluteFirstDifference = Math.abs(firstDifference);

        //Check if firts difference is withing range
        if (absoluteFirstDifference < this.minAbsoluteChangeInLevel || absoluteFirstDifference > this.maxAbsoluteChangeInLevel) {
            isThisValidRow = false;
        }

        if (isThisValidRow) {
            for (int i = 2; i < arrayOfNumbers.length; i++ ) {
                int difference = arrayOfNumbers[i] - arrayOfNumbers[i-1];

                //Check max diff criterion
                if (Math.abs(difference) > this.maxAbsoluteChangeInLevel || Math.abs(difference) < this.minAbsoluteChangeInLevel) {
                    isThisValidRow = false;
                    break;
                }

                // Check sign criterion
                if (checkIfPositive(difference) != isPositive) {
                    isThisValidRow = false;
                    break;
                }

            }
        }
        return isThisValidRow;
    }

    
}
