package com.example.ITS.Config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "spark.config")
@Data
public class SparkClient {
    private String appId;
    private String apiSecret;
    private String apiKey;

    @Bean
    public SparkClient sparkConfig(){
        SparkClient sparkClient = new SparkClient();
        sparkClient.appId = appId;
        sparkClient.apiKey = apiKey;
        sparkClient.apiSecret = apiSecret;
        return sparkClient;
    }

}
