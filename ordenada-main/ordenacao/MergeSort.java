package ordenacao;

public class MergeSort {

    // Método principal para ordenar o array usando Merge Sort
    public void sort(int[] array) {
        // Caso base: se o array tem menos de 2 elementos, já está ordenado
        if (array.length < 2) return;

        int mid = array.length / 2; // Calcula o ponto médio para dividir o array
        int[] left = new int[mid];
        int[] right = array.length % 2 == 0 ? new int[mid] : new int[mid + 1];

        // Copia elementos para as sub-arrays esquerda e direita
        for (int i = 0; i < mid; i++) left[i] = array[i];
        for (int i = mid; i < array.length; i++) right[i - mid] = array[i];

        // Chama recursivamente o sort para as sub-arrays
        sort(left);
        sort(right);

        // Mescla as sub-arrays ordenadas
        merge(array, left, right);
    }

    // Função para mesclar duas sub-arrays ordenadas em uma única array
    private void merge(int[] result, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;

        // Compara elementos das duas sub-arrays e insere no array final em ordem
        while (i < left.length && j < right.length) {
            result[k++] = left[i] <= right[j] ? left[i++] : right[j++];
        }

        // Copia quaisquer elementos restantes da sub-array esquerda
        while (i < left.length) result[k++] = left[i++];

        // Copia quaisquer elementos restantes da sub-array direita
        while (j < right.length) result[k++] = right[j++];
    }
}
