import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;

public class Main {
    static final int MAX_VALUE = 1000;
    static final int LENGTH = 1_000_000_000;

    public static void main(String[] args) {
        int[] array = new int[LENGTH];
        generateArray(array);
        long sumOne = sumSingle(array);
        // ExampleFork exampleFork = new ExampleFork(0, array.length, array);

        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());

        Instant start = Instant.now(); //можно брать системное время - System.currentTimeMillis(), но вроде как с 8ой явы рекомендуется применять Instant
        long sumM = pool.invoke(new ExampleFork(0, array.length, array));
        Instant finish = Instant.now();

        long timeWork = Duration.between(start, finish).toMillis();
        System.out.println("Время выполнения в многопоточном режиме = " + timeWork + " мс");


//        удиительно, но при LENGTH = 1_000_000_000 время выполнения в многопоточном режиме окалаось в разы больше, скорее всего дело в разбиении массива
//        и, наверное в том, что создаётся много потоков, больше чем есть в действительности
//        Время выполнения в один поток = 278 мс
//        Время выполнения в многопоточном режиме = 4504 мс (далее, да и везде - результат при решении по примеру из презентации)

//        при LENGTH = 1_000_000;
//        Время выполнения в один поток = 1 мс
//        Время выполнения в многопоточном режиме = 250 мс

//        при LENGTH = 1_000;
//        Время выполнения в один поток = 0 мс
//        Время выполнения в многопоточном режиме = 3 мс

        //попробуем сделать ForkJoinPool исходя из возможного кол-ва потоков


        start = Instant.now(); //можно брать системное время - System.currentTimeMillis(), но вроде как с 8ой явы рекомендуется применять Instant
        long sumMP = pool.invoke(new MyForkjoinPool(0, array.length, array));
        finish = Instant.now();

        timeWork = Duration.between(start, finish).toMillis();
        System.out.println("Время выполнения в многопоточном режиме  с привязой к кол-ву потоков= " + timeWork + " мс");

//        ч.т.д. оптимально бить кол-во операций на возможности системы, то есть на кол-во потоков, результат выполнения при LENGTH = 1_000_000_000:
//        Время выполнения в один поток = 285 мс
//        Время выполнения в многопоточном режиме = 4135 мс
//        Время выполнения в многопоточном режиме  с привязой к потокам= 113 мс

//        но при небольших объёмах один поток всё равно быстрее, так как кол-во операций в итоге получается меньше, например при LENGTH = 1_000_000:
//        Время выполнения в один поток = 1 мс
//        Время выполнения в многопоточном режиме = 256 мс
//        Время выполнения в многопоточном режиме  с привязой к потокам= 14 мс

        //для проверки
        System.out.println(sumOne);
        System.out.println(sumM);
        System.out.println(sumMP);

    }

    public static void generateArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * MAX_VALUE);
        }
    }

    public static long sumSingle(int[] array) {
        long sum = 0;
        Instant start = Instant.now(); //можно брать системное время - System.currentTimeMillis(), но вроде как с 8ой явы рекомендуется применять Instant
        for (int i : array) {
            sum += i;
        }
        Instant finish = Instant.now();
        long timeWork = Duration.between(start, finish).toMillis();
        System.out.println("Время выполнения в один поток = " + timeWork + " мс");// всё таки в нано больше видно изменений, хотя увеличил на 3 порядка массив и норм в миливремя выполнения в среднем 283 мс
        return sum;
    }

}
