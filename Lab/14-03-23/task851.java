// SAP ID:70069779
// Student Name: Haider Ali
// Java Section A
import java.util.Scanner; // Library for input

public class task851{
    static String userInput(){
        Scanner input = new Scanner(System.in); // scanner object for reading input
        return input.next();
    }
    public static void main(String[] args){
        String choice;
        int uc;
            System.out.println("Press 1 to create car.");
            System.out.println("Press 2 to create bike."); 
            System.out.println("Press 3 truck.");
            choice = userInput();
            uc = Character.getNumericValue(choice.charAt(0));
            if(uc==1){
               Car c = new Car();
               c.createVehicle();
               c.displayVehicleStatus();
            }
            else if(uc==2){
              Bike b = new Bike();
              b.createVehicle();
              b.displayVehicleStatus();
            }
            else if (uc==3){
               Truck t = new Truck();
               t.createVehicle();
               t.displayVehicleStatus();
        
            }
    }
 }

    abstract class VehicleFactory{
        String vehicleName;
        int numberOfTyres;
        String veh_colour;
        String veh_class;
        abstract void createVehicle();
        void displayVehicleStatus(){
            System.out.println("This vehicle is a "+   vehicleName);
            System.out.println("This vehicle has "+   numberOfTyres+" tyres");
            System.out.println("This vehicles colour is "+   veh_colour);
            System.out.println("This vehicle belongs to "+  veh_class+" category");
        }

    }
    class Car extends VehicleFactory{
        void createVehicle(){
            veh_class="Sedan";
            veh_colour="Blue";
            vehicleName="Corolla";
            numberOfTyres=4;
            System.out.println("Car successfully created");
        }
    }
    class Bike extends VehicleFactory{
        void createVehicle(){
            veh_class="Heavy Bike";
            veh_colour="Red";
            vehicleName="Hayabusa";
              numberOfTyres=2;
            System.out.println("Bike successfully created");
        }
    }
    class Truck extends VehicleFactory{
        void createVehicle(){
              veh_class="Heavy loader";
              veh_colour="Silver";
              vehicleName="Heavy loader";
              numberOfTyres=12;
            System.out.println("Truck successfully created");
        }
    }

