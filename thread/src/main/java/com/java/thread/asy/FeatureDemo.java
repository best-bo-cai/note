package com.java.thread.asy;

import java.util.concurrent.*;

/**
 * 教程：https://juejin.cn/post/6970558076642394142#heading-25
 * 不使用 future+线程池 则耗时：300+300+300=900ms
 * 使用future+线程池 总耗时：607ms
 */
public class FeatureDemo {

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

        FutureTask<String> stringFuture = new FutureTask<>(FeatureDemo::getCat);
        executorService.submit(stringFuture);

        Thread.sleep(300);

        FutureTask<String> stringFutureTask = new FutureTask<>(FeatureDemo::getDog);
        executorService.submit(stringFutureTask);

        // get方法在获取到结果前 会一直阻塞线程
        stringFuture.get();
        stringFutureTask.get();

        System.out.println("总共用时" + (System.currentTimeMillis() - startTime) + "ms");
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
