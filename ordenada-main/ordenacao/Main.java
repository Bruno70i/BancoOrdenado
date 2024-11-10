package ordenacao;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void runSorting(File file, JTextArea resultArea) throws IOException {
        int[] dadosExemplo = {38, 27, 43, 3, 9, 82, 10, 8, 22, 55};
        int[] dadosArquivo = readValuesFromFile(file.getAbsolutePath());

        if (dadosArquivo.length == 0) {
            resultArea.append("Erro: Os dados do arquivo estão vazios. Verifique o caminho e o conteúdo do arquivo.\n");
            return;
        }

        resultArea.append("------------- Dados do próprio Sistema: -------------\n");
        resultArea.append(Arrays.toString(dadosExemplo) + "\n");
        resultArea.append("------------ Dados do arquivo: ------------\n");
        resultArea.append(Arrays.toString(dadosArquivo) + "\n\n");

        Map<String, Long> executionTimesExample = new HashMap<>();
        Map<String, Long> executionTimesFile = new HashMap<>();

        sortAndMeasureTime(dadosExemplo, dadosArquivo, "Quick Sort", executionTimesExample, executionTimesFile, resultArea);
        sortAndMeasureTime(dadosExemplo, dadosArquivo, "Merge Sort", executionTimesExample, executionTimesFile, resultArea);
        sortAndMeasureTime(dadosExemplo, dadosArquivo, "Insertion Sort", executionTimesExample, executionTimesFile, resultArea);
        sortAndMeasureTime(dadosExemplo, dadosArquivo, "Selection Sort", executionTimesExample, executionTimesFile, resultArea);

        resultArea.append("\n------------- Comparação de desempenho: -------------\n");
        displayFastestAndSlowestSort(executionTimesExample, "Dados do Sistema", resultArea);
        displayFastestAndSlowestSort(executionTimesFile, "Dados do arquivo", resultArea);
    }

    public static void sortAndMeasureTime(int[] dadosExemplo, int[] dadosArquivo, String sortType,
                                          Map<String, Long> executionTimesExample, Map<String, Long> executionTimesFile, JTextArea resultArea) {
        int[] dadosExemploCopy = Arrays.copyOf(dadosExemplo, dadosExemplo.length);
        int[] dadosArquivoCopy = Arrays.copyOf(dadosArquivo, dadosArquivo.length);

        long startTimeExample = System.nanoTime();
        applySort(dadosExemploCopy, sortType);
        long endTimeExample = System.nanoTime();
        executionTimesExample.put(sortType, endTimeExample - startTimeExample);
        resultArea.append("Resultado do " + sortType + " nos Dados do Sistema:\n" + Arrays.toString(dadosExemploCopy) + "\n");
        printExecutionTime("Dados do Sistema", startTimeExample, endTimeExample, resultArea);

        long startTimeFile = System.nanoTime();
        applySort(dadosArquivoCopy, sortType);
        long endTimeFile = System.nanoTime();
        executionTimesFile.put(sortType, endTimeFile - startTimeFile);
        resultArea.append("Resultado do " + sortType + " nos Dados do arquivo:\n" + Arrays.toString(dadosArquivoCopy) + "\n");
        printExecutionTime("Dados do arquivo", startTimeFile, endTimeFile, resultArea);
    }

    public static void applySort(int[] dados, String sortType) {
        switch (sortType) {
            case "Quick Sort":
                QuickSort.quickSort(dados, 0, dados.length - 1);
                break;
            case "Merge Sort":
                MergeSort mergeSort = new MergeSort();
                mergeSort.sort(dados);
                break;
            case "Insertion Sort":
                InsertionSort.insertionSort(dados);
                break;
            case "Selection Sort":
                SelectionSort.selectionSort(dados);
                break;
            default:
                throw new IllegalArgumentException("Tipo de ordenação inválido: " + sortType);
        }
    }

    public static void printExecutionTime(String dataType, long startTime, long endTime, JTextArea resultArea) {
        long durationNano = endTime - startTime;
        double durationSeconds = durationNano / 1_000_000_000.0;
        resultArea.append(String.format("Tempo de execução para %s: %.9f segundos%n", dataType, durationSeconds));
        resultArea.append("----------------------------------------------------\n");
    }

    public static int[] readValuesFromFile(String filePath) {
        ArrayList<Integer> valuesList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine();
            if (line != null) {
                String[] values = line.split(",");
                for (String value : values) {
                    valuesList.add(Integer.parseInt(value.trim()));
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        return valuesList.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void displayFastestAndSlowestSort(Map<String, Long> executionTimes, String dataType, JTextArea resultArea) {
        String fastestSort = null;
        String slowestSort = null;
        long minTime = Long.MAX_VALUE;
        long maxTime = Long.MIN_VALUE;

        for (Map.Entry<String, Long> entry : executionTimes.entrySet()) {
            if (entry.getValue() < minTime) {
                minTime = entry.getValue();
                fastestSort = entry.getKey();
            }
            if (entry.getValue() > maxTime) {
                maxTime = entry.getValue();
                slowestSort = entry.getKey();
            }
        }

        resultArea.append(String.format("Para %s:\n", dataType));
        resultArea.append(String.format("- Ordenação mais rápida: %s com tempo de %.9f segundos%n", fastestSort, minTime / 1_000_000_000.0));
        resultArea.append(String.format("- Ordenação mais lenta: %s com tempo de %.9f segundos%n", slowestSort, maxTime / 1_000_000_000.0));
        resultArea.append("----------------------------------------------------\n");
    }
}

