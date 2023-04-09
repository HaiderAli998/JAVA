// SAP ID:70069779
// Student Name: Haider Ali
// Java Section A
import java.util.Scanner; // Library for input
class Ex9_5_1{
    static String userInput(){
        Scanner input = new Scanner(System.in); // scanner object for reading input
        return input.next();
    
        }
    public static void main(String[] args) {
        String input;
        int Array_1[]=new int[12]; // even 
        int Array_2[]= new int[12]; //odd
        int Arr1_count=0;
        int Arr2_count=0;
        int d;
        do{
        System.out.println("Enter either even or odd number");
        input = userInput();
         try{
            d = Integer.parseInt(input);
            if (d > 100) {
                throw new IllegalArgumentException("Exception! Cannot enter value greater than 100");
            }
       
            if(d%2==0){
                Array_1[Arr1_count]=d;
                Arr1_count++;
            }
            else{
                Array_2[Arr2_count]=d;
                Arr2_count++;
            }
          
        }
        catch(ArrayIndexOutOfBoundsException e){
                System.out.println("ArrayIndexOutOfBoundsException");
        }
        catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }while(Arr1_count+Arr2_count<=24);
    int sum=0;
    for(int i=0;i<12;i++){
        sum=sum+Array_1[i]+Array_2[i];
    }
    System.out.println("The sum is "+sum);
}
}