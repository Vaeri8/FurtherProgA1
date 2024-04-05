//Luong Tuan Kiet - s3980288

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.*;

public class Main {
    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Main system = new Main();
        system.run();
    }

    public void run() {
        boolean exit = false;
        while (!exit) {
            System.out.println("Welcome to the Claim Processing System!");
            System.out.println("Please select an option:");
            System.out.println("1. Add Claim");
            System.out.println("2. Update Claim");
            System.out.println("3. Delete Claim");
            System.out.println("4. View Claim");
            System.out.println("5. View All Claims");
            System.out.println("6. Exit");

            int option = scanner.nextInt();
            scanner.nextLine(); // consume newline

            ClaimOperation claimOperation = new ClaimOperation();
            switch (option) {
                case 1:
                    claimOperation.addClaim();
                    break;
                case 2:
                    claimOperation.updateClaim();
                    break;
                case 3:
                    claimOperation.deleteClaim();
                    break;
                case 4:
                    claimOperation.viewClaim();
                    break;
                case 5:
                    claimOperation.viewAllClaims();
                    break;
                case 6:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

}