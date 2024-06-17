package com.example.ITS.Config;

import io.github.briqt.spark4j.SparkClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "spark.config")
@Data
public class XFConfig {
    private String appid;
    private String apiSecret;
    private String apiKey;

    @Bean
    public SparkClient sparkConfig(){
        SparkClient sparkClient = new SparkClient();
        sparkClient.appid = appid;
        sparkClient.apiKey = apiKey;
        sparkClient.apiSecret = apiSecret;
        return sparkClient;
    }

}
