package ordenacao;

public class QuickSort {

    // Método para realizar o Quick Sort
    public static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            // Particiona o array
            int pivotIndex = partition(array, low, high);

            // Ordena as duas sub-listas
            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }
    }

    // Método de partição
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

    // Método principal para testar o Quick Sort
    public static void main(String[] args) {
        int[] array = {34, 7, 23, 32, 5, 62};
        
        System.out.println("Array original:");
        for (int num : array) {
            System.out.print(num + " ");
        }

        quickSort(array, 0, array.length - 1);

        System.out.println("\nArray ordenado:");
        for (int num : array) {
            System.out.print(num + " ");
        }
    }
}
