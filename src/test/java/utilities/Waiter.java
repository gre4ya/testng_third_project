package utilities;

public class Waiter {

    public static void pause(int seconds){
        try {
            Thread.sleep(1000 * seconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
