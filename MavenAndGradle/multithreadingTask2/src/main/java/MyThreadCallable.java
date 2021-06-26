import java.util.concurrent.Callable;

public class MyThreadCallable extends Thread implements Callable<Integer> {
    private static int totalMessages = 0;//как вариант подсчета кол-ва всех выведенных сообщений в случае с invokeAny
    private int count = 0;//что бы понять как call возвращает значение

    public static int getTotalMessages() {
        return totalMessages;
    }

    @Override
    public Integer call() {
        try {
            while (count < 5) {
                Thread.sleep(250);
                System.out.printf("Я %s. Всем привет! My count = %s\n", Thread.currentThread().getName(), count);
                count++;
                totalMessages++;
            }
        } catch (InterruptedException ignored) {
        } finally {

            System.out.printf("%s завершен\n", getName());
        }
        return count;
    }
}