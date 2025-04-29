package src.main.java.com.nmarsucco.adventofcode.days;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;

import src.main.java.com.nmarsucco.adventofcode.Day;
import src.main.java.com.nmarsucco.adventofcode.util.WholeFile;

public class Day9 extends Day {

    public Day9() {
        this(false);
    }

    public Day9(boolean isTestingMode) {
        super(9, isTestingMode);
    }

    @Override
    public Object solvePart1() {
        String line = getLineFromInput();
        List<Integer> lineAsList = convertStringToArrayWithPlaceHolders(line);
        moveFilesToCorrectPositionPart1(lineAsList);
        return calculateChecksum(lineAsList);
    }

    @Override
    public Object solvePart2() {
        String line = getLineFromInput();
        List<Integer> lineAsList = convertStringToArrayWithPlaceHolders(line);
        int numberOfFiles = (line.length() + 1) / 2;
        moveFilesToCorrectPositionPart2(lineAsList, numberOfFiles);
        return calculateChecksum(lineAsList);
    }

    private String getLineFromInput() {
        String line;
        try (BufferedReader br = getNewBufferedReader()) {
            line = br.readLine();
        } catch (Exception e) {
            line = "error";
            System.out.println("Error: " + e.getMessage());
        }
        return line;
    }

    private List<Integer> convertStringToArrayWithPlaceHolders(String line) {
        List<Integer> list = new ArrayList<>(line.length() * 5);
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (isFile(i)) {
                addFiles(list, c, i);
            } else {
                addFreeSpace(list, c, i);
            }
        }
        return list;
    }

    private boolean isFile(int i) {
        return i % 2 == 0;
    }

    private void addFiles(List<Integer> list, char c, int indexOfChar) {
        int value = c - '0';
        int id = indexOfChar / 2;
        for (int i = 0; i < value; i++) {
            list.add(id);
        }
    }

    private void addFreeSpace(List<Integer> list, char c, int indexOfChar) {
        int value = c - '0';
        for (int i = 0; i < value; i++) {
            list.add(null);
        }
    }

    private void moveFilesToCorrectPositionPart1(List<Integer> list) {
        int freeSpace = list.indexOf(null);
        if (freeSpace == -1) {
            return;
        }
        while (freeSpace < list.size()) {
            list.set(freeSpace, list.removeLast());
            while (freeSpace < list.size() && list.get(freeSpace) != null) {
                freeSpace++;
            }
        }
    }

    private void moveFilesToCorrectPositionPart2(List<Integer> list, int numberOfFiles) {

        // At each iteration, we search the first file starting from this
        int searchBlockStartingFrom = list.size() - 1;
        int threshold = Integer.MAX_VALUE;

        for (int i = 0; i < numberOfFiles - 1; i++) {
            OptionalInt endOfLastWholeFile = findIndexOfFirstNonNullAndNotMoved(list, searchBlockStartingFrom,
                    threshold);
            if (endOfLastWholeFile.isPresent()) {
                WholeFile file = findLastWholeFile(list, endOfLastWholeFile.getAsInt());
                OptionalInt indexOfSpace = findFirstSuitableSpace(list, file.length, file.startIndex);
                if (indexOfSpace.isPresent()) {
                    moveWholeFileToPosition(list, file, indexOfSpace.getAsInt());
                }
                searchBlockStartingFrom = file.startIndex - 1;
                threshold = file.id;
            }
            // printList(list);
        }

    }

    private void printList(List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == null) {
                System.out.print('.');
            } else {
                System.out.print(list.get(i));
            }
        }
        System.out.println();
    }

    private WholeFile findLastWholeFile(List<Integer> list, int endOfFile) {
        int id = list.get(endOfFile);
        int i = endOfFile - 1;
        while (i >= 0) {
            if (list.get(i) == null || list.get(i) != id) {
                break;
            }
            i--;
        }
        int startIndex = i + 1;
        return new WholeFile(id, i + 1, endOfFile - startIndex + 1);
    }

    private OptionalInt findIndexOfFirstNonNullAndNotMoved(List<Integer> list, int start, int threshold) {
        while (start >= 0) {
            if (list.get(start) != null && list.get(start) < threshold) {
                return OptionalInt.of(start);
            }
            start--;
        }
        return OptionalInt.empty();
    }

    private OptionalInt findFirstSuitableSpace(List<Integer> list, int spaceRequired, int maxPosition) {
        int currLength = 0;
        for (int i = 0; i < maxPosition; i++) {
            if (list.get(i) != null) {
                currLength = 0;
            } else {
                currLength++;
            }
            if (currLength >= spaceRequired) {
                return OptionalInt.of(i - currLength + 1);
            }
        }
        return OptionalInt.empty();
    }

    private void moveWholeFileToPosition(List<Integer> list, WholeFile file, int freeSpaceIndex) {
        for (int i = freeSpaceIndex; i < (freeSpaceIndex + file.length); i++) {
            list.set(i, file.id);
            list.set(file.startIndex + (i - freeSpaceIndex), null);
        }
    }

    private long calculateChecksum(List<Integer> list) {
        long checksum = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != null) {
                checksum += i * list.get(i);
            }
        }
        return checksum;
    }

}
