import java.io.FileWriter;
import java.io.IOException;
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

    public void addClaim() {
        System.out.println("Enter the insured person's name:");
        String insuredPerson = scanner.nextLine();

        System.out.println("Enter the insured person's card number:");
        String cardNumber = scanner.nextLine();

        Date claimDate = new Date(); // The claim date is set to the current date

        System.out.println("Enter the exam date (in the format yyyy-mm-dd):");
        String examDateString = scanner.nextLine();
        Date examDate = null;
        try {
            examDate = new SimpleDateFormat("yyyy-MM-dd").parse(examDateString);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please try again.");
            return;
        }

        System.out.println("Enter the documents (separated by commas):");
        String documentsString = scanner.nextLine();
        List<String> documents = Arrays.asList(documentsString.split(","));

        System.out.println("Enter the claim amount:");
        double claimAmount = scanner.nextDouble();
        scanner.nextLine(); // consume newline

        System.out.println("Enter the status:");
        String status = scanner.nextLine();

        System.out.println("Enter the banking info (account holder's name, account number, bank name):");
        String bankingInfoString = scanner.nextLine();
        String[] bankingInfoArray = bankingInfoString.split(",");
        if (bankingInfoArray.length != 3) {
            System.out.println("Invalid banking info. Please try again.");
            return;
        }
        BankingInfo bankingInfo = new BankingInfo(bankingInfoArray[0], bankingInfoArray[1], bankingInfoArray[2]);

        Claim claim = new Claim(UUID.randomUUID().toString(), claimDate, insuredPerson, cardNumber, examDate, documents, claimAmount, status, bankingInfo);

        // Find the customer and add the claim to their list of claims
        for (Customer customer : customers) {
            if (customer.insuranceCard.equals(cardNumber)) {
                customer.getClaims().add(claim);
                System.out.println("Claim added successfully.");
                return;
            }
        }

        System.out.println("No customer found with the given card number.");
    }

    public void updateClaim() {
        System.out.println("Enter the ID of the claim you want to update:");
        String claimId = scanner.nextLine();

        // Find the claim in the list of all claims
        for (Customer customer : customers) {
            for (Claim claim : customer.getClaims()) {
                if (claim.getId().equals(claimId)) {
                    // The claim is found, ask the user for the new details
                    System.out.println("Enter the new insured person's name:");
                    String insuredPerson = scanner.nextLine();

                    System.out.println("Enter the new insured person's card number:");
                    String cardNumber = scanner.nextLine();

                    System.out.println("Enter the new exam date (in the format yyyy-mm-dd):");
                    String examDateString = scanner.nextLine();
                    Date examDate = null;
                    try {
                        examDate = new SimpleDateFormat("yyyy-MM-dd").parse(examDateString);
                    } catch (ParseException e) {
                        System.out.println("Invalid date format. Please try again.");
                        return;
                    }

                    System.out.println("Enter the new documents (separated by commas):");
                    String documentsString = scanner.nextLine();
                    List<String> documents = Arrays.asList(documentsString.split(","));

                    System.out.println("Enter the new claim amount:");
                    double claimAmount = scanner.nextDouble();
                    scanner.nextLine(); // consume newline

                    System.out.println("Enter the new status:");
                    String status = scanner.nextLine();

                    System.out.println("Enter the new banking info (account holder's name, account number, bank name):");
                    String bankingInfoString = scanner.nextLine();
                    String[] bankingInfoArray = bankingInfoString.split(",");
                    if (bankingInfoArray.length != 3) {
                        System.out.println("Invalid banking info. Please try again.");
                        return;
                    }
                    BankingInfo bankingInfo = new BankingInfo(bankingInfoArray[0], bankingInfoArray[1], bankingInfoArray[2]);

                    // Update the claim object with the new details
                    claim.setInsuredPerson(insuredPerson);
                    claim.setCardNumber(cardNumber);
                    claim.setExamDate(examDate);
                    claim.setDocuments(documents);
                    claim.setClaimAmount(claimAmount);
                    claim.setStatus(status);
                    claim.setBankingInfo(bankingInfo);

                    System.out.println("Claim updated successfully.");
                    return;
                }
            }
        }

        System.out.println("No claim found with the given ID.");
    }

    public void deleteClaim() {
        System.out.println("Enter the ID of the claim you want to delete:");
        String claimId = scanner.nextLine();

        // Find the claim in the list of all claims
        for (Customer customer : customers) {
            Iterator<Claim> iterator = customer.getClaims().iterator();
            while (iterator.hasNext()) {
                Claim claim = iterator.next();
                if (claim.getId().equals(claimId)) {
                    // The claim is found, delete it from the list
                    iterator.remove();
                    System.out.println("Claim deleted successfully.");
                    return;
                }
            }
        }

        System.out.println("No claim found with the given ID.");
    }

    public void viewClaim() {
        System.out.println("Enter the ID of the claim you want to view:");
        String claimId = scanner.nextLine();

        // Find the claim in the list of all claims
        for (Customer customer : customers) {
            for (Claim claim : customer.getClaims()) {
                if (claim.getId().equals(claimId)) {
                    // The claim is found, print its details
                    System.out.println("Claim ID: " + claim.getId());
                    System.out.println("Insured Person: " + claim.getInsuredPerson());
                    System.out.println("Card Number: " + claim.getCardNumber());
                    System.out.println("Claim Date: " + claim.getClaimDate());
                    System.out.println("Exam Date: " + claim.getExamDate());
                    System.out.println("Documents: " + claim.getDocuments());
                    System.out.println("Claim Amount: " + claim.getClaimAmount());
                    System.out.println("Status: " + claim.getStatus());
                    System.out.println("Banking Info: " + claim.getBankingInfo());
                    return;
                }
            }
        }

        System.out.println("No claim found with the given ID.");
    }

    public void viewAllClaims() {
        // Iterate over all customers
        for (Customer customer : customers) {
            // Iterate over all claims of the current customer
            for (Claim claim : customer.getClaims()) {
                // Print the details of the claim
                System.out.println("Claim ID: " + claim.getId());
                System.out.println("Insured Person: " + claim.getInsuredPerson());
                System.out.println("Card Number: " + claim.getCardNumber());
                System.out.println("Claim Date: " + claim.getClaimDate());
                System.out.println("Exam Date: " + claim.getExamDate());
                System.out.println("Documents: " + claim.getDocuments());
                System.out.println("Claim Amount: " + claim.getClaimAmount());
                System.out.println("Status: " + claim.getStatus());
                System.out.println("Banking Info: " + claim.getBankingInfo());
                System.out.println("-------------------------------");
            }
        }
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
        System.out.println("Enter the insured person's card number:");
        String cardNumber = scanner.nextLine();

        // Find the customer and add the claim to their list of claims
        for (Customer customer : customers) {
            if (customer.insuranceCard.equals(cardNumber)) {
                customer.getClaims().add(claim);
                System.out.println("Claim added successfully.");
                return;
            }
        }

        System.out.println("No customer found with the given card number.");
    }

    @Override
    public void update(Claim newClaim) {
        System.out.println("Enter the ID of the claim you want to update:");
        String claimId = scanner.nextLine();

        // Find the claim in the list of all claims
        for (Customer customer : customers) {
            for (Claim claim : customer.getClaims()) {
                if (claim.getId().equals(claimId)) {
                    // The claim is found, update it with the new details
                    claim.setInsuredPerson(newClaim.getInsuredPerson());
                    claim.setCardNumber(newClaim.getCardNumber());
                    claim.setExamDate(newClaim.getExamDate());
                    claim.setDocuments(newClaim.getDocuments());
                    claim.setClaimAmount(newClaim.getClaimAmount());
                    claim.setStatus(newClaim.getStatus());
                    claim.setBankingInfo(newClaim.getBankingInfo());

                    System.out.println("Claim updated successfully.");
                    return;
                }
            }
        }

        System.out.println("No claim found with the given ID.");
    }

    @Override
    public void delete(String claimId) {
        // Find the claim in the list of all claims
        for (Customer customer : customers) {
            Iterator<Claim> iterator = customer.getClaims().iterator();
            while (iterator.hasNext()) {
                Claim claim = iterator.next();
                if (claim.getId().equals(claimId)) {
                    // The claim is found, delete it from the list
                    iterator.remove();
                    System.out.println("Claim deleted successfully.");
                    return;
                }
            }
        }

        System.out.println("No claim found with the given ID.");
    }

    @Override
    public Claim getOne(String claimId) {
        // Find the claim in the list of all claims
        for (Customer customer : customers) {
            for (Claim claim : customer.getClaims()) {
                if (claim.getId().equals(claimId)) {
                    // The claim is found, return it
                    return claim;
                }
            }
        }

        System.out.println("No claim found with the given ID.");
        return null;
    }

    @Override
    public List<Claim> getAll() {
        List<Claim> allClaims = new ArrayList<>();

        // Iterate over all customers
        for (Customer customer : customers) {
            // Add all claims of the current customer to the list of all claims
            allClaims.addAll(customer.getClaims());
        }

        return allClaims;
    }

    @Override
    public Iterator<Claim> iterator() {
        List<Claim> allClaims = getAll();
        return allClaims.iterator();
    }
}


