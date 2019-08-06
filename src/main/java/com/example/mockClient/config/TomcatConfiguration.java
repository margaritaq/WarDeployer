package com.example.mockClient.config;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.servlet.ServletException;
import java.io.File;


@Configuration
public class TomcatConfiguration {
    private static final Logger logger = Logger.getLogger(TomcatConfiguration.class);

    @Autowired
    private Environment environment;


    @Bean
    public EmbeddedServletContainerFactory servletContainerFactory() {
        return new TomcatEmbeddedServletContainerFactory() {

            @Override
            protected TomcatEmbeddedServletContainer getTomcatEmbeddedServletContainer(Tomcat tomcat) {

                new File(tomcat.getServer().getCatalinaBase(), "webapps").mkdirs();
                try {
                    String warPath = environment.getProperty("war.path");
                    File wars = new File(warPath);
                    String[] warList = wars.list();
                    for (int i = 0; i < warList.length; i++) {
                        String generalWarPath = "/app" + (i + 1);
                        Context context = tomcat.addWebapp(generalWarPath, warPath + warList[i]);
                        context.setParentClassLoader(getClass().getClassLoader());
                        logger.info("WE DEPLOY APPLICATION !");
                        logger.info("War: " + warList[i] + " Status: Successfully!");

                    }

                } catch (ServletException ex) {
                    throw new IllegalStateException("Failed to add webapp", ex);
                }

                return super.getTomcatEmbeddedServletContainer(tomcat);
            }

        };
    }


}
