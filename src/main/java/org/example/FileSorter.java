package org.example;

import java.util.List;

public class FileSorter {

    public static void main(String[] args) {
        if (args.length < 3 || args.length > 4) {
            System.out.println("Использование: java FileSorter <входной_файл> <выходной_файл> <номер_критерия> [номер_слова]");
            System.out.println("Номера критериев:\n1. По алфавиту\n2. По количеству символов в строке\n3. По слову в строке");
            System.exit(1);
        }

        String inputFile = args[0];
        String outputFile = args[1];
        int criterion = Integer.parseInt(args[2]);

        if (criterion == 3 && args.length != 4) {
            System.out.println("Ошибка: Необходим четвертый аргумент для критерия 3 (По слову в строке).");
            System.exit(1);
        }

        int wordIndex = (criterion == 3) ? Integer.parseInt(args[3]) : 0;

        List<String> lines = Utils.readLinesFromFile(inputFile);
        Utils.sortAndSaveToFile(lines, outputFile, criterion, wordIndex);
    }
}
