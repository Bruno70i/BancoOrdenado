package ordenacao;

public class SelectionSort {

    // Método que implementa o Selection Sort
    public static void selectionSort(int[] arr) {
        int n = arr.length;

        // Percorre cada elemento do array
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i; // Assume o menor elemento como o atual

            // Busca o menor elemento na parte não ordenada do array
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            // Troca o menor elemento encontrado com o primeiro elemento da parte não ordenada
            if (minIndex != i) {
                int temp = arr[minIndex];
                arr[minIndex] = arr[i];
                arr[i] = temp;
            }
        }
    }
}
