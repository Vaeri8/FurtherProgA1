//Luong Tuan Kiet - s3980288

import Operations.ClaimOperation;
import Operations.CustomerOperation;
import Operations.InsuranceOperation;

import java.util.Scanner;

public class Main {
    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Main system = new Main();
        system.run();
    }

    public void run() {
        boolean exit = false;
        while (!exit) {
            System.out.println("Welcome to the Classes.Claim Processing System!");
            System.out.println("Please select an option:");
            System.out.println("1. Add Claim");
            System.out.println("2. Update Claim");
            System.out.println("3. Delete Claim");
            System.out.println("4. View Claim");
            System.out.println("5. View All Claims");
            System.out.println("6. View All Customers");
            System.out.println("7. View All Insurance Cards");
            System.out.println("8. Exit");

            int option = scanner.nextInt();
            scanner.nextLine(); // consume newline

            ClaimOperation claimOperation = new ClaimOperation();
            CustomerOperation customerOperation = new CustomerOperation();
            InsuranceOperation insuranceOperation = new InsuranceOperation();
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
                    customerOperation.viewAllCustomers();
                    break;
                case 7:
                    insuranceOperation.viewAllInsurances();
                    break;
                case 8:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

}