package modsen.pizza.newservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class NewServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewServiceApplication.class, args);
    }

}
