import java.io.BufferedReader;
import java.io.FileReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by abansal on 8/23/15.
 */
public class FileRead {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();

        Runnable readFileTask = () -> {
            try {
                BufferedReader br = new BufferedReader(new FileReader("/Users/abansal/dev/gitebay/IK/out/production/Test/test.txt"));
                String currentLine;
                while((currentLine = br.readLine()) != null){
                    queue.put(currentLine);
                }
            }catch (Exception e){ e.printStackTrace();}
        };

        Runnable dequeueTask = () -> {
            try {
                while(1>0) {
                    //Do heavy duty task
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(queue.take());
                }
            } catch (InterruptedException e) { e.printStackTrace();}
        };

        executor.submit(readFileTask);
        executor.submit(dequeueTask);
        executor.submit(dequeueTask);
    }
}
