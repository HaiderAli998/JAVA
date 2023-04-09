// SAP ID:70069779
// Student Name: Haider Ali
// Java Section A
import java.util.Scanner; // Library for input
public class bank{
    static String userInput(){
        Scanner input = new Scanner(System.in); // scanner object for reading input
        return input.next();
    }

    public static void main(String[] args){
        int userOP;
        Customer cust1; // the "new" customer
        Customer custb = new Customer(5433,"Haider",24); // the "existing" customer
        do {
        userOP = newOrOldCustomer(); // calls the UI for asking if old or new customer
        if(userOP==1){
            performCustomerOP(custb); // calls UI for customer operations
        }
        if (userOP==2){
            cust1=new Customer(); // creates new customer 
            System.out.println("User with customer id "+cust1.getCustomerId()+"successfully created");
            performCustomerOP(cust1);
            
        }
    }while(userOP!=3);
    }

    static void performCustomerOP(Customer userOP){
        String choice;
        int uc;
        do {
        System.out.println("Press 1 to view customer info");
        System.out.println("Press 2 to view account balance."); 
        System.out.println("Press 3 to deposit amount."); 
        System.out.println("Press 4 to withdraw amount");
        System.out.println("Press 5 to Check eligibility for psl ticket");
        System.out.println("Press 6 to edit customer details");
        System.out.println("Press 7 to exit to logout and return to main menu");
        choice = userInput(); // gets string input from scanner class
        uc = Character.getNumericValue(choice.charAt(0)); // converts string to character and gets the integer option

        if(uc==1){
            System.out.println("Customer cnic is "+userOP.getCustomerCnic());
            System.out.println("Customer name is "+userOP.getCustomerName());
            System.out.println("Customer age is "+userOP.getCustomerAge());
        }
        else if (uc==2){
            System.out.println("Account balance is "+userOP.acc.getAccountBalance());

        }
        else if (uc==3){
            System.out.println("Please enter the amount to deposit ");
            choice = userInput();
            double d = Double.parseDouble(choice);
            userOP.acc.Deposit(d);

        }
        else if(uc==4){
            System.out.println("Please enter the amount to withdraw ");
            choice = userInput();
            double d = Double.parseDouble(choice);
            userOP.acc.Withdraw(d);
        }
        else if(uc==5){
            if(userOP.acc.freePslTicket()){
                System.out.println("You are eligible for psl ticket");
            }
            else{
                System.out.println("You are not eligible for psl ticket");
            }
        }
        else if(uc==6){
            editCustomerOP(userOP);
        }
    }while (uc!=7); 
    }
    public static class Account{
        static int all_account_id=0;
        int account_id =0;
        double account_balance =0.0;
        Account(){
            this.account_id=++all_account_id;
        }
        
        void Deposit(double amount){
            this.account_balance+=amount;

        }
        void Withdraw(double amount){
            if (this.account_balance ==0){
                System.out.println("Invalid amount. You have "+this.account_balance+"left ");
            }
            else if (amount>this.account_balance){
                System.out.println("Invalid amount. You have "+this.account_balance+"left ");
            }
            else {
                this.account_balance-=amount;
            }
        }
        double getAccountBalance(){
            return this.account_balance;
        }
        boolean freePslTicket(){
            if(account_balance>100000.00){
                return true;
            }
            else
            return false;
        }

    }
    public static class Customer{
        static int all_cust_id =0; // easier to assign customer ids as it tracks the customer ids
        int cust_id =0;
        int cnic;
        String cust_name;
        int cust_age;
        Account acc = new Account(); // makes a new account for the customer
        Customer(int cnic, String cust_name,int cust_age){
            this.cust_id=++all_cust_id; // makes a new id for the newly created customer
            this.cnic = cnic;
            this.cust_name=cust_name;
            this.cust_age=cust_age;
        }
        Customer(){
            this.cust_id=++all_cust_id;
            this.cnic=0;
        }
        void setCustomerName(String cust_name){
            this.cust_name=cust_name;
        }
        void setCustomerAge(int cust_age){
            this.cust_age=cust_age;
        }
        void setCustomerCnic(int cnic){
            this.cnic=cnic;
        }
        int getCustomerId(){
            return this.cust_id;
        }
        String getCustomerName(){
            return this.cust_name;
        }
        int getCustomerCnic(){
            return this.cnic;
        }
        int getCustomerAge(){
            return this.cust_age;
        }

    }
    static int newOrOldCustomer(){
        String choice;
        int uc;
        System.out.println("Are you an existing customer or a new customer?");
        System.out.println("Press 1 for existing customer.");
        System.out.println("Press 2 for new customer. ");
        System.out.println("Press 3 to exit");
        choice = userInput();
        uc = Character.getNumericValue(choice.charAt(0));
        return uc;
    }
    static void editCustomerOP(Customer userOP){
        String choice;
        int uc;
        do {
        System.out.println("Press 1 to edit customer name.");
        System.out.println("Press 2 to edit customer age."); 
        System.out.println("Press 3 to edit customer cnic.");
        System.out.println("Press 4 to return to main menu.");
        choice = userInput();
        uc = Character.getNumericValue(choice.charAt(0));
        if(uc==1){
            System.out.println("Please enter new customer name.");
            choice = userInput();
            userOP.setCustomerName(choice);
        }
        else if(uc==2){
            System.out.println("Please enter new customer age.");
            choice = userInput();
            userOP.setCustomerAge(Integer.parseInt(choice));
        }
        else if (uc==3){
            System.out.println("Please enter new customer cnic.");
            choice = userInput();
            userOP.setCustomerCnic(Integer.parseInt(choice));
        }
    }while(uc!=4);
        
    }



}