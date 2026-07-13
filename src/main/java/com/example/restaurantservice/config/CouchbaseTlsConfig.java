package com.example.restaurantservice.config;

import com.couchbase.client.java.env.ClusterEnvironment;
import org.springframework.boot.autoconfigure.couchbase.ClusterEnvironmentBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CouchbaseTlsConfig {

    @Bean
    public ClusterEnvironmentBuilderCustomizer clusterEnvironmentBuilderCustomizer() {
        return (ClusterEnvironment.Builder builder) -> builder.securityConfig(sc -> sc.enableTls(true));
    }
}