import thread.MyCallable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //Создаем 2 пула, один для всех задач, другой для 1 задачи
        final ExecutorService threadPoolAll = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        final ExecutorService threadPoolAny = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        //Создаем 2 списка аналогично
        List<Callable<Integer>> tasksAll = new ArrayList<>();
        List<Callable<Integer>> tasksAny = new ArrayList<>();

        //Добавляем в список задачи
        for (int i = 0; i < 4; i++) {
            Callable<Integer> myCallable = new MyCallable("Поток " + (i + 1));
            tasksAll.add(myCallable);
        }

        //Получаем список с результатами выполнения
        List<Future<Integer>> result = threadPoolAll.invokeAll(tasksAll);

        //"Выключаем" пул
        threadPoolAll.shutdownNow();

        //Выводим на экран результат работы
        for (Future<Integer> in : result) {
            System.out.println("Количество выведенных сообщений на экран: " + in.get());
        }

        System.out.println("Запускаем новый пул:");
        //Добавляем в список новые задачи
        for (int i = 0; i < 4; i++) {
            Callable<Integer> myCallable = new MyCallable("Поток " + (i + 1));
            tasksAny.add(myCallable);
        }

        //Получаем первую выполненную задачу и выводим её на экран
        //Так и не понял как мне вывести название выполненной задачи
        System.out.println("Количество выведенных сообщений на экран: " + threadPoolAny.invokeAny(tasksAny));

        //"Выключаем" пул
        threadPoolAny.shutdownNow();
    }
}