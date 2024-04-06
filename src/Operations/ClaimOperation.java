//Luong Tuan Kiet - s3980288

package Operations;

import Interface.ClaimProcessManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import Classes.*;
import Enum.*;

public class ClaimOperation implements ClaimProcessManager {

    List<Customer> customers = new ArrayList<>();
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
        scanner.nextLine();

        System.out.println("Enter status:");
        ClaimStatus Status = ClaimStatus.valueOf(scanner.nextLine().toUpperCase());

        // Create a new claim
        Claim claim = new Claim(id, new Date(), insuredPerson, cardNumber, new Date(), new ArrayList<>(), claimAmount, Status, null);

        // Write the new claim to the file
        try (FileWriter writer = new FileWriter("src/Data/claims.txt", true)) { // true to append to the file
            writer.write(claim.getId() + ",");
            writer.write(new SimpleDateFormat("yyyy-MM-dd").format(claim.getClaimDate()) + ",");
            writer.write(claim.getInsuredPerson() + ",");
            writer.write(claim.getCardNumber() + ",");
            writer.write(new SimpleDateFormat("yyyy-MM-dd").format(claim.getExamDate()) + ",");
            writer.write(claim.getClaimAmount() + ",");
            writer.write(claim.getStatus() + ",");
            // Add more fields as necessary
            writer.write("\n");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: src/Data/claims.txt");
        }

        saveToFile("customers.txt");// Saves customers list
    }

    public void updateClaim() {
        System.out.println("Enter the ID of the claim you want to update:");
        String id = scanner.nextLine();

        // Reads file and stores each claim in a list
        List<Claim> claims = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/src/Data/claims.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String claimId = parts[0];
                Date claimDate = new SimpleDateFormat("yyyy-MM-dd").parse(parts[1]);
                String insuredPerson = parts[2];
                String cardNumber = parts[3];
                Date examDate = new SimpleDateFormat("yyyy-MM-dd").parse(parts[4]);
                double claimAmount = Double.parseDouble(parts[5]);
                ClaimStatus Status = ClaimStatus.valueOf(parts[6]);

                Claim claim = new Claim(claimId, claimDate, insuredPerson, cardNumber, examDate, new ArrayList<>(), claimAmount, Status, null);

                claims.add(claim);
            }
        } catch (IOException | ParseException e) {
            System.out.println("An error occurred while reading from the file: src/Data/claims.txt");
        }

        // Find the claim
        for (Claim claim : claims) {
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
                claim.setStatus(ClaimStatus.NEW);

                System.out.println("Claim updated successfully.");

                // Write the updated list of claims back to the src/Data/claims.txt file
                try (FileWriter writer = new FileWriter("src/Data/claims.txt")) {
                    for (Claim updatedClaim : claims) {
                        writer.write(updatedClaim.getId() + ",");
                        writer.write(new SimpleDateFormat("yyyy-MM-dd").format(updatedClaim.getClaimDate()) + ",");
                        writer.write(updatedClaim.getInsuredPerson() + ",");
                        writer.write(updatedClaim.getCardNumber() + ",");
                        writer.write(new SimpleDateFormat("yyyy-MM-dd").format(updatedClaim.getExamDate()) + ",");
                        writer.write(updatedClaim.getClaimAmount() + ",");
                        writer.write(updatedClaim.getStatus() + ",");
                        // Add more fields as necessary
                        writer.write("\n");
                    }
                } catch (IOException e) {
                    System.out.println("An error occurred while writing to the file: src/Data/claims.txt");
                }

                return;
            }
        }

        System.out.println("No claim found with the given ID.");
    }

    public void deleteClaim() {
        System.out.println("Enter the ID of the claim you want to delete:");
        String id = scanner.nextLine();

        // Read the src/Data/claims.txt file and store each claim in a list
        List<Claim> claims = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/Data/claims.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String claimId = parts[0];
                Date claimDate = new SimpleDateFormat("yyyy-MM-dd").parse(parts[1]);
                String insuredPerson = parts[2];
                String cardNumber = parts[3];
                Date examDate = new SimpleDateFormat("yyyy-MM-dd").parse(parts[4]);
                double claimAmount = Double.parseDouble(parts[5]);
                ClaimStatus Status = ClaimStatus.valueOf(parts[6]);

                // Create a new claim
                Claim claim = new Claim(claimId, claimDate, insuredPerson, cardNumber, examDate, new ArrayList<>(), claimAmount, Status, null);

                // Add the claim to the list
                claims.add(claim);
            }
        } catch (IOException | ParseException e) {
            System.out.println("An error occurred while reading from the file: src/Data/claims.txt");
        }

        // Find the claim
        for (Iterator<Claim> iterator = claims.iterator(); iterator.hasNext();) {
            Claim claim = iterator.next();
            if (claim.getId().equals(id)) {
                // Remove the claim
                iterator.remove();

                System.out.println("Claim deleted successfully.");

                // Write the updated list of claims back to the src/Data/claims.txt file
                try (FileWriter writer = new FileWriter("src/Data/claims.txt")) {
                    for (Claim updatedClaim : claims) {
                        writer.write(updatedClaim.getId() + ",");
                        writer.write(new SimpleDateFormat("yyyy-MM-dd").format(updatedClaim.getClaimDate()) + ",");
                        writer.write(updatedClaim.getInsuredPerson() + ",");
                        writer.write(updatedClaim.getCardNumber() + ",");
                        writer.write(new SimpleDateFormat("yyyy-MM-dd").format(updatedClaim.getExamDate()) + ",");
                        writer.write(updatedClaim.getClaimAmount() + ",");
                        writer.write(updatedClaim.getStatus() + ",");
                        // Add more fields as necessary
                        writer.write("\n");
                    }
                } catch (IOException e) {
                    System.out.println("An error occurred while writing to the file: src/Data/claims.txt");
                }

                return;
            }
        }

        System.out.println("No claim found with the given ID.");
    }

    public void viewClaim() {
        System.out.println("Enter the ID of the claim you want to view:");
        String id = scanner.nextLine();

        try (BufferedReader reader = new BufferedReader(new FileReader("src/Data/claims.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String claimId = parts[0];
                Date claimDate = new SimpleDateFormat("yyyy-MM-dd").parse(parts[1]);
                String insuredPerson = parts[2];
                String cardNumber = parts[3];
                Date examDate = new SimpleDateFormat("yyyy-MM-dd").parse(parts[4]);
                double claimAmount = Double.parseDouble(parts[5]);
                ClaimStatus Status = ClaimStatus.valueOf(parts[6]);

                // Create a new claim
                Claim claim = new Claim(claimId, claimDate, insuredPerson, cardNumber, examDate, new ArrayList<>(), claimAmount, Status, null);

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
            System.out.println("An error occurred while reading from the file: src/Data/claims.txt");
        }

        System.out.println("No claim found with the given ID.");
    }

    public void viewAllClaims() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/Data/claims.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String claimId = parts[0];
                Date claimDate = new SimpleDateFormat("yyyy-MM-dd").parse(parts[1]);
                String insuredPerson = parts[2];
                String cardNumber = parts[3];
                Date examDate = new SimpleDateFormat("yyyy-MM-dd").parse(parts[4]);
                double claimAmount = Double.parseDouble(parts[5]);
                ClaimStatus Status = ClaimStatus.valueOf(parts[6]);

                // Create a new claim
                Claim claim = new Claim(claimId, claimDate, insuredPerson, cardNumber, examDate, new ArrayList<>(), claimAmount, Status, null);

                // Print the details of the claim
                System.out.println("ID: " + claim.getId());
                System.out.println("Insured Person: " + claim.getInsuredPerson());
                System.out.println("Card Number: " + claim.getCardNumber());
                System.out.println("Claim Amount: " + claim.getClaimAmount());
                System.out.println("Status: " + claim.getStatus());
                System.out.println("------------------------");
            }
        } catch (IOException | ParseException e) {
            System.out.println("An error occurred while reading from the file: src/Data/claims.txt");
        }
    }

    public void saveToFile(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            for (Customer customer : customers) {
                writer.write(customer.getInsuredPerson() + ",");
                writer.write(customer.getCardNumber() + ",");

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
