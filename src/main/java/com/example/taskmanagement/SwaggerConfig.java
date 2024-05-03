package com.example.taskmanagement;

import org.springdoc.core.properties.SwaggerUiConfigProperties;
import org.springdoc.core.utils.SpringDocUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class SwaggerConfig implements WebMvcConfigurer {

    @Autowired
    private SwaggerUiConfigProperties swaggerUiConfigProperties;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/swagger-ui/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/springdoc-openapi-ui/")
                .resourceChain(false);
    }

    static {
        SpringDocUtils.getConfig().addRequestWrapperToIgnore(HttpServletRequest.class);
    }

}
