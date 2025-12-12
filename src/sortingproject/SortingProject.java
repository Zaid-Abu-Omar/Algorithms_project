package sortingproject;

import java.util.Random;

public class SortingProject {

    private static final int[] SIZES = {1000, 10000, 50_000, 100_000};
    private static final int MAX_VALUE = 1_000_000;
    private static final Random RAND = new Random();

    enum DatasetType {
        RANDOM,
        SORTED,
        REVERSE,
        FEW_UNIQUE
    }

    private static int[] generateRandomArray(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = 1 + RAND.nextInt(MAX_VALUE);
        }
        return arr;
    }

    private static int[] generateSortedArray(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i + 1;
        }
        return arr;
    }

    private static int[] generateReverseSortedArray(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = n - i;
        }
        return arr;
    }

    private static int[] generateFewUniqueArray(int n) {
        int[] arr = new int[n];
        int[] values = {1, 2, 3, 4, 5};
        for (int i = 0; i < n; i++) {
            arr[i] = values[RAND.nextInt(values.length)];
        }
        return arr;
    }

    private static int[] generateBaseArray(DatasetType type, int n) {
        switch (type) {
            case RANDOM:
                return generateRandomArray(n);
            case SORTED:
                return generateSortedArray(n);
            case REVERSE:
                return generateReverseSortedArray(n);
            case FEW_UNIQUE:
                return generateFewUniqueArray(n);
            default:
                throw new IllegalArgumentException("Unknown dataset type: " + type);
        }
    }

    private static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    private static void mergeSort(int[] arr, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; i++) {
            L[i] = arr[left + i];
        }
        for (int j = 0; j < n2; j++) {
            R[j] = arr[mid + 1 + j];
        }

        int i = 0;
        int j = 0;
        int k = left;

        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }

        while (i < n1) {
            arr[k++] = L[i++];
        }
        while (j < n2) {
            arr[k++] = R[j++];
        }
    }

    private static void heapSort(int[] arr) {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        for (int i = n - 1; i > 0; i--) {
            swap(arr, 0, i);
            heapify(arr, i, 0);
        }
    }

    private static void heapify(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }

        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }

        if (largest != i) {
            swap(arr, i, largest);
            heapify(arr, n, largest);
        }
    }

    private static void quickSort(int[] arr, int low, int high) {
        while (low < high) {
            int pi = partition(arr, low, high);

            int leftSize = pi - low;
            int rightSize = high - pi;

            if (leftSize < rightSize) {
                if (low < pi) {
                    quickSort(arr, low, pi - 1);
                }
                low = pi + 1;
            } else {
                if (pi < high) {
                    quickSort(arr, pi + 1, high);
                }
                high = pi - 1;
            }
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    private static void randomizedQuickSort(int[] arr, int low, int high) {
        while (low < high) {
            int pi = randomizedPartition(arr, low, high);

            int leftSize = pi - low;
            int rightSize = high - pi;

            if (leftSize < rightSize) {
                if (low < pi) {
                    randomizedQuickSort(arr, low, pi - 1);
                }
                low = pi + 1;
            } else {
                if (pi < high) {
                    randomizedQuickSort(arr, pi + 1, high);
                }
                high = pi - 1;
            }
        }
    }

    private static int randomizedPartition(int[] arr, int low, int high) {
        int pivotIndex = low + RAND.nextInt(high - low + 1);
        swap(arr, pivotIndex, high);
        return partition(arr, low, high);
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private static boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) return false;
        }
        return true;
    }

    private static void runAndPrint(DatasetType type, int size, String algName, int algorithmId, int[] base) {
        int[] arr = base.clone();

        long start = System.nanoTime();

        switch (algorithmId) {
            case 1:
                insertionSort(arr);
                break;
            case 2:
                mergeSort(arr, 0, arr.length - 1);
                break;
            case 3:
                heapSort(arr);
                break;
            case 4:
                quickSort(arr, 0, arr.length - 1);
                break;
            case 5:
                randomizedQuickSort(arr, 0, arr.length - 1);
                break;
            default:
                throw new IllegalArgumentException("Unknown algorithm id: " + algorithmId);
        }

        long end = System.nanoTime();
        double ms = (end - start) / 1_000_000.0;

        System.out.printf("%-10s\t%-8d\t%-12s\t%10.3f%n", type, size, algName, ms);
    }

    public static void main(String[] args) {

        System.out.println("Type      \tSize    \tAlgorithm   \tTime(ms)");
        System.out.println("----------------------------------------------------------");

        for (int size : SIZES) {
            for (DatasetType type : DatasetType.values()) {
                int[] base = generateBaseArray(type, size);

                runAndPrint(type, size, "Insertion", 1, base);
                runAndPrint(type, size, "Merge",     2, base);
                runAndPrint(type, size, "Heap",      3, base);
                runAndPrint(type, size, "Quick",     4, base);
                runAndPrint(type, size, "RandQuick", 5, base);
            }
            System.out.println("----------------------------------------------------------");
        }

        System.out.println("Done.");
    }
}
