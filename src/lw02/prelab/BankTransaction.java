package lw02.prelab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class BankTransaction {
    public static void main(String[] args) {
        LinkedList<String[]> transactionsList = new LinkedList<>();
        LinkedList<String[]> customersList = new LinkedList<>();

        try {
            File file = new File("transactions.txt");
            Scanner scanner = new Scanner(file);
            
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.trim().isEmpty()) continue;
                
                String[] data = line.split(" ");
                transactionsList.add(data);
                
                boolean exists = false;
                for (String[] cust : customersList) {
                    if (cust[0].equals(data[0])) {
                        exists = true;
                        break;
                    }
                }
                if (!exists) {
                    customersList.add(new String[]{data[0], "0"}); 
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File transactions.txt tidak ditemukan.");
            return;
        }

        Queue<String[]> transactionQueue = new LinkedList<>();
        while (!transactionsList.isEmpty()) {
            transactionQueue.add(transactionsList.poll());
        }

        Stack<String[]> failedTransactions = new Stack<>();

        while (!transactionQueue.isEmpty()) {
            String[] trx = transactionQueue.poll();
            String name = trx[0];
            String type = trx[1];
            int amount = Integer.parseInt(trx[2]);

            for (String[] cust : customersList) {
                if (cust[0].equals(name)) {
                    int balance = Integer.parseInt(cust[1]);
                    
                    if (type.equals("DEPOSIT")) {
                        balance += amount;
                        cust[1] = String.valueOf(balance);
                    } else if (type.equals("WITHDRAW")) {
                        if (amount > balance) {
                            failedTransactions.push(trx); 
                        } else {
                            balance -= amount;
                            cust[1] = String.valueOf(balance);
                        }
                    }
                    break;
                }
            }
        }

        System.out.println("=== Final Balances ===");
        for (String[] cust : customersList) {
            System.out.println(cust[0] + ": " + cust[1]);
        }

        System.out.println("=== Failed Transactions ===");
        while (!failedTransactions.isEmpty()) {
            String[] failedTrx = failedTransactions.pop();
            System.out.println(failedTrx[0] + " " + failedTrx[1] + " " + failedTrx[2]);
        }
    }
}
