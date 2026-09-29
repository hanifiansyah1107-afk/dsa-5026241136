package lw02.unguided;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class RestaurantOrder {
    public static void main(String[] args) throws Exception {
        
        LinkedList<String[]> orders = new LinkedList<>();
        LinkedList<String[]> foods = new LinkedList<>();
        LinkedList<String[]> drinks = new LinkedList<>();
        LinkedList<String[]> successfulOrders = new LinkedList<>();
        Queue<String[]> queue = new LinkedList<>();
        Stack<String[]> failed = new Stack<>();

        foods.add(new String[]{"Bakso", "2"});
        foods.add(new String[]{"Sate", "1"});
        foods.add(new String[]{"Soto", "2"});

        drinks.add(new String[]{"EsTeh", "4"});
        drinks.add(new String[]{"EsJeruk", "2"});

        Scanner scanner = new Scanner(new File("C:\\Users\\Dhany\\Downloads\\ASD\\order.txt"));
        
        while(scanner.hasNext()){
            String[] order = new String[4];
            order[0] = scanner.next(); 
            order[1] = scanner.next(); 
            order[2] = scanner.next(); 
            order[3] = scanner.next(); 
            orders.add(order);
        }
        scanner.close();

        queue.addAll(orders);

        while (!queue.isEmpty()) {
            String[] order = queue.poll();

            String foodRequested = order[1];
            String drinkRequested = order[2];

            boolean foodAvailable = true;
            boolean drinkAvailable = true;
            
            String[] foodItem = null;
            String[] drinkItem = null;

            if (!foodRequested.equals("-")) {
                foodAvailable = false;
                for (String[] f : foods) {
                    if (f[0].equals(foodRequested)) {
                        foodItem = f;
                        if (Integer.parseInt(f[1]) > 0) {
                            foodAvailable = true;
                        }
                        break;
                    }
                }
            }

            if (!drinkRequested.equals("-")) {
                drinkAvailable = false;
                for (String[] d : drinks) {
                    if (d[0].equals(drinkRequested)) {
                        drinkItem = d;
                        if (Integer.parseInt(d[1]) > 0) {
                            drinkAvailable = true;
                        }
                        break;
                    }
                }
            }

            if (foodAvailable && drinkAvailable) {
                if (foodItem != null) {
                    int currentFoodStock = Integer.parseInt(foodItem[1]);
                    foodItem[1] = String.valueOf(currentFoodStock - 1);
                }
                
                if (drinkItem != null) {
                    int currentDrinkStock = Integer.parseInt(drinkItem[1]);
                    drinkItem[1] = String.valueOf(currentDrinkStock - 1);
                }
                
                successfulOrders.add(order);
            } else {
                failed.push(order);
            }
        }

        System.out.println("=== Successfully Processed Orders ===");
        for (String[] success : successfulOrders) {
            System.out.println(success[0] + " " + success[1] + " " + success[2] + " " + success[3]);
        }

        System.out.println("=== Remaining Food Stock ===");
        for (String[] f : foods) {
            System.out.println(f[0] + ": " + f[1]);
        }

        System.out.println("=== Remaining Drink Stock ===");
        for (String[] d : drinks) {
            System.out.println(d[0] + ": " + d[1]);
        }

        System.out.println("=== Failed Orders ===");
        while (!failed.isEmpty()) {
            String[] fail = failed.pop();
            System.out.println(fail[0] + " " + fail[1] + " " + fail[2] + " " + fail[3]);
        }
    }
}