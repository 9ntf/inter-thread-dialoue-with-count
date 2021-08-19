package thread;

import java.io.IOException;
import java.util.concurrent.Callable;

public class MyCallable implements Callable<Integer> {

    public static final int THREAD_TIMEOUT = 2500;
    private String name;
    private Integer count = 0;

    public MyCallable(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getCount() {
        return count;
    }

    @Override
    public Integer call() {
        try {
            for (int i = 0; i < 5; i++) {
                Thread.sleep(THREAD_TIMEOUT);
                System.out.printf("Я поток %s Всем привет!\n", getName());
                count++;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            System.out.printf("%s завершен\n", getName());
        }
        return count;
    }
}