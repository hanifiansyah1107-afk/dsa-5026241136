package lw03.unguided;

import java.io.File;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws Exception {
        
        Set<String> registered = new LinkedHashSet<>();
        Scanner scRegistrations = new Scanner(new File("C:\\Users\\Dhany\\Downloads\\ASD\\dsa-5026241136\\registrations.txt"));
        
        while (scRegistrations.hasNextLine()) {
            String id = scRegistrations.nextLine().trim();
            if (!id.isEmpty()) {
                registered.add(id);
            }
        }
        scRegistrations.close();

        Set<String> checkedIn = new LinkedHashSet<>();
        int rejectedAttempts = 0;
        
        System.out.println("===== Event Check-In Results =====");
        Scanner scCheckins = new Scanner(new File("C:\\Users\\Dhany\\Downloads\\ASD\\dsa-5026241136\\checkins.txt"));
        
        while (scCheckins.hasNextLine()) {
            String id = scCheckins.nextLine().trim();
            if (id.isEmpty()) continue;
            
            if (registered.contains(id)) {
                if (checkedIn.add(id)) {
                    System.out.println(id + ": Checked in");
                } else {
                    System.out.println(id + ": Rejected (already checked in)");
                    rejectedAttempts++;
                }
            } else {
                System.out.println(id + ": Rejected (not registered)");
                rejectedAttempts++;
            }
        }
        scCheckins.close();

        int absentStudents = registered.size() - checkedIn.size();

        System.out.println("\n===== Final Event Summary =====");
        System.out.println("Registered students: " + registered.size());
        System.out.println("Successful check-ins: " + checkedIn.size());
        System.out.println("Absent students: " + absentStudents);
        System.out.println("Rejected attempts: " + rejectedAttempts);
    }
}