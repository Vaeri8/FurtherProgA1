//Luong Tuan Kiet - s3980288

package Operations;

import Interface.InsuranceProcessManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import Classes.*;

public class InsuranceOperation implements InsuranceProcessManager {

    List<Customer> customers = new ArrayList<>(); // makes custoemrs class level
    Scanner scanner = new Scanner(System.in);

    public void viewAllInsurances() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/Data/InsuranceCards.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String cardNumber = parts[0];
                String cardHolder = parts[1];
                String policyOwner = parts[2];
                Date expirationDate = new SimpleDateFormat("yyyy-MM-dd").parse(parts[3]);

                // Create a new insurance card
                InsuranceCard insuranceCard = new InsuranceCard(cardNumber, cardHolder, policyOwner, expirationDate);

                // Print the details of the insurance card
                System.out.println("Card Number: " + insuranceCard.getCardNumber());
                System.out.println("Card Holder: " + insuranceCard.getCardHolder());
                System.out.println("Policy Owner: " + insuranceCard.getPolicyOwner());
                System.out.println("Expiration Date: " + new SimpleDateFormat("yyyy-MM-dd").format(insuranceCard.getExpirationDate()));
                System.out.println("------------------------");
            }
        } catch (IOException | ParseException e) {
            System.out.println("An error occurred while reading from the file: src/Data/InsuranceCards.txt");
        }
    }
}