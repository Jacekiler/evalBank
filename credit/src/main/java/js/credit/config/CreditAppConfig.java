package js.credit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.client.RestTemplate;

@Configuration
@ImportResource({"classpath*:applicationContext.xml"})
public class CreditAppConfig {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
