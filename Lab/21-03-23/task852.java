// SAP ID:70069779
// Student Name: Haider Ali
// Java Section A
import java.util.Scanner; // Library for input
class task852{
    public static void main(String[] args){
        String input;
        Calculation cal= new Calculation();
        int result = 0;
        System.out.println("Enter the first number to perform calculation");
        int num1;
        input = userInput();
        num1 = Character.getNumericValue(input.charAt(0));
        System.out.println("Enter the second number to perform calculation"); 
        int num2;
        input = userInput();
        num2 = Character.getNumericValue(input.charAt(0));
        System.out.println("Enter the operand to perform calculation");
        input = userInput();
        char c = input.charAt(0);
        switch(c) {
            case '+':
              cal = new Calculation();
              result=cal.add(num1,num2);
              break;
            case '-':
            cal = new Calculation();
            result = cal.subtract(num1,num2);
              break;
            case '/':
              cal = new Calculation();
              result = cal.divide(num1,num2);
                break;
            case '*':
                cal = new Calculation();
                result = cal.multiply(num1,num2);
                  break;
            
            default:
                System.out.println("Invalid operand");
                break;
            }
          System.out.println("The result is "+ result);
          if(cal.isPrime(result)){
            System.out.println("The result is prime");
          }
          else{
            System.out.println("The result is not prime");
          }
          
        }  

static String userInput(){
    Scanner input = new Scanner(System.in); // scanner object for reading input
    return input.next();

    }
}
interface Multiplication{
    int multiply(int a,int b);
}
interface Subtraction{
    int subtract(int a,int b);

}
interface Addition{
    int add(int a,int b);
}
interface Division{
    int divide(int a,int b);
}
class Calculation implements Multiplication,Subtraction,Addition,Division{
    
    public int multiply(int a,int b){
        return a*b;
    }
    public int divide(int a,int b){
        return a/b;
    }
    public int add(int a,int b){
        return a+b;
    }
    public int subtract(int a,int b){
        return a-b; 
    }
    boolean isPrime(int n) {  
        if (n <= 1) {  
            return false;  
        }  
        for (int i = 2; i < Math.sqrt(n); i++) {  
            if (n % i == 0) {  
                return false;  
            }  
        }  
        return true;  
    }  

}