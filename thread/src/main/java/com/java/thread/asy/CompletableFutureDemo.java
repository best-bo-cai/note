package com.java.thread.asy;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class CompletableFutureDemo {

    private static final ExecutorService executorService = new ThreadPoolExecutor(
            16,
            24,
            3L,
            TimeUnit.MINUTES,
            new LinkedBlockingDeque<>(500),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.DiscardOldestPolicy()
    );

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        long startTime = System.currentTimeMillis();

        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                return getCat();
            } catch (InterruptedException e) {
                log.info("错误信息：{}", e.getMessage());
                throw new RuntimeException(e);
            }
        }, executorService);


        Thread.sleep(300);

        CompletableFuture<String> stringCompletableFuture1 = CompletableFuture.supplyAsync(() -> {
            try {
                return getDog();
            } catch (InterruptedException e) {
                log.info("错误信息：{}", e.getMessage());
                throw new RuntimeException(e);
            }
        }, executorService);

        // get方法在获取到结果前 会一直阻塞线程
        //get() 方法会抛出经检查的异常，可被捕获，自定义处理或者直接抛出。
        //
        //而 join() 会抛出未经检查的异常。
        stringCompletableFuture.get();
        stringCompletableFuture1.join();

        log.info("总共用时" + (System.currentTimeMillis() - startTime) + "ms");
    }

    public static String getCat() throws InterruptedException {
        Thread.sleep(300);
        return "Tom";
    }

    public static String getDog() throws InterruptedException {
        Thread.sleep(300);
        return "Jerry";
    }

}
