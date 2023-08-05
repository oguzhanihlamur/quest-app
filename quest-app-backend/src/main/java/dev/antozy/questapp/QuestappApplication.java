package dev.antozy.questapp;

import dev.antozy.questapp.filters.LoggingFilter;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Log4j2
@Configuration
@SpringBootApplication
public class QuestappApplication {

    public static void main(String[] args) {
        log.info("QuestApp Application is started.");
        SpringApplication.run(QuestappApplication.class, args);
        log.info("QuestApp Application is finished.");
    }

    @Bean
    public FilterRegistrationBean<LoggingFilter> loggingFilter() {
        FilterRegistrationBean<LoggingFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new LoggingFilter());
        registrationBean.addUrlPatterns("/*"); // Filtrenin uygulanacağı URL desenleri (tümü için /* kullanabilirsiniz)
        return registrationBean;
    }
}
