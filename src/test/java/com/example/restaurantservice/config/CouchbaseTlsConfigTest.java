package com.example.restaurantservice.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.couchbase.ClusterEnvironmentBuilderCustomizer;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CouchbaseTlsConfigTest {

    private CouchbaseTlsConfig couchbaseTlsConfig;

    @BeforeEach
    void setUp() {
        couchbaseTlsConfig = new CouchbaseTlsConfig();
    }

    @Test
    void shouldCreateClusterEnvironmentBuilderCustomizer() {

        ClusterEnvironmentBuilderCustomizer customizer =
                couchbaseTlsConfig.clusterEnvironmentBuilderCustomizer();

        assertNotNull(customizer);
    }
}
