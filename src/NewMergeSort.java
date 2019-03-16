import java.util.ArrayList;
import java.util.Arrays;

public class NewMergeSort {


    public static void NewMergeSort(int[]arr, int n){
        int mid, i, j, k, l;
        mid = n/4;
        int [] quarter1 = new int [mid];
        int [] quarter2 = new int [mid];
        int [] quarter3 = new int [mid];
        int [] quarter4 = new int [mid];
        for (i = 0; i < mid ; i++){
            quarter1[i] = arr[i];
        }
        for (j = i; j < i + mid ; j++){
            quarter2[j - i] = arr[j];
        }
        for (k = j; k < j + mid ; k++){
            quarter3[k - j] = arr[k];
        }
        for (l = k; l < k + mid ; l++){
            quarter4[l - k] = arr[l];
        }
        MergeSort(quarter1, quarter1.length);
        QuickSort(quarter2, 0, quarter2.length - 1);
        BubbleSort(quarter3);
        InsertionSort(quarter4);
        Merge4arrays(arr, quarter1,quarter2,quarter3,quarter4);
    }

    public static void Merge4arrays(int [] arr, int [] quarter1, int [] quarter2, int [] quarter3, int [] quarter4) {
        int i = 0, j = 0, k = 0, l = 0, m = 0;

        while (i < arr.length){
            arr[i] = Math.min(Math.min(quarter1[j], quarter2[k]), Math.min(quarter3[l], quarter4[m]));
            if (arr[i] == quarter1[j]){
                j++;
                if (j == quarter1.length){
                    j = quarter1.length - 1;
                    quarter1[j] = Integer.MAX_VALUE;
                }
            }
            if (arr[i] == quarter2[k]){
                k++;
                if (k == quarter2.length){
                    k = quarter2.length - 1;
                    quarter2[k] = Integer.MAX_VALUE;
                }
            }
            if (arr[i] == quarter3[l]){
                l++;
                if (l == quarter3.length){
                    l = quarter3.length - 1;
                    quarter1[l] = Integer.MAX_VALUE;
                }
            }
            if (arr[i] == quarter4[m]){
                m++;
                if (m == quarter4.length){
                    m = quarter4.length - 1;
                    quarter4[m] = Integer.MAX_VALUE;
                }
            }
            i++;
        }
    }

    public static void MergeSort(int[]arr, int n){
        int mid, i;
        if (n < 2){
            return;
        }
        mid = n/2;
        int [] left = new int [mid];
        int [] right = new int [n-mid];
        for (i = 0; i < mid; i++){
            left[i] = arr[i];
        }
        for (i = mid; i < n; i++){
            right[i - mid] = arr[i];
        }
        MergeSort(left, mid);
        MergeSort(right, n - mid);
        Merge(arr, right, left, mid, n - mid);
    }

    public static void Merge(int[] arr, int[] right, int[] left, int leftCounter, int rightCounter){
        int i = 0, j = 0, k = 0;
        while (i < leftCounter && j < rightCounter){
            if (left[i] < right[j]){
                arr[k++] = left[i++];
            }
            else {
                arr[k++] = right[j++];
            }
        }
        while (i < leftCounter){
            arr[k++] = left[i++];
        }
        while (j < rightCounter){
            arr[k++] = right [j++];
        }
    }

    public static void QuickSort(int[]arr, int low, int high){
        int pivot, i, j, temp;
        if (low < high){
            pivot = low;
            i = low;
            j = high;
            while (i < j){
                while (arr[i] <= arr[pivot] && i <= high){
                    i++;
                }
                while (arr[j] > arr[pivot] && j >= low){
                    j--;
                }
                if (i < j){
                    temp = arr [i];
                    arr [i] = arr [j];
                    arr[j] = temp;
                }
            }
            temp = arr[j];
            arr[j] = arr[pivot];
            arr[pivot] = temp;
            QuickSort(arr, low, j-1);
            QuickSort(arr, j+1, high);
        }
    }

    public static void BubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void InsertionSort(int[] arr){
        int n = arr.length;
        for (int i = 1; i < n; ++i){
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key){
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

    public static void main(String[]args){
        int [] arr = {4,7,5,10,8,2,1,12,13,14,9,16,11,3,15,6};
        int n = arr.length;
        System.out.println("Array before Sorting ");
        for (int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        NewMergeSort(arr, n);
        System.out.println("Array after Sorting ");
        for (int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
    }
}