package pl.piotrowski.ksb2restapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class Ksb2RestApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(Ksb2RestApiApplication.class, args);
    }

}
