package ordenacao;

public class InsertionSort {

    // Método que implementa o Insertion Sort
    public static void insertionSort(int[] array) {
        // Percorre cada elemento do array a partir do segundo
        for (int i = 1; i < array.length; i++) {
            int key = array[i]; // Elemento a ser inserido na parte ordenada
            int j = i - 1;

            // Move elementos maiores que a chave uma posição à frente
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            // Insere a chave na posição correta
            array[j + 1] = key;
        }
    }
}
