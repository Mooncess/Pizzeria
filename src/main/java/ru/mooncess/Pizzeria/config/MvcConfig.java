package ru.mooncess.Pizzeria.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/pizza").setViewName("pizza");
        registry.addViewController("/dessert").setViewName("dessert");
        registry.addViewController("/drink").setViewName("drink");
        registry.addViewController("/snack").setViewName("snack");
        registry.addViewController("/login").setViewName("login");
    }
}
