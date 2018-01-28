package com.example.amqp.config;

import com.example.amqp.entity.TaskTest;
import com.example.amqp.serializer.JsonSerializerConfig;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.spring.cache.HazelcastCacheManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * Created by ksb on 2018. 1. 7..
 */
@Configuration
public class CacheConfig {

    @Value("${hazelcast.urls}")
    private String[] hazelcastUrls;

    @Value("${hazelcast.pool.size ?: 10}")
    private int poolSize;

    @Bean
    public HazelcastInstance hazelcastInstance() {
        ClientConfig config = new ClientConfig();
        config.getNetworkConfig().addAddress(hazelcastUrls).setSmartRouting(false);
        config.setExecutorPoolSize(poolSize);
        config.getSerializationConfig()
                .getSerializerConfigs()
                .addAll(
                        Arrays.asList(
                                new JsonSerializerConfig(TaskTest.class, TaskTest.TYPE_ID)
                        ));

        return HazelcastClient.newHazelcastClient(config);
    }

    @Bean
    public CacheManager cacheManager(HazelcastInstance hazelcastInstance) {
        return new HazelcastCacheManager(hazelcastInstance);
    }

}
