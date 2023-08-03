package dev.antozy.questapp;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Log4j2
@SpringBootApplication
public class QuestappApplication {

    public static void main(String[] args) {
        log.info("QuestApp Application is started.");
        SpringApplication.run(QuestappApplication.class, args);
        log.info("QuestApp Application is finished.");
    }

}
