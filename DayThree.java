import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DayThree {
    private String filename;
    private int sumPuzzleOne = 0;
    private int sumPuzzleTwo = 0;
    private String regexPatternString = "mul\\(\\d+,\\d+\\)";
    private String regexPatternPuzzleTwo = "do\\(\\)|don't\\(\\)";

    public DayThree(String filename) {
        this.filename = filename;
        this.solvePuzzles();
    }

    public int getFirstExerciseSolution() {
        return this.sumPuzzleOne;
    }

    public int getSecondExerciseSolution() {
        return this.sumPuzzleTwo;
    }

    private void solvePuzzles() {


        try (Scanner scanner = new Scanner(Paths.get(this.filename))) {

            // Define regex pattern to search
            Pattern pattern = Pattern.compile(this.regexPatternString);
            Pattern dosAndDont = Pattern.compile(this.regexPatternPuzzleTwo);

            // String where to save the puzzle input
            String line = "";
            
            // Combine all lines into a single one
            while (scanner.hasNextLine()) {
                line += scanner.nextLine();
            }

            // Get a matcher object
            Matcher matcherInstance = pattern.matcher(line);
            Matcher matcherPuzzleTwo = dosAndDont.matcher(line);

            List<Integer> ListIdxDosAndDont = new ArrayList<>();
            while (matcherPuzzleTwo.find()) {
                ListIdxDosAndDont.add(matcherPuzzleTwo.start());
            }

            boolean isToBeAdded = true;
            int indexOfDoAndDont = -1;

            while (matcherInstance.find()) {
                String pairOfNumbers = line.substring(matcherInstance.start()+4,matcherInstance.end()-1);
                String[] numbers = pairOfNumbers.split(",");
                int numberToAdd = Integer.valueOf(numbers[0])*Integer.valueOf(numbers[1]);
                this.sumPuzzleOne += numberToAdd;

                //Puzzle two (All lines have to combined into one)

                //Logic to increment index 
                while (true) {
                    if ((indexOfDoAndDont+1 < ListIdxDosAndDont.size()) && ListIdxDosAndDont.get(indexOfDoAndDont+1) < matcherInstance.start()) {
                        indexOfDoAndDont++;
                    } else {
                        break;
                    }
                }
                  
                if (indexOfDoAndDont >= 0) {
                    if (line.substring(ListIdxDosAndDont.get(indexOfDoAndDont),ListIdxDosAndDont.get(indexOfDoAndDont)+4).equals("do()")) {
                        isToBeAdded = true;
                    } else {
                        isToBeAdded = false;
                    }
                }
                

                if (isToBeAdded) {
                    this.sumPuzzleTwo += numberToAdd;
                }
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
    
}
