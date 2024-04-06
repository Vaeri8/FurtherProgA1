//Luong Tuan Kiet - s3980288

package Operations;

import Classes.Claim;
import Interface.CustomerProcessManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import Classes.Customer;


public class CustomerOperation implements CustomerProcessManager {

    List<Customer> customers = new ArrayList<>(); // makes custoemrs class level
    Scanner scanner = new Scanner(System.in);

    public void viewAllCustomers() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/Data/customers.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String id = parts[0];
                String insuredPerson = parts[1];
                String cardNumber = parts[2];

                System.out.println("ID: " + id);
                System.out.println("Insured Person: " + insuredPerson);
                System.out.println("Card Number: " + cardNumber);
                System.out.println("------------------------");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading from the file: src/Data/customers.txt");
        }
    }
}
