package lpssy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WeiXinMages {
    public static void main(String[] args) {
        SpringApplication.run(WeiXinMages.class,args);
    }
}
