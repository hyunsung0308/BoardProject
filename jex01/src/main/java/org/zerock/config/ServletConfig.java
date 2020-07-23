package org.zerock.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

//기존의 servlet-context.xml에 설정돈 모든 내용을 담아야한다.
/*
 *  @EnableWebMvc 어노테이션과 WebMvcConfigurer 인터페이스를 구현하는 방식
 *  (과거에는 WebMvcConfigurerAdapter 추상 클래스를 사용했으나 스프링 5.0버전부터는 Deprecated되었 으므로 주의합니다.J. 
 *  @Configuration과 WebMvcConfigurationSupport 클래스를 상속하는 방식 - 
 *  일반 @Configuration 우선 순위가 구분되지 않는 경우에 사용
 */

@EnableWebMvc
@ComponentScan(basePackages = {"org.zerock.controller" , "org.zerock.exception"})
public class ServletConfig implements WebMvcConfigurer {

	//WebMvcConfigurer는 스프링 MVC와 관련된 설정을 메서드로 오버라이드 하는 형태를 이용할때 사용
	//ComponentScan 다른패키지에 작성된 스프링의 객체를 인식할 수 있다.
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		
		InternalResourceViewResolver bean = new InternalResourceViewResolver();
		bean.setViewClass(JstlView.class);
		bean.setPrefix("/WEB-INF/views/");
		bean.setSuffix(".jsp");
		registry.viewResolver(bean);
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
	
	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver getResolver() throws IOException {
		
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		
		//10MB
		resolver.setMaxUploadSizePerFile(1024 * 1024 * 10);
		
		//2MB
		resolver.setMaxUploadSize(1024 * 1024 * 2);
		
		//1MB
		resolver.setMaxInMemorySize(1024 * 1024);
		
		//temp upload
		resolver.setUploadTempDir(new FileSystemResource("C:\\upload\\tmp"));
		
		resolver.setDefaultEncoding("UTF-8");
		
		return resolver;
	}
	
}
