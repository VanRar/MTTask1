import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // Runtime.getRuntime().availableProcessors() - выдает кол-во процессоров ( максимальное возможное кол-во потоков)
        final ExecutorService threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        Callable<Integer> myCallable0 = new MyThreadCallable();
        Callable<Integer> myCallable1 = new MyThreadCallable();
        Callable<Integer> myCallable2 = new MyThreadCallable();
        Callable<Integer> myCallable3 = new MyThreadCallable();

        final Future<Integer> task0 = threadPool.submit(myCallable0);
        final Future<Integer> task1 = threadPool.submit(myCallable1);
        final Future<Integer> task2 = threadPool.submit(myCallable2);
        final Future<Integer> task3 = threadPool.submit(myCallable3);

        final int result0 = task0.get();
        final int result1 = task1.get();
        final int result2 = task2.get();
        final int result3 = task3.get();

        int totalMessages = result0 + result1 + result2 + result3;

        System.out.println("Всего сообщений \"Всем привет!\" было выведено " + totalMessages);

        threadPool.shutdown();
    }
}