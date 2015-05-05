package io.spring.isomorphic;

import java.math.BigDecimal;
import java.util.Currency;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.script.ScriptTemplateConfigurer;
import org.springframework.web.servlet.view.script.ScriptTemplateViewResolver;

@SpringBootApplication
public class IsomorphicApplication extends WebMvcConfigurerAdapter {

    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
            "classpath:/META-INF/resources/", "classpath:/resources/",
            "classpath:/static/", "classpath:/public/" };

    public static void main(String[] args) {
        SpringApplication.run(IsomorphicApplication.class, args);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (!registry.hasMappingForPattern("/webjars/**")) {
            registry.addResourceHandler("/webjars/**").addResourceLocations(
                    "classpath:/META-INF/resources/webjars/");
        }
        if (!registry.hasMappingForPattern("/**")) {
            registry.addResourceHandler("/**").addResourceLocations(
                    CLASSPATH_RESOURCE_LOCATIONS);
        }
    }

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        configurer.setDefaultTimeout(1000000);
    }

    @Bean
    CommandLineRunner init(ProductRepository pr) {
        return args -> {

            pr.save(new Product("Boot Porter", Currency.getInstance("GBP"), BigDecimal.valueOf(3.99d), "https://buyourbottles.com/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/1/2/12ozheritage_bottle_0511f_rgb_fnl.jpg"));
            pr.save(new Product("Boot Lager", Currency.getInstance("GBP"), BigDecimal.valueOf(2.50d), "https://buyourbottles.com/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/1/2/12ozheritage_bottle_0511f_rgb_fnl.jpg"));
            pr.save(new Product("Boot IPA", Currency.getInstance("GBP"), BigDecimal.valueOf(2.99d), "https://buyourbottles.com/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/1/2/12ozheritage_bottle_0511f_rgb_fnl.jpg"));
            System.out.println("---------------------------------");
            pr.findAll().forEach(System.out::println);
            System.out.println("---------------------------------");
        };
    }

    @Bean
    public ViewResolver reactViewResolver() {
        ScriptTemplateViewResolver viewResolver = new ScriptTemplateViewResolver();
        viewResolver.setPrefix("static/templates/");
        viewResolver.setSuffix(".ejs");
        return viewResolver;
    }

    @Bean
    public ScriptTemplateConfigurer reactConfigurer() {
        ScriptTemplateConfigurer configurer = new ScriptTemplateConfigurer();
        configurer.setEngineName("nashorn");
        configurer.setScripts("static/polyfill.js",
                "static/lib/js/ejs.min.js",
                "/META-INF/resources/webjars/react/0.13.1/react.js",
//                "/META-INF/resources/webjars/react/0.13.1/JSXTransformer.js",
                "static/render.js",
                "static/output/product.js",
                "static/output/cart.js",
                "static/output/product-list.js");
        configurer.setRenderFunction("render");
        return configurer;
    }
}
