import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CallAny {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        List<MyThreadCallable> tasks = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            MyThreadCallable myThreadCallable = new MyThreadCallable();
            tasks.add(myThreadCallable);
        }
        int futures = executorService.invokeAny(tasks);

        System.out.println("Какой-то из потоков уже вывел все " + futures + " сообщений");
        System.out.println("Всего сообщений " + MyThreadCallable.getTotalMessages());
        executorService.shutdown();
    }
}