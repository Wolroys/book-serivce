package org.sber.bookingserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class BookingServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookingServerApplication.class, args);
    }

}
