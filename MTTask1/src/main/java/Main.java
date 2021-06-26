public class Main {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("Создаю потоки...");

        ThreadGroup mainGroup = new ThreadGroup("main group");
        MyThread myThread0 = new MyThread();
        MyThread myThread1 = new MyThread();
        MyThread myThread2 = new MyThread();
        MyThread myThread3 = new MyThread();
        //можно номер потока парсить и выводить, но сказали не хардкодить, так что перепишем имя
        myThread0.setName("Поток 0");
        myThread1.setName("Поток 1");
        myThread2.setName("Поток 2");
        myThread3.setName("Поток 3");

        final Thread thread0 = new Thread(mainGroup, myThread0);
        final Thread thread1 = new Thread(mainGroup, myThread1);
        final Thread thread2 = new Thread(mainGroup, myThread2);
        final Thread thread3 = new Thread(mainGroup, myThread3);

        thread0.start();
        thread1.start();
        thread2.start();
        thread3.start();

        Thread.sleep(10000);
        // TimeUnit.SECONDS.sleep(10);

        System.out.println("Завершаю потоки.");
        mainGroup.interrupt();
    }
}