package ca.alexandremoore.mtg;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) throws IOException {
        ApplicationContext context = SpringApplication.run(DemoApplication.class, args);
    }

    @Primary
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
