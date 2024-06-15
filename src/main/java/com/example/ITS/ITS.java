package com.example.ITS;

import org.mybatis.spring.annotation.MapperScan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.example.ITS.Mapper")
@SpringBootApplication
public class ITS {

	public static void main(String[] args) {
		SpringApplication.run(ITS.class, args);
	}

}
