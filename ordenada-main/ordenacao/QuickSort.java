package ordenacao;

public class QuickSort {

    // Método para realizar o Quick Sort
    public static void quickSort(int[] array, int low, int high) {
        // Se a posição inicial for menor que a final, significa que ainda há elementos para ordenar
        if (low < high) {
            // Particiona o array e obtém o índice do pivô
            int pivotIndex = partition(array, low, high);

            // Recursivamente ordena as duas metades ao redor do pivô
            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }
    }

    // Método de partição: organiza o array de forma que elementos menores que o pivô fiquem à esquerda
    // e os maiores à direita
    private static int partition(int[] array, int low, int high) {
        int pivot = array[high]; // Escolhe o último elemento como pivô
        int i = low - 1; // Índice do menor elemento

        for (int j = low; j < high; j++) {
            // Se o elemento atual é menor ou igual ao pivô
            if (array[j] <= pivot) {
                i++; // Incrementa o índice do menor elemento
                swap(array, i, j); // Troca os elementos
            }
        }
        // Troca o pivô para a posição correta
        swap(array, i + 1, high);
        return i + 1; // Retorna a posição do pivô
    }

    // Método para trocar elementos no array
    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
