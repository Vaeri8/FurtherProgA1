//Luong Tuan Kiet - s3980288

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ClaimOperation implements ClaimProcessManager{

    List<Customer> customers = new ArrayList<>(); // Declare customers at the class level
    Scanner scanner = new Scanner(System.in);

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
        Claim claim = new Claim(id, new Date(), insuredPerson, cardNumber, new Date(), new ArrayList<>(), claimAmount,
                status, null);

        // Add the claim to the appropriate customer
        for (Customer customer : customers) {
            if (cardNumber.equals(customer.getInsuranceCard())) {
                customer.getClaims().add(claim);
                System.out.println("Claim added successfully.");
                break;
            }
        }

        // Save the updated customers list to a file
        saveToFile("customers.txt");
    }

    public void updateClaim() {
        System.out.println("Enter the ID of the claim you want to update:");
        String id = scanner.nextLine();

        // Find the claim
        for (Customer customer : customers) {
            for (Claim claim : customer.getClaims()) {
                if (claim.getId().equals(id)) {
                    // Ask the user for the new details of the claim
                    System.out.println("Enter the new insured person:");
                    String insuredPerson = scanner.nextLine();

                    System.out.println("Enter the new card number:");
                    String cardNumber = scanner.nextLine();

                    System.out.println("Enter the new claim amount:");
                    double claimAmount = scanner.nextDouble();
                    scanner.nextLine(); // consume newline

                    System.out.println("Enter the new status:");
                    String status = scanner.nextLine();

                    // Update the claim
                    claim.setInsuredPerson(insuredPerson);
                    claim.setCardNumber(cardNumber);
                    claim.setClaimAmount(claimAmount);
                    claim.setStatus(status);

                    System.out.println("Claim updated successfully.");

                    // Save the updated customers list to a file
                    saveToFile("customers.txt");

                    return;
                }
            }
        }

        System.out.println("No claim found with the given ID.");
    }

    public void deleteClaim() {
        System.out.println("Enter the ID of the claim you want to delete:");
        String id = scanner.nextLine();

        // Find the claim
        for (Customer customer : customers) {
            Iterator<Claim> iterator = customer.getClaims().iterator();
            while (iterator.hasNext()) {
                Claim claim = iterator.next();
                if (claim.getId().equals(id)) {
                    // Remove the claim
                    iterator.remove();

                    System.out.println("Claim deleted successfully.");

                    // Save the updated customers list to a file
                    saveToFile("customers.txt");

                    return;
                }
            }
        }

        System.out.println("No claim found with the given ID.");
    }

    public void viewClaim() {
        System.out.println("Enter the ID of the claim you want to view:");
        String id = scanner.nextLine();

        try (BufferedReader reader = new BufferedReader(new FileReader("claims.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String claimId = parts[0];
                Date claimDate = new SimpleDateFormat("yyyy-MM-dd").parse(parts[1]);
                String insuredPerson = parts[2];
                String cardNumber = parts[3];
                Date examDate = new SimpleDateFormat("yyyy-MM-dd").parse(parts[4]);
                double claimAmount = Double.parseDouble(parts[5]);
                String status = parts[6];

                // Create a new claim
                Claim claim = new Claim(claimId, claimDate, insuredPerson, cardNumber, examDate, new ArrayList<>(), claimAmount, status, null);

                // If this is the claim we're looking for, print its details and return
                if (claim.getId().equals(id)) {
                    System.out.println("ID: " + claim.getId());
                    System.out.println("Insured Person: " + claim.getInsuredPerson());
                    System.out.println("Card Number: " + claim.getCardNumber());
                    System.out.println("Claim Amount: " + claim.getClaimAmount());
                    System.out.println("Status: " + claim.getStatus());
                    return;
                }
            }
        } catch (IOException | ParseException e) {
            System.out.println("An error occurred while reading from the file: claims.txt");
        }

        System.out.println("No claim found with the given ID.");
    }

    public void viewAllClaims() {
        try (BufferedReader reader = new BufferedReader(new FileReader("claims.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String claimId = parts[0];
                Date claimDate = new SimpleDateFormat("yyyy-MM-dd").parse(parts[1]);
                String insuredPerson = parts[2];
                String cardNumber = parts[3];
                Date examDate = new SimpleDateFormat("yyyy-MM-dd").parse(parts[4]);
                double claimAmount = Double.parseDouble(parts[5]);
                String status = parts[6];

                // Create a new claim
                Claim claim = new Claim(claimId, claimDate, insuredPerson, cardNumber, examDate, new ArrayList<>(), claimAmount, status, null);

                // Print the details of the claim
                System.out.println("ID: " + claim.getId());
                System.out.println("Insured Person: " + claim.getInsuredPerson());
                System.out.println("Card Number: " + claim.getCardNumber());
                System.out.println("Claim Amount: " + claim.getClaimAmount());
                System.out.println("Status: " + claim.getStatus());
                System.out.println("------------------------");
            }
        } catch (IOException | ParseException e) {
            System.out.println("An error occurred while reading from the file: claims.txt");
        }
    }

    public void saveToFile(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            for (Customer customer : customers) {
                writer.write(customer.getName() + ",");
                writer.write(customer.getInsuranceCard() + ",");

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

}
