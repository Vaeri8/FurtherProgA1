//Luong Tuan Kiet - s3980288

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.*;

public class Main implements ClaimProcessManager {
    List<Customer> customers; // Declare customers at the class level
    Scanner scanner = new Scanner(System.in);

    public Main() {
        customers = new ArrayList<>();
        initializeData();
    }

    private void initializeData() {
        // Create some default customers
        customers.add(new Customer("John Doe", "123 Main St", "555-555-5555"));
        customers.add(new Customer("Jane Smith", "456 Oak St", "444-444-4444"));

        // Create some default claims
        List<String> documents = new ArrayList<>();
        documents.add("Document1");
        documents.add("Document2");

        Date date = new Date();
        BankingInfo bankingInfo = new BankingInfo("John Doe", "123456789", "987654321");

        Claim claim1 = new Claim("1", date, "John Doe", "123456789", date, documents, 1000.0, "Pending", bankingInfo);
        Claim claim2 = new Claim("2", date, "Jane Smith", "987654321", date, documents, 2000.0, "Approved", bankingInfo);

        // Add the claims to the customers
        customers.get(0).getClaims().add(claim1);
        customers.get(1).getClaims().add(claim2);
    }

    public void saveToFile(String filename) {

    }

    public void loadFromFile(String filename) {

    }
}

public void addClaim() {
    System.out.println("Enter claim details:");

    System.out.println("Enter ID:");
    String id = scanner.nextLine();

    System.out.println("Enter insured person:");
    String insuredPerson = scanner.nextLine();

    System.out.println("Enter card number:");
    String cardNumber = scanner.nextLine();

    System.out.println("Enter claim amount:");
    double claimAmount = scanner.nextDouble();
    scanner.nextLine(); // consume newline

    System.out.println("Enter status:");
    String status = scanner.nextLine();

    // Create a new claim
    Claim claim = new Claim(id, new Date(), insuredPerson, cardNumber, new Date(), new ArrayList<>(), claimAmount, status, null);

    // Add the claim to the appropriate customer
    for (Customer customer : customers) {
        if (customer.getInsuranceCard().getCardNumber().equals(cardNumber)) {
            customer.getClaims().add(claim);
            System.out.println("Claim added successfully.");
            break;
        }
    }

    // Save the updated customers list to a file
    saveToFile("customers.txt");
}

public void saveToFile(String filename) {
    try (FileWriter writer = new FileWriter(filename)) {
        for (Customer customer : customers) {
            writer.write(customer.getName() + ",");
            writer.write(customer.getAddress() + ",");
            writer.write(customer.getPhoneNumber() + ",");
            writer.write(customer.getInsuranceCard().getCardNumber() + ",");

            // Write the claims
            for (Claim claim : customer.getClaims()) {
                writer.write(claim.getId() + ",");
                writer.write(new SimpleDateFormat("yyyy-MM-dd").format(claim.getClaimDate()) + ",");
                writer.write(claim.getInsuredPerson() + ",");
                writer.write(claim.getCardNumber() + ",");
                writer.write(new SimpleDateFormat("yyyy-MM-dd").format(claim.getExamDate()) + ",");
                writer.write(claim.getClaimAmount() + ",");
                writer.write(claim.getStatus() + ",");
                // Add more fields as necessary
            }

            writer.write("\n");
        }
    } catch (IOException e) {
        System.out.println("An error occurred while writing to the file: " + filename);
    }
}

    public void updateClaim() {

    }

    public void deleteClaim() {

    }

    public void viewClaim() {

    }

    public void viewAllClaims() {

    }

    public static void main(String[] args) {
        Main system = new Main();

        boolean exit = false;
        while (!exit) {
            System.out.println("Welcome to the Claim Management System");
            System.out.println("Please select an option:");
            System.out.println("1. Add a claim");
            System.out.println("2. Update a claim");
            System.out.println("3. Delete a claim");
            System.out.println("4. View a claim");
            System.out.println("5. View all claims");
            System.out.println("6. Exit");

            int option = system.scanner.nextInt();
            system.scanner.nextLine(); // consume newline

            switch (option) {
                case 1:
                    system.addClaim();
                    break;
                case 2:
                    system.updateClaim();
                    break;
                case 3:
                    system.deleteClaim();
                    break;
                case 4:
                    system.viewClaim();
                    break;
                case 5:
                    system.viewAllClaims();
                    break;
                case 6:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    @Override
    public void add(Claim claim) {

    }

    @Override
    public void update(Claim newClaim) {

    }

    @Override
    public void delete(String claimId) {

    }

    @Override
    public Claim getOne(String claimId) {

    }

    @Override
    public List<Claim> getAll() {

    }

    @Override

}


