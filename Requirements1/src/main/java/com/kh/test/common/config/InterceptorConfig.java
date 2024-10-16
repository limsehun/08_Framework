package com.kh.test.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.kh.test.common.interceptor.CategoryInterceptor;
@Configuration// Configuration - 이하 모두 불러
public class InterceptorConfig implements WebMvcConfigurer{
	@Bean//bean?
	public CategoryInterceptor categoryInterceptor() {
		return new CategoryInterceptor();// 생성자 - CategoryInterceptor를 생성, bean등록
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor( categoryInterceptor() )
		.addPathPatterns("/**")
		.excludePathPatterns("/css/**", "/js/**", "/images/**", "/favicon.ico"); 
		
		
	}
}