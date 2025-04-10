package SortMethods;

import java.util.Arrays;

public enum SortingMethod {
    BUBBLE,
    INSERTION,
    MERGE,
    SELECTION,
    SHELL,
    QUICK;

    public String getMethodName() {
        switch (this) {
            case BUBBLE:
                return "Bubble sort";
        
            case INSERTION:
                return "Insertion sort";
        
            case MERGE:
                return "Merge sort";
        
            case SELECTION:
                return "Selection sort";
        
            case SHELL:
                return "Shell sort";
        
            case QUICK:
                return "Quick sort";
        
            default:
            return "none";
        }
    }

    public void callMethod(int[] list) {
        switch (this) {
            case BUBBLE:
                bubbleSort(list);
                break;
        
            case INSERTION:
                insertionSort(list);
                break;
        
            case MERGE:
                mergeSort(list);
                break;
        
            case SELECTION:
                selectionSort(list);
                break;
        
            case SHELL:
                shellSort(list);
                break;
        
            case QUICK:
                quickSort(list);
                break;
        
            default:
            break;
        }
    }

    // Bubble Sort:
    private static void bubbleSort(int[] list) {
        for (int i = 0; i < list.length - 1; i++) {
            for (int j = 0; j < list.length - i - 1; j++) {
                if (list[j] > list[j + 1]) {
                    int temp = list[j];
                    list[j] = list[j+1];
                    list[j + 1] = temp;
                }
            }
        }
    }

    //Selection Sort:
    private static int[] selectionSort(int[] list) {
        for (int i = list.length - 1; i > 0; i--) {
            int maxIndex = 0;
            for (int j = 0; j < i; j++) {
                if (list[j] > list[maxIndex]) {
                    maxIndex = j;
                }
            }
            if (i != maxIndex) {
                int temp = list[maxIndex];
                list[maxIndex] = list[i];
                list[i] = temp;
            }
        }
        return list;
    }

    //Insertion Sort:
    public static int[] insertionSort(int[] list) {
        for (int i = 0; i < list.length; i++) {
            int value = list[i];
            int position = i;

            while (position > 0 && list[position - 1] > value) {
                list[position] = list[position - 1];
                position--;
            }
            list[position] = value;
            
        }
        return list;
    }

    //Shell Sort:
    private static void shellSort(int[] list) {
        int sublistCount = list.length/2;
        while (sublistCount > 0) {
            for (int startPos = 0; startPos < sublistCount; startPos++) {
                gapInsertionSort(list, startPos, sublistCount);
            }
            sublistCount = sublistCount/2;
        }
    }
    private static void gapInsertionSort(int[] list, int start, int gap) {
        for (int i = start + gap; i < list.length; i += gap) {
            int value = list[i];
            int position = i;
            while (position >= gap && list[position - gap] > value) {
                list[position] = list[position - gap];
                position = position - gap;
            }
            list[position] = value;
        }
    }

    //Merge Sort:
    private static void mergeSort(int[] list){
        int level = 0;
        mergeSort(list, level);
    }
    private static void mergeSort(int[] list, int level) {
        if (list.length > 1) {
            int middle = list.length/2;
            int[] leftHalf = Arrays.copyOfRange(list,0, middle);
            int[] rightHalf = Arrays.copyOfRange(list, middle, list.length);

            mergeSort(leftHalf, level + 1);
            mergeSort(rightHalf, level + 1);

            int i = 0, j = 0, resultIndex = 0;

            while (i < leftHalf.length && j < rightHalf.length) {
                if (leftHalf[i] <= rightHalf[j]) {
                    list[resultIndex] = leftHalf[i];
                    i ++;
                } else {
                    list[resultIndex] = rightHalf[j];
                    j ++;
                }
                resultIndex ++;
            }
            while (i < leftHalf.length) {
                list[resultIndex] = leftHalf[i];
                i ++;
                resultIndex ++;
            }
            while (j < rightHalf.length) {
                list[resultIndex] = rightHalf[j];
                j ++;
                resultIndex ++;
            }
        }
    }

    //Quick Sort:
    private static void quickSort(int[] list) {
        quickSortHelper(list, 0, list.length - 1);
    }
    private static void quickSortHelper(int[] list, int first, int last) {
        if (first < last) {
            int split = partition(list, first, last);
            quickSortHelper(list, first, split - 1);
            quickSortHelper(list, split + 1, last);
        }
    }
    private static int partition(int[] list, int first, int last) {
        int pivotValue = list[first];
        int leftMark = first + 1;
        int rightMark = last;
        boolean done = false;

        while (!done) {
            while (leftMark <= rightMark && list[leftMark] <= pivotValue) {
                leftMark ++;
            }
            while (rightMark >= leftMark && list[rightMark] >= pivotValue) {
                rightMark --;
            }
            if (rightMark < leftMark) {
                done = true;
            } else {
                int temp = list[leftMark];
                list[leftMark] = list[rightMark];
                list[rightMark] = temp;
            }
        }

        int temp = list[first];
        list[first] = list[rightMark];
        list[rightMark] = temp;

        return rightMark;
    }
}