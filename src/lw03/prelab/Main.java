package lw03.prelab;

import java.io.File;
import java.util.LinkedList;
import java.util.LinkedHashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws Exception {
        
        List<String> playlist = new LinkedList<>();
        Scanner scanner1 = new Scanner(new File("C:\\Users\\Dhany\\Downloads\\ASD\\dsa-5026241136\\playlist.txt"));
        
        while (scanner1.hasNextLine()) {
            String line = scanner1.nextLine().trim();
            if (line.isEmpty()) continue;
            
            String[] parts = line.split(" ", 2);
            String command = parts[0];
            
            if (command.equals("ADD")) {
                playlist.add(parts[1]);
            } else if (command.equals("INSERT")) {
                String[] subParts = parts[1].split(" ", 2);
                int index = Integer.parseInt(subParts[0]);
                String song = subParts[1];
                playlist.add(index, song);
            } else if (command.equals("REMOVE")) {
                playlist.remove(parts[1]);
            }
        }
        scanner1.close();

        System.out.println("===== Problem 1 =====");
        System.out.println("Total songs: " + playlist.size());
        for (int i = 0; i < playlist.size(); i++) {
            System.out.println((i + 1) + ": " + playlist.get(i));
        }

        Set<String> participants = new LinkedHashSet<>();
        int duplicates = 0;
        Scanner scanner2 = new Scanner(new File("C:\\Users\\Dhany\\Downloads\\ASD\\dsa-5026241136\\participants.txt"));
        
        while (scanner2.hasNextLine()) {
            String name = scanner2.nextLine().trim();
            if (name.isEmpty()) continue;
            
            if (!participants.add(name)) { 
                duplicates++;
            }
        }
        scanner2.close();

        System.out.println("\n===== Problem 2 =====");
        System.out.println("Unique participants: " + participants.size());
        int count = 1;
        for (String participant : participants) {
            System.out.println(count + ". " + participant);
            count++;
        }
        System.out.println("Duplicate registrations: " + duplicates);

        Map<String, Integer> inventory = new LinkedHashMap<>();
        int failedSales = 0;
        Scanner scanner3 = new Scanner(new File("C:\\Users\\Dhany\\Downloads\\ASD\\dsa-5026241136\\inventory.txt"));
        
        while (scanner3.hasNextLine()) {
            String line = scanner3.nextLine().trim();
            if (line.isEmpty()) continue;
            
            String[] parts = line.split(" ");
            String type = parts[0];
            String product = parts[1];
            int quantity = Integer.parseInt(parts[2]);
            
            if (type.equals("ADD")) {
                inventory.put(product, inventory.getOrDefault(product, 0) + quantity);
            } else if (type.equals("SELL")) {
                int currentStock = inventory.getOrDefault(product, 0);
                if (currentStock >= quantity) {
                    inventory.put(product, currentStock - quantity);
                } else {
                    failedSales++;
                }
            }
        }
        scanner3.close();

        System.out.println("\n===== Problem 3 =====");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("Failed sales: " + failedSales);
    }
}
