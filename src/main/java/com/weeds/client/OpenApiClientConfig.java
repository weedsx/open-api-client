package com.weeds.client;

import com.weeds.client.client.OpenApiClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author weeds
 */
@Configuration
@ConfigurationProperties("open-api.client")
@ComponentScan
@Data
public class OpenApiClientConfig {
    private String accessKey;

    private String secretKey;

    @Bean
    public OpenApiClient openApiClient() {
        return new OpenApiClient(this.accessKey, this.secretKey);
    }
}
