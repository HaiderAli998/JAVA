
// SAP ID:70069779
// Student Name: Haider Ali
// Java Section A
import java.io.FileWriter;
import java.io.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner; // Library for input

public class file_handling {
    static String filenam = "Abc.txt";

    static String userInput() {
        Scanner input = new Scanner(System.in); // scanner object for reading input
        return input.next();
    }

    static int getIntChoice() {
        String choice;
        int uc;
        choice = userInput(); // gets string input from scanner class
        uc = Character.getNumericValue(choice.charAt(0)); // converts string to character and gets the integer at first
                                                          // position
        return uc;
    }

    static int newOrOldCustomer() { // screen 1 asking for old or new customer

        System.out.println("Are you an existing customer or a new customer?");
        System.out.println("Press 1 for sign-in.");
        System.out.println("Press 2 for sign-up. ");
        System.out.println("Press 3 for sign in History");
        System.out.println("Press 4 to view total balance of all accounts");
        System.out.println("Press 5 to exit");
        return getIntChoice();
    }

    static boolean writeCustomerToFile(Customer newCust, boolean appen) {
        try {
            FileWriter fileWriter = new FileWriter(filenam, appen); // passing true to show file is being appended
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter); // making a buffer for writing text in a
                                                                            // file
            PrintWriter printWriter = new PrintWriter(bufferedWriter);
            printWriter.println(newCust.toCSVString()); // converting each attribute of the customer into a comma
                                                        // seperated value so that each customer fits in one line
            printWriter.close();
            bufferedWriter.close();
            fileWriter.close();

        } catch (IOException e) {
            System.out.println(e);
        }
        return true;
    }

    static List<Customer> readCustomersFromFile() {
        List<Customer> customers = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(filenam);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) { // reads the whole line
                String[] tokens = line.split(","); // seperates each attribute using a comma and saves it as a token
                Customer customer = new Customer(tokens[0], tokens[1], tokens[2], Double.parseDouble(tokens[3]));
                customers.add(customer);
            }
            bufferedReader.close();
            fileReader.close();

        } catch (IOException e) {
            System.out.println("No user exists in the system");
        }
        return customers;
    }

    static int existingCustomer(String searchNameString) {
        List<Customer> customers = readCustomersFromFile();
        int count = -1;
        for (Customer customer : customers) {
            count += 1;
            if (customer.getCustomerName().equals(searchNameString)) {
                return count;
            }
        }
        return -1;
    }

    static Customer customerSignIn() {
        String existingUser;
        String passwordToMatch;
        System.out.println("Please enter your username.");
        existingUser = userInput();
        List<Customer> customers = readCustomersFromFile();
        Customer matchingCustomer = null;
        int customer_index = existingCustomer(existingUser);
        if (customer_index != -1) {
            matchingCustomer = customers.get(customer_index);
        }

        if (matchingCustomer != null) {
            do {
                System.out.println("Please enter your password.");
                passwordToMatch = userInput();

            } while (!matchingCustomer.getCustomerPassword().equals(passwordToMatch));
            return matchingCustomer;
        } else {
            System.out.println("No matching customer found.");
        }
        return matchingCustomer;
    }

    static void customerSignUp() {
        String username;
        String password;
        String email;
        do {
            System.out.println("Please enter your unique username.");
            username = userInput();
        } while (existingCustomer(username) != -1);
        do {
            System.out.println("Please enter your password.");
            password = userInput();
            if (username.equals(password))
                System.out.println("The username and password cannot be the same. Please renter a different password");
        } while (username.equals(password));

        System.out.println("Please enter your email.");
        email = userInput();
        Customer newCust = new Customer(username, password, email);
        do {
        } while (!writeCustomerToFile(newCust, true)); // keeps trying to write customer until successfull
        System.out.println("Customer data has been saved.");
    }

    static void updateCustomerBalance(Customer customerToUpdate) {
        // Read all customers from the file
        List<Customer> customerz = readCustomersFromFile();
        int count = existingCustomer(customerToUpdate.getCustomerName());
        // Update the balance of the specified customer by replacing the customer at
        // that index position in the list read from the file
        customerz.set(count, customerToUpdate);
        try {
                       // Write all customers back to the file
                       FileWriter fileWriter = new FileWriter(filenam);
                       BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                       PrintWriter printWriter = new PrintWriter(bufferedWriter);
                       for (Customer customer : customerz) {
                           printWriter.println(customer.toCSVString());
                       }
                       printWriter.close();
                       bufferedWriter.close();
                       fileWriter.close();
               
                       System.out.println("Customer balance updated successfully.");
               
                   } catch (IOException e) {
                       System.out.println("Error updating customer balance.");
    }
}

    public static void main(String[] args) {
        List<String> customers = new ArrayList<>();
        int uc = 0;
        do {
            uc = newOrOldCustomer();
            if (uc == 1) {
                Customer c = customerSignIn();
                if (c != null) {
                    performCustomerOP(c);
                    customers.add(c.getCustomerName()); // adds the customer that successfully logins in and performs
                                                        // operations
                } else {
                    System.out.println("User does not exist. Please make a new account");
                }
            }
            if (uc == 2) {
                customerSignUp(); // calls the method for customer signup
            }
            if (uc == 3) {
                System.out.println("The following users have logged in since the program ran");
                if (customers.size() > 0)
                    for (String customer : customers) {
                        System.out.println(customer);
                    }
            }
            if (uc == 4) {
                double total_cust_balance =0.0;
                List<Customer> customerBalance = readCustomersFromFile();
                for (Customer customer:customerBalance){
                    total_cust_balance+=customer.getAccountBalance();
                }
                System.out.println("The total balance of all users signed up is " + total_cust_balance);
            }

        } while (uc != 5); // while user doesnt want to exit from main menu (screen 1)
    }

    static void performCustomerOP(Customer userOP) { // screen 6
        String choice;
        int uc;
        do {
            System.out.println("Welcome " + userOP.getCustomerName());
            System.out.println("Balance     Press-1");
            System.out.println("Deposit     Press-2");
            System.out.println("Exit        Press-3");
            uc = getIntChoice();
            if (uc == 1) {
                System.out.println("Account balance is " + userOP.getAccountBalance());

            } else if (uc == 2) {
                System.out.println("Please enter the amount to deposit ");
                choice = userInput(); // takes the input from user as a string
                double d = Double.parseDouble(choice); // parses the input string into a double value
                userOP.Deposit(d);
                updateCustomerBalance(userOP);
                System.out.println("New balance is " + userOP.getAccountBalance());
            }
        } while (uc != 3);
    }

}

class Customer {
    String cust_username;
    String password;

    String cust_email;
    double account_balance = 0.0;

    double getAccountBalance() {
        return account_balance;
    }

    void Deposit(double amount) {
        account_balance += amount;

    }

    Customer(String cust_username, String cust_pass, String cust_email) {
        this.cust_username = cust_username;
        this.cust_email = cust_email;
        this.password = cust_pass;
    }

    Customer(String cust_username, String cust_pass, String cust_email, double balance) {
        this.cust_username = cust_username;
        this.cust_email = cust_email;
        this.password = cust_pass;
        this.account_balance = balance;
    }

    String getCustomerName() {
        return cust_username;
    }

    String getCustomerEmail() {
        return cust_email;
    }

    String getCustomerPassword() {
        return password;
    }

    public String toCSVString() {
        return String.format("%s,%s,%s,%.2f", cust_username, password, cust_email, account_balance);
    }
}