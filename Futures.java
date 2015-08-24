import java.io.BufferedReader;
import java.io.FileReader;
import java.util.concurrent.*;

/**
 * Created by abansal on 8/23/15.
 */
public class Futures {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(1);

        //A callable to run an long running/async operation
        Callable<String> task = () -> {
            TimeUnit.SECONDS.sleep(5);
            return "Hello";
        };

        Future<String> future = executor.submit(task);

        while(!future.isDone()){
            System.out.println("Future not done");
            TimeUnit.SECONDS.sleep(1);
        }
           System.out.print("Future Done! Result: " + future.get());

        executor.shutdownNow();
    }
}
