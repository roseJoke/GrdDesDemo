package com.grddes;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@SpringBootApplication
@EnableWebSocket
@MapperScan("com.grddes.mapper")  //映射对应的mapper类
public class GrddesdemoApplication extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
		return application.sources(GrddesdemoApplication.class);
	}
	public static void main(String[] args) {
		SpringApplication.run(GrddesdemoApplication.class, args);
	}
}
