package com.sondeos.javanotifychallenge.configurations;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@EnableCaching
public class CacheConfig {
    /*@Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        Cache contactsCache = new ConcurrentMapCache("contacts");
        /*Cache emailsCache = new ConcurrentMapCache("emails");
        Cache smsCache = new ConcurrentMapCache("sms");*
        cacheManager.setCaches(Arrays.asList(contactsCache/*, emailsCache, smsCache*));
        return cacheManager;
    }*/
}
