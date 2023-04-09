// SAP ID:70069779
// Student Name: Haider Ali
// Java Section A
//Assignment 2
import java.util.Timer; // library for the timer class
import java.util.Scanner; // Library for input
import java.util.*; // automatically fetches missing libraries
public class as2{
    // note the code snippet for functionRunner is taken from stack overflow 
    //sniipet start
    // static class FunctionRunner extends TimerTask {
    //     public void run() {
    //         // call the function that you want to run automatically after 1 minute
    //         System.out.println("Function called automatically after 1 minute");
    //     }
    // }

    // snippet end
    static String userInput(){
        Scanner input = new Scanner(System.in); // scanner object for reading input
        return input.next(); 
    }
    static int getIntChoice(){
        String choice;
        int uc;
        choice = userInput(); // gets string input from scanner class
        uc = Character.getNumericValue(choice.charAt(0)); // converts string to character and gets the integer at first position
        return uc;
    }
    static int generateRandomPin(){
        int randomPin = (int) (Math.random()*900)+100; // uses the math random function to create a random number and make a 3 digit pin
        return randomPin;
    }    
    

    public static void main(String[] args){
        int uc = 0;
        int index =-1;
        // Timer timer = new Timer();
        // timer.schedule(new FunctionRunner(),0, 60 * 1000); // schedule the function to run after 1 minute
        List<Customer> customerList = new ArrayList<>(); // creates a new dynamic array for Customer objects
        do{
            uc=newOrOldCustomer();
            if (uc==1){
                int choice = 0;
                choice = CustomerDetailsOP(customerList);
                if(choice==4){ // customer entered details and accepted terms and condition. move to screen 4
                    displayCustomerInfo(customerList.get(customerList.size()-1));

                }   
                else{ // customer entered details and didnt accept terms and condition. move to screen 1 using do while loop


                }
            }
            else if (uc==2){ // customer is an existing customer. ask for pin
                if(customerList.size()>0){ // checks if there are any customers in the database
                index = customerMatchPin(customerList,askCustomerPin()); // checks if the customer has entered correct pin
                if (index!=-1){// only continues if customer enters correct pin
                    performCustomerOP(customerList.get(index),customerList); // passes the customer in the dynamic array to perform operations

                }
                else{
                    System.out.println("Please renter pin. Pin incorrect.");
                }
                
                }
                if(customerList.size()==0){
                    System.out.println("Sorry there are no customers in the database. Please create a new account as a new Customer.");
                }
            }
            }while(uc!=3); // while user doesnt want to exit from main menu (screen 1)
    }

    static int newOrOldCustomer(){ // screen 1

        System.out.println("Are you an existing customer or a new customer?");
        System.out.println("Press 1 for new customer.");
        System.out.println("Press 2 for existing customer. ");
        System.out.println("Press 3 to exit");
        return getIntChoice();
    }
    static boolean termsAndConditions(){ // screen 3
        Scanner input = new Scanner(System.in); // scanner object for reading input
        int choice;
        System.out.println("Terms and conditions:");
        System.out.println("Press 1 to accept the Terms and Conditions");
        System.out.println("Press 2 to exit");
        choice=input.nextInt();
        switch(choice){
            case 1:
                return true;
            default:
                break;
        }

        return false;
    }
    static int CustomerDetailsOP(List<Customer> customerList){ // screen 2
        String name;
        String email;
        System.out.println("Please enter your name.");
        name = userInput();
        System.out.println("Please enter your email."); 
        email = userInput();
        if(termsAndConditions()){ // checks if the customer accepts the terms and conditions
            customerList.add(new Customer(name,email)); // creates a new customer object with the customers information if customer accepts terms 
            return 4; // to return to the welcome customer screen
        }
        else
            return 1; // to return to the main menu
    }
    static void displayCustomerInfo(Customer c){ // screen 4
        System.out.println("Message: You are now Account-Holder Customer");
        System.out.println("Your PIN-Code is "+c.getCustomerPin());
        System.out.println("Your ID is "+c.getCustomerId());
    }
    static int askCustomerPin(){ //screen 5
        String choice;
        int inputPin;
        System.out.println("Please enter Pin-Code ");
        choice = userInput();
        inputPin = Integer.parseInt(choice); // parses the string input to an integer input
        return inputPin;
    }
    static int customerMatchPin(List<Customer> customerList, int pin){ // checks which customer index matches with pin
        int index =-1;
        for (int i = 0; i < customerList.size(); i++) {
            if (customerList.get(i).getCustomerPin()==pin) {
                index = i;
                break;
            }
        }
        return index;
    }

    static void performCustomerOP(Customer userOP,List<Customer> customerList){ // screen 6
        String choice;
        int uc;
        do {
        System.out.println("Welcome "+userOP.getCustomerName());    
        System.out.println("Balance     Press-1"); 
        System.out.println("Deposit     Press-2"); 
        System.out.println("Withdraw    Press-3");
        System.out.println("Loan        Press-4"); 
        System.out.println("Transfer    Press-5"); // need to work on
        System.out.println("Exit        Press-6");
        uc = getIntChoice();

        if (uc==1){
            System.out.println("Account balance is "+userOP.acc.getAccountBalance());

        }
        else if (uc==2){
            System.out.println("Please enter the amount to deposit ");
            choice = userInput(); // takes the input from user as a string
            double d = Double.parseDouble(choice); // parses the input string into a double value
            userOP.acc.Deposit(d);
            System.out.println("New balance is "+userOP.acc.getAccountBalance());
        }
        else if(uc==3){
            System.out.println("Please enter the amount to withdraw ");
            choice = userInput(); // takes the input from user as a string
            double d = Double.parseDouble(choice); // parses the input string into a double value
            userOP.acc.Withdraw(d);
            System.out.println("New balance is "+userOP.acc.getAccountBalance());
        }
        else if(uc==4){
            loanDetails(userOP);
            System.out.println("New balance is "+userOP.acc.getAccountBalance());
        }
        else if(uc==5){
         customerTransfer(userOP,customerList);
        }
            }while (uc!=6); 
        }

        static void loanDetails(Customer c){
            String choice;
            int uc;
            if(c.acc.existingLoan()==false){
                System.out.println("Press 1 to get new loan");
                System.out.println("Press any other number to return to previous menu");
                uc = getIntChoice();
                if (uc==1){
                    System.out.println("Please enter the amount to take loan ");
                    choice = userInput(); // takes the input from user as a string
                    double d = Double.parseDouble(choice); // parses the input string into a double value
                    c.acc.getLoan(d);
                }

            }
            else{
                System.out.println("You already have an existing loan");
                System.out.println("You have "+c.acc.getLoanAmount()+" remaining left to pay");
                System.out.println("Press 1 to pay from account.");
                System.out.println("Press any other number to return to previous menu");
                uc = getIntChoice();
                if (uc==1){
                    c.acc.payOffLoan();
                    
             }
            }
        }
        static void customerTransfer(Customer userOP,List<Customer> customerList){
            String choice;
            int id;
            int uc;
            int pin;
            System.out.println("Please enter the customer id of the customer to transfer to ");
            choice = userInput();
            id = (Integer.parseInt(choice)-1); // indexing from 0 so id 1 is at 0 index
            if(customerList.get(id)!=null){ // if the customer id exists
                Customer c = customerList.get(id);
                System.out.println("Please enter the amount to deposit ");
                choice = userInput(); // takes the input from user as a string
                double d = Double.parseDouble(choice); // parses the input string into a double value
                System.out.println("Press pin to confirm you want to send "+ d +" to "+c.getCustomerName() +" with id "+c.getCustomerId());
                System.out.println("Press other integer number to cancel");
                pin = askCustomerPin();
                if (pin==userOP.getCustomerPin()){
                    userOP.acc.Withdraw(d);
                    c.acc.Deposit(d);
                    System.out.println("New balance is "+userOP.acc.getAccountBalance());
            }
            else{
                System.out.println("Transaction cancelled");
            }
        }


        }
    }


    class Account{
        static int all_account_id=0;
        int account_id =0;
        double account_balance =0.0;
        double loan_amount =0.0;
        double profit_rate =1.015;// 1.5 %
        double loan_rate =0.0;// 0 as no loan taken at the beginning
        boolean loan;
        Account(){
            account_id=++all_account_id;
            loan = false;
        }
        double getAccountBalance(){
            return account_balance;
        } 
        void Deposit(double amount){
            account_balance+=amount;

        }
        void Withdraw(double amount){
            if (account_balance ==0){
                System.out.println("Invalid amount. You have "+account_balance+"left ");
            }
            else if (amount>this.account_balance){
                System.out.println("Invalid amount. You have "+account_balance+"left ");
            }
            else {
                account_balance-=amount;
            }
        }
        boolean existingLoan(){
            return loan;
        }
        double getLoanAmount(){
            return loan_amount;
        }
        double getLoanRate(){
            return loan_rate;
        }

        void getLoan(double amount){
            if (amount>this.account_balance){
                System.out.println("Invalid amount. You have "+account_balance+"left ");
            }
            else {
                loan=true;
                loan_amount=amount;
                loan_rate = 0.015;
            }
                

        }
        void payOffLoan(){
            if(loan_amount>account_balance){
                System.out.println("Insufficient amount. You have "+account_balance+"left ");
            }
            else{
                loan=false;
                loan_amount =0.0;
                loan_rate = 0.0;
                System.out.println("Loan successfully paid off ");
            }
        }
        void generateProfit(){
            account_balance = account_balance*profit_rate; // generates a profit of 1.5%
        }
        void adjustLoan(){
            if(loan == true){
                account_balance = account_balance - (loan_rate*loan_amount); // deducts loan amount of 1.5% from balance
                loan_amount = loan_amount - (loan_rate*loan_amount); // adjusts the value of loan amount after deducting from balance
            }
        }

    }
    class Customer{
        static int all_cust_id =0; // easier to assign customer ids as it tracks the customer ids
        int cust_id =0;
        int pin;
        String cust_name;
        String cust_email;
        Account acc = new Account(); // makes a new account for the customer
        Customer(String cust_name,String cust_email){
            cust_id=++all_cust_id; // makes a new id for the newly created customer
            this.cust_name=cust_name;
            this.cust_email=cust_email;
            this.pin = as2.generateRandomPin(); // generates a random pin and assigns it
        }
        Customer(){
            this.cust_id=++all_cust_id;
            this.pin = as2.generateRandomPin(); // generates a random pin and assigns it
        }
        void setCustomerName(String cust_name){
            this.cust_name=cust_name;
        }
        void setCustomerEmail(String cust_email){
            this.cust_email=cust_email;
        }
        void setCustomerPin(int pin){
            this.pin=pin;;
        }
        int getCustomerId(){
            return cust_id;
        }
        String getCustomerName(){
            return cust_name;
        }
        String getCustomerEmail(){
            return cust_email;
        }
        int getCustomerPin(){
            return pin;
        }
    }
    




