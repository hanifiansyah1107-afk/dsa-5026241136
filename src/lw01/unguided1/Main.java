package lw01.unguided1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(new File("washes.txt"));
            if (scanner.hasNextInt()) {
                int totalRecords = scanner.nextInt();
                WashService[] services = new WashService[totalRecords];
                int[] unitsArray = new int[totalRecords];

                for (int i = 0; i < totalRecords; i++) {
                    String type = scanner.next();
                    String id = scanner.next();
                    int days = scanner.nextInt();
                    int units = scanner.nextInt();

                    unitsArray[i] = units;

                    if (type.equals("MOTORCYCLE")) {
                        services[i] = new MotorcycleWash(id, days);
                    } else if (type.equals("CAR")) {
                        services[i] = new CarWash(id, days);
                    }
                }

                
                for (int i = 0; i < services.length; i++) {
                    WashService service = services[i];
                    int units = unitsArray[i];
                    
                    System.out.println(service.getId() + " | " + service.label() + " | " + service.calculateCharge(units));
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File washes.txt not found.");
        }
    }
}
