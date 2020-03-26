package cn.xyr.ssm.common.utils;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 定时任务线程池管理对象
 *
 * @author xyr
 */
public class ScheduledExecutorManger {

    private static ConcurrentHashMap<String, ScheduledFuture<?>> concurrentHashMap = new ConcurrentHashMap<>(8);
    private static ConcurrentHashMap<String, List<Future<?>>> map = new ConcurrentHashMap<>(8);

    private ScheduledExecutorManger() {

    }

    private static final class ScheduledExecutor {
        private static ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(4,
                new ThreadFactoryBuilder().setNameFormat("example-schedule-pool-%d").build());

    }

    private static final class MyExecutorService {
        private static ExecutorService executorService = new ThreadPoolExecutor(
                4, 32, 2000L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(1024),
                new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build());
    }

    private static class MyRunnable implements Runnable {

        private Runnable command;
        private int count;
        private boolean forever;
        private String taskName;

        public MyRunnable(String taskName, Runnable command, int count) {
            this.taskName = taskName;
            this.command = command;
            this.count = count;
            this.forever = count <= 0;
        }

        @Override
        public void run() {
            if (forever) {
                task();
            } else {
                if (count-- > 0) {
                    task();
                } else {
                    ScheduledFuture<?> scheduledFuture = concurrentHashMap.remove(taskName);
                    scheduledFuture.cancel(true);
                    List<Future<?>> list = map.remove(taskName);
                    list.clear();
                }
            }
        }

        private void task() {
            ExecutorService executorService = getExecutorService();
            Future<?> future = executorService.submit(command);
            List<Future<?>> list = map.computeIfAbsent(taskName, (key) -> new ArrayList<>());
            list.removeIf(Future::isDone);
            list.add(future);
        }
    }

    public static ScheduledExecutorService getInstance() {
        return ScheduledExecutor.scheduledExecutorService;
    }

    public static ExecutorService getExecutorService() {
        return MyExecutorService.executorService;
    }

    /**
     * 指定执行次数和执行时间间隔执行定时任务
     *
     * @param command      任务
     * @param initialDelay 延迟执行时间
     * @param period       循环执行间隔
     * @param count        执行次数
     * @param unit         时间单位
     */
    public static ScheduledFuture<?> execute(String taskName, Runnable command, long initialDelay, long period, int count, TimeUnit unit) {
        ScheduledExecutorService service = getInstance();
        MyRunnable runnable = new MyRunnable(taskName, command, count);
        ScheduledFuture<?> scheduledFuture = service.scheduleAtFixedRate(runnable, initialDelay, period, unit);
        concurrentHashMap.put(taskName, scheduledFuture);
        return scheduledFuture;
    }

    /**
     * 停止定时任务
     *
     * @param taskName 任务名
     */
    public static void stop(String taskName) {
        ScheduledFuture<?> scheduledFuture = concurrentHashMap.remove(taskName);
        List<Future<?>> list = map.remove(taskName);
        scheduledFuture.cancel(true);
        if (list != null) {
            for (Future<?> f : list) {
                f.cancel(true);
            }
            list.clear();
        }
    }

}
