package com.kubrafelek.jpahibernate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpaHibernateApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(JpaHibernateApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(JpaHibernateApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        log.info("***************App Started***************");
        log.info("***************App Stopped***************");
    }

    private void testPersist() {

    }
}
