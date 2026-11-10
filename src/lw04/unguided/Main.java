package lw04.unguided;
import java.io.File;
import java.util.Scanner;

public class Main {

    static int steps;

    static void bubbleSort(int[] arr, int fromPass, int toPass){
        for(int i=fromPass; i<toPass && i<arr.length-1; i++){
            for(int j=0; j<arr.length-1-i; j++){
                if (arr[j] < arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }

    static int binarySearch(int[] arr, int target){
        int low = 0;
        int high = arr.length - 1;
        steps = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            steps++;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    static void printArray(int[] arr){
        for(int i=0;i<arr.length;i++){
            if (i > 0) {
                System.out.print(" ");
            }
            System.out.print(arr[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("C:\\Users\\Dhany\\Downloads\\ASD\\dsa-5026241136\\src\\lw04\\unguided\\parcels.txt"));
        int n = 0;
        int m = 0;
        int[] arr = new int[0];
        int[] targets = new int[0];
        int no = 0;

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }
            no++;
            String[] parts = line.split("\\s+");
            if (no == 1) {
                n = Integer.parseInt(parts[0]);
            } else if (no == 2) {
                arr = new int[n];
                for(int i=0;i<n;i++){
                    arr[i] = Integer.parseInt(parts[i]);
                }
            } else if (no == 3) {
                m = Integer.parseInt(parts[0]);
            } else if (no == 4) {
                targets = new int[m];
                for(int i=0;i<m;i++){
                    targets[i] = Integer.parseInt(parts[i]);
                }
            }
        }
        sc.close();

        bubbleSort(arr, 0, 2);
        int[] copy = new int[n];
        for(int i=0;i<n;i++){
            copy[i] = arr[i];
        }
        bubbleSort(arr, 2, n - 1);

        System.out.println("==== After 2 Passes ====");
        printArray(copy);
        System.out.println();
        System.out.println("==== Fully Sorted ====");
        printArray(arr);
        System.out.println();
        System.out.println("==== Search Results ====");

        for(int i=0;i<m;i++){
            int idx = binarySearch(arr, targets[i]);
            if (idx != -1) {
                System.out.println(targets[i] + ": FOUND at index " + idx + " (steps: " + steps + ")");
            } else {
                System.out.println(targets[i] + ": NOT FOUND (steps: " + steps + ")");
            }
        }
    }
}