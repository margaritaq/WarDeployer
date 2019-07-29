package com.example.mockClient;



import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.servlet.ServletException;
import java.io.File;

@SpringBootApplication
@EnableScheduling
public class MockClientApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(MockClientApplication.class, args);
		SheduledTasks scan = new SheduledTasks();
		scan.scanFolder();
	}
	@Bean
	public EmbeddedServletContainerFactory servletContainerFactory() {
		return new TomcatEmbeddedServletContainerFactory() {

			@Override
			protected TomcatEmbeddedServletContainer getTomcatEmbeddedServletContainer(Tomcat tomcat) {

				new File(tomcat.getServer().getCatalinaBase(), "webapps").mkdirs();

				try {
					Context context = tomcat.addWebapp("/app", "C:\\My Program Files\\myApp\\wars\\Mock.war");
					context.setParentClassLoader(getClass().getClassLoader());
				} catch (ServletException ex) {
					throw new IllegalStateException("Failed to add webapp", ex);
				}
				return super.getTomcatEmbeddedServletContainer(tomcat);
			}

		};}
}
