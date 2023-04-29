package com.redshift.kountry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class KountryApplication {

    public static void main(String[] args) {
        SpringApplication.run(KountryApplication.class, args);
    }

}
