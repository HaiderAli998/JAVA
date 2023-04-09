import java.util.*;

public class AutomaticFunctionRunner {
    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new FunctionRunner(),0, 10 * 100); // schedule the function to run after 1 minute
    }

    static class FunctionRunner extends TimerTask {
        public void run() {
            // call the function that you want to run automatically after 1 minute
            System.out.println("Function called automatically after 1 minute");
        }
    }
}

