package lw04.prelab;

import java.io.File;
import java.util.Scanner;

public class Main {

    static void insertionSort(int[] arr, int passes){
        for(int i=1; i<=passes && i<arr.length; i++){
            int key = arr[i];
            int j = i-1;

            while (j >=0 && arr[j] < key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j+1] = key;
        }
    }

    static int linearSearch(int[] arr, int target){
        for(int i=0;i<arr.length;i++){
            if (arr[i] == target) {
                return i;
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
        Scanner sc = new Scanner(new File("C:\\Users\\Dhany\\Downloads\\ASD\\dsa-5026241136\\src\\lw04\\prelab\\branches.txt"));
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

        insertionSort(arr, 3);
        printArray(arr);

        for(int i=0;i<m;i++){
            int idx = linearSearch(arr, targets[i]);
            if (idx != -1) {
                System.out.println(targets[i] + ": FOUND at index " + idx);
            } else {
                System.out.println(targets[i] + ": NOT FOUND");
            }
        }
    }
}