// SAP ID:70069779
// Student Name: Haider Ali
// Java Section A
import java.util.Scanner; // Library for input
public class Ex9_5_2 {
    static String userInput(){
        Scanner input = new Scanner(System.in); // scanner object for reading input
        return input.next();
    
        }
        public static void main(String[] args){
            String input;
            int MonthlySalary=0;
            int dailywage=0;
            do{
                System.out.println("Please enter monthly salary");
                input = userInput();
                 try{
                    MonthlySalary = Integer.parseInt(input);
                    if (MonthlySalary < 0) {
                        throw new IllegalArgumentException("Exception! Cannot enter value less than 0");
                    }
                    else{
                        dailywage=MonthlySalary/30;
                    }
                    }
                catch(IllegalArgumentException e){
                        System.out.println(e.getMessage());
                    }
        }while(MonthlySalary<0);
        System.out.println("The daily salary is "+dailywage);
    }
}
