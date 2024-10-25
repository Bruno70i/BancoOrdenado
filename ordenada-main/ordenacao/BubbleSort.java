package ordenacao;

public class BubbleSort {

    // Método para realizar o Bubble Sort
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        boolean swapped;
        
        // Loop para passar por todos os elementos do array
        for (int i = 0; i < n - 1; i++) {
            swapped = false; // Resetar a flag de troca

            // Últimos i elementos já estão na posição correta
            for (int j = 0; j < n - 1 - i; j++) {
                // Trocar se o elemento encontrado é maior que o próximo
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true; // Uma troca ocorreu
                }
            }

            // Se não houve troca, o array já está ordenado
            if (!swapped) {
                break;
            }
        }
    }

    // Método para imprimir o array
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // Método principal
    public static void main(String[] args) {
        int[] arr = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("Array original:");
        printArray(arr);

        bubbleSort(arr);

        System.out.println("Array ordenado:");
        printArray(arr);
        //dd
    }
}

// teste de adicionar