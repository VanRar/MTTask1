import java.util.concurrent.RecursiveTask;

public class MyForkjoinPool extends RecursiveTask<Long> {
    int[] array;
    int start;
    int end;
    final int perfomance;//будем считать соотношение задач и потоков, величина нужна постоянная на всю задачу

    public MyForkjoinPool(int start, int end, int[] array) {
        this.array = array;
        this.start = start;
        this.end = end;
        this.perfomance = array.length / Runtime.getRuntime().availableProcessors();
    }

    private long forkTasksAndGetResult() {
        final int middle = (end - start) / 2 + start;
        // Создаем задачу для левой части диапазона
        MyForkjoinPool task1 = new MyForkjoinPool(start, middle, array);
        // Создаем задачу для правой части диапазона
        MyForkjoinPool task2 = new MyForkjoinPool(middle, end, array);
        // Запускаем обе задачи в пуле
        invokeAll(task1, task2);
        // Суммируем результаты выполнения обоих задач
        return task1.join() + task2.join();

    }

    @Override
    protected Long compute() {
        long sumArray = 0;
        if ((end - start) <= perfomance) {
            for (int i = start; i < end; i++) {
                sumArray += array[i];
            }
            return sumArray;
        } else {
            return forkTasksAndGetResult();
        }
    }
}
