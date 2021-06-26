import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class TestCallable {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        List<Future<Integer>> tasks = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            MyThreadCallable myThreadCallable = new MyThreadCallable();
            Future<Integer> submit = executorService.submit((Callable<Integer>) myThreadCallable);
            tasks.add(submit);
        }

        int totalMessages = 0;
        for (Future<Integer> f : tasks) {
            totalMessages += f.get();
        }

        System.out.println("Всего сообщений \"Всем привет!\" было выведено " + totalMessages);
        executorService.shutdown();

    }
}
