public class MyThread extends Thread {

    @Override
    public void run() {
        super.run();
        try {
            while (!isInterrupted()) {
                Thread.sleep(2500);
                System.out.printf("Я %s. Всем привет!\n", getName());
            }
        } catch (InterruptedException ignored) {
        } finally {

            System.out.printf("%s завершен\n", getName());
        }
    }
}