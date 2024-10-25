package ordenacao;

public class InsertionSort {
    // Método que implementa o Insertion Sort
    public static void insertionSort(int[] array) {
        int n = array.length;
        
        // Percorre todos os elementos do array
        for (int i = 1; i < n; i++) {
            int key = array[i]; // O elemento a ser inserido na parte ordenada
            int j = i - 1;

            // Move os elementos do array[0..i-1], que são maiores que key,
            // para uma posição à frente de sua posição atual
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key; // Insere o key na posição correta
        }
    }

    // Método principal para testar o algoritmo
    public static void main(String[] args) {
        int[] array = {12, 11, 13, 5, 6}; // Exemplo de array desordenado

        System.out.println("Array original:");
        printArray(array);

        insertionSort(array); // Ordena o array

        System.out.println("Array ordenado:");
        printArray(array);
    }

    // Método auxiliar para imprimir o array
    public static void printArray(int[] array) {
        for (int value : array) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
}
