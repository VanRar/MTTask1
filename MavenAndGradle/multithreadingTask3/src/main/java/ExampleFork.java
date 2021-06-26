import java.util.concurrent.RecursiveTask;

public class ExampleFork extends RecursiveTask<Long> {
    int[] array;
    int start;
    int end;

    public ExampleFork(int start, int end, int[] array) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    private long forkTasksAndGetResult() {
        final int middle = (end - start) / 2 + start;
        // Создаем задачу для левой части диапазона
        ExampleFork task1 = new ExampleFork(start, middle, array);
        // Создаем задачу для правой части диапазона
        ExampleFork task2 = new ExampleFork(middle, end, array);
        // Запускаем обе задачи в пуле
        invokeAll(task1, task2);
        // Суммируем результаты выполнения обоих задач
        return task1.join() + task2.join();

    }

    @Override
    protected Long compute() {
        final int diff = end - start;
        switch (diff) {
            case 0:
                return 0L;
            case 1:
                return (long) array[start];
            case 2:
                return (long) (array[start] + array[start + 1]);
            default:
                return forkTasksAndGetResult();
        }
    }
}
