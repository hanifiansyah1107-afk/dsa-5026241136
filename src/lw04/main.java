package lw04;

import java.io.File;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("C:\\Users\\Dhany\\Downloads\\ASD\\dsa-5026241136\\branches.txt"));
        int n = 0;
        int m = 0;
        int[] a = new int[0];
        int[] t = new int[0];
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
                a = new int[n];
                int i = 0;
                while (i < n) {
                    a[i] = Integer.parseInt(parts[i]);
                    i++;
                }
            } else if (no == 3) {
                m = Integer.parseInt(parts[0]);
            } else if (no == 4) {
                t = new int[m];
                int i = 0;
                while (i < m) {
                    t[i] = Integer.parseInt(parts[i]);
                    i++;
                }
            }
        }
        sc.close();

        int i = 1;
        while (i <= 3 && i < n) {
            int key = a[i];
            int j = i - 1;
            while (j >= 0 && a[j] < key) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = key;
            i++;
        }

        String out = "";
        i = 0;
        while (i < n) {
            if (i > 0) {
                out = out + " ";
            }
            out = out + a[i];
            i++;
        }
        System.out.println(out);

        int k = 0;
        while (k < m) {
            int idx = -1;
            int j = 0;
            while (j < n) {
                if (a[j] == t[k]) {
                    idx = j;
                    break;
                }
                j++;
            }
            if (idx != -1) {
                System.out.println(t[k] + ": FOUND at index " + idx);
            } else {
                System.out.println(t[k] + ": NOT FOUND");
            }
            k++;
        }
    }
}