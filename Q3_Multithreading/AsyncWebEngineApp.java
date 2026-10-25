package Q3_Multithreading;

public class AsyncWebEngineApp {

    public static void main(String[] args) {

        System.out.println(
            "Main Thread: " + Thread.currentThread().getName()
        );

        System.out.println("Web engine initialized.");
    }
}