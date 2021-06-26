import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableInvokeAll {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ExecutorService executorService = Executors.newFixedThreadPool(4);

        List<MyThreadCallable> tasks = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            MyThreadCallable myThreadCallable = new MyThreadCallable();
            tasks.add(myThreadCallable);
        }

        List<Future<Integer>> futures = executorService.invokeAll(tasks);

        int totalMessages = 0;
        for (Future<Integer> f : futures) {
            totalMessages += f.get();
        }

        System.out.println("Всего сообщений \"Всем привет!\" было выведено " + totalMessages);
        executorService.shutdown();
    }
}