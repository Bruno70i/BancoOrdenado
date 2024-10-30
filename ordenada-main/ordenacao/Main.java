package ordenacao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        int[] arrayExemplo = {38, 27, 43, 3, 9, 82, 10, 8, 22, 55};
        int[] arrayArquivo = readValuesFromFile("C:\\Users\\bruno\\Documents\\GitHub\\BancoOrdenado\\ordenada-main\\ordenacao\\valores_externos_0_a_1000.txt");

        if (arrayArquivo.length == 0) {
            System.out.println("Erro: O array do arquivo está vazio. Verifique o caminho e o conteúdo do arquivo.");
            return;
        }

        System.out.println("------------- Dados do próprio Sistema: -------------");
        printArray(arrayExemplo);
        System.out.println("------------ Dados do arquivo: ------------");
        printArray(arrayArquivo);
        System.out.println("");

        Map<String, Long> executionTimesExample = new HashMap<>();
        Map<String, Long> executionTimesFile = new HashMap<>();

        sortAndMeasureTime(arrayExemplo, arrayArquivo, "Quick Sort", executionTimesExample, executionTimesFile);
        sortAndMeasureTime(arrayExemplo, arrayArquivo, "Merge Sort", executionTimesExample, executionTimesFile);
        sortAndMeasureTime(arrayExemplo, arrayArquivo, "Insertion Sort", executionTimesExample, executionTimesFile);
        sortAndMeasureTime(arrayExemplo, arrayArquivo, "Selection Sort", executionTimesExample, executionTimesFile);

        System.out.println("\n------------- Comparação de desempenho: -------------");
        displayFastestAndSlowestSort(executionTimesExample, "Dados do Sistema");
        displayFastestAndSlowestSort(executionTimesFile, "Dados do arquivo");
    }

    public static void sortAndMeasureTime(int[] arrayExemplo, int[] arrayArquivo, String sortType,
                                          Map<String, Long> executionTimesExample, Map<String, Long> executionTimesFile) {
        int[] arrayExemploCopy = Arrays.copyOf(arrayExemplo, arrayExemplo.length);
        int[] arrayArquivoCopy = Arrays.copyOf(arrayArquivo, arrayArquivo.length);

        long startTimeExample = System.nanoTime();
        applySort(arrayExemploCopy, sortType);
        long endTimeExample = System.nanoTime();
        executionTimesExample.put(sortType, endTimeExample - startTimeExample);
        System.out.println("Resultado do " + sortType + " no Dados Sistema:");
        printArray(arrayExemploCopy);
        printExecutionTime("Dados do Sistema", startTimeExample, endTimeExample);

        long startTimeFile = System.nanoTime();
        applySort(arrayArquivoCopy, sortType);
        long endTimeFile = System.nanoTime();
        executionTimesFile.put(sortType, endTimeFile - startTimeFile);
        System.out.println("Resultado do " + sortType + " no Dados do arquivo:");
        printArray(arrayArquivoCopy);
        printExecutionTime("Dados do arquivo", startTimeFile, endTimeFile);
    }

    public static void applySort(int[] array, String sortType) {
        switch (sortType) {
            case "Quick Sort":
                QuickSort.quickSort(array, 0, array.length - 1);
                break;
            case "Merge Sort":
                MergeSort mergeSort = new MergeSort();
                mergeSort.sort(array);
                break;
            case "Insertion Sort":
                InsertionSort.insertionSort(array);
                break;
            case "Selection Sort":
                SelectionSort.selectionSort(array);
                break;
            default:
                throw new IllegalArgumentException("Tipo de ordenação inválido: " + sortType);
        }
    }

    public static void printArray(int[] array) {
        for (int value : array) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    public static void printExecutionTime(String arrayType, long startTime, long endTime) {
        long durationNano = endTime - startTime;
        double durationSeconds = durationNano / 1_000_000_000.0;
        System.out.printf("Tempo de execução para %s: %.9f segundos%n", arrayType, durationSeconds);
        System.out.println("----------------------------------------------------");
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

    // Método para exibir a ordenação mais rápida e mais lenta
    public static void displayFastestAndSlowestSort(Map<String, Long> executionTimes, String arrayType) {
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

        System.out.printf("Para %s:\n", arrayType);
        System.out.printf("- Ordenação mais rápida: %s com tempo de %.9f segundos%n", fastestSort, minTime / 1_000_000_000.0);
        System.out.printf("- Ordenação mais lenta: %s com tempo de %.9f segundos%n", slowestSort, maxTime / 1_000_000_000.0);
        System.out.println("----------------------------------------------------");
    }
}