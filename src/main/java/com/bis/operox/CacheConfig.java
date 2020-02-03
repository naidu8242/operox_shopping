/*
 * Copyright (c) 2016, Business Intelli Solutions India Pvt. Ltd., and/or its affiliates. All rights reserved.
 * Business Intelli Solutions India Pvt. Ltd. PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.bis.operox;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
/*import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;*/
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * This class is used to configure the caching with ehcache.
 * 
 * 
 * @author: Srinivas Vemula
 */
@Configuration
@EnableCaching
public class CacheConfig {
	
    @Bean
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
        EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        ehCacheManagerFactoryBean.setCacheManagerName("OperoxCache");
        ehCacheManagerFactoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
        ehCacheManagerFactoryBean.isSingleton();
        ehCacheManagerFactoryBean.setShared(true);
        return ehCacheManagerFactoryBean;
    }

    @Bean
    public EhCacheCacheManager ehCacheCacheManager() {
      return new EhCacheCacheManager(ehCacheManagerFactoryBean().getObject());
    }
}
