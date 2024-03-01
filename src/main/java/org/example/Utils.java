package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Utils {

    public static List<String> readLinesFromFile(String filename) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }

    public static void sortAndSaveToFile(List<String> lines, String filename, int criterion, int wordIndex) {
        Comparator<String> comparator = switch (criterion) {
            case 1 -> Comparator.naturalOrder();
            case 2 -> Comparator.comparingInt(String::length);
            case 3 -> Comparator.<String, String>comparing(s -> getWordAtIndex(s, wordIndex)).thenComparing(Comparator.naturalOrder());
            default -> throw new IllegalArgumentException("Неверный номер критерия");
        };

        lines.sort(comparator);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (String line : lines) {
                int count = Collections.frequency(lines, line);
                writer.write(line + " " + count + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getWordAtIndex(String line, int index) {
        String[] words = line.split("\\s+");
        if (index >= 1 && index <= words.length) {
            return words[index - 1];
        } else {
            throw new IllegalArgumentException("Неверный номер слова");
        }
    }
}
