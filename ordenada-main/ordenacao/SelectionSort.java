package ordenacao;

public class SelectionSort {

    // Método para ordenar um array usando o Selection Sort
    public static void selectionSort(int[] arr) {
        int n = arr.length;

        // Percorre todos os elementos do array
        for (int i = 0; i < n - 1; i++) {
            // Encontra o menor elemento na parte não ordenada
            int minIndex = i;
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

    // Método para imprimir o array
    public static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    // Método principal
    public static void main(String[] args) {
        int[] arr = {64, 25, 12, 22, 11};
        System.out.println("Array original:");
        printArray(arr);

        selectionSort(arr);

        System.out.println("Array ordenado:");
        printArray(arr);
    }
}
