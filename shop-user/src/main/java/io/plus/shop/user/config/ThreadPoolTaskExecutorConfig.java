package io.plus.shop.user.config;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.sleuth.instrument.async.LazyTraceExecutor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * Description: Sleuth异步线程池配置
 * 自定义异步任务线程池
 * @author Super_light
 * @date 6/8/22 10:02 PM
 */
@Configuration
@EnableAutoConfiguration
public class ThreadPoolTaskExecutorConfig extends AsyncConfigurerSupport {

    @Autowired
    private BeanFactory beanFactory;

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(5);
        executor.setQueueCapacity(10);
        executor.setThreadNamePrefix("trace-thread-");
        executor.initialize();

        //        return executor;
        //包装返回的异步任务线程池
        return new LazyTraceExecutor(this.beanFactory, executor);
    }
}
