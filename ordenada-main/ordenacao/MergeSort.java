package ordenacao;
public class MergeSort {

    // Função principal que ordena a lista
    public void sort(int[] array) {
        if (array.length < 2) {
            return; // caso base
        }
        
        int mid = array.length / 2;
        int[] left = new int[mid];
        int[] right;

        // Se o comprimento do array for ímpar, faz uma divisão extra
        if (array.length % 2 == 0) {
            right = new int[mid];
        } else {
            right = new int[mid + 1];
        }

        // Copiando os elementos para as sub-arrays
        for (int i = 0; i < mid; i++) {
            left[i] = array[i];
        }
        for (int i = mid; i < array.length; i++) {
            right[i - mid] = array[i];
        }

        // Ordenando as sub-arrays
        sort(left);
        sort(right);

        // Mesclando as sub-arrays ordenadas
        merge(array, left, right);
    }

    // Função que mescla duas sub-arrays
    private void merge(int[] result, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;

        // Mesclando os elementos
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
        }

        // Copiando os elementos restantes da sub-array esquerda
        while (i < left.length) {
            result[k++] = left[i++];
        }

        // Copiando os elementos restantes da sub-array direita
        while (j < right.length) {
            result[k++] = right[j++];
        }
    }

    // Método para testar a ordenação
    public static void main(String[] args) {
        int[] array = {38, 27, 43, 3, 9, 82, 10};
        MergeSort mergeSort = new MergeSort();
        
        System.out.println("Array original:");
        for (int num : array) {
            System.out.print(num + " ");
        }

        mergeSort.sort(array);

        System.out.println("\nArray ordenado:");
        for (int num : array) {
            System.out.print(num + " ");
        }
    }
}
