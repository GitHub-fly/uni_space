package com.scs.web.uni_space.util;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @ClassName ThreadPool
 * @Description 线程池
 * @Author xiaobinggan
 * @Date 2019/12/22 2:28 下午
 * @Version 1.0
 **/
@Component
public class ThreadPool {
    @Resource
    private UploadTask uploadTask;

    public List<String> getResult() {
        List<String> returnValue = null;
        //线程调度，开始执行任务
        ExecutorService pool = Executors.newCachedThreadPool();
        Future<List<String>> future = pool.submit(uploadTask);
        while (true) {
            if (future.isDone()) {
                try {
                    returnValue = future.get();
                    System.out.println("线程返回值：" + returnValue);
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
                pool.shutdown();
                break;
            }
        }
        return returnValue;
    }
}
