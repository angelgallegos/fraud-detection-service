package leapest.edcast.assign;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import leapest.edcast.assign.entity.Fraud;
import leapest.edcast.assign.repository.FraudRepository;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableConfigurationProperties(Config.class)
public class Application implements CommandLineRunner {

    @Autowired
    FraudRepository repository;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }

        };
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Override
    public void run(String... args) throws Exception {
        String date = "2022-03-DDT13:00:00";
        List<String> companies =  Arrays.asList("edcast", "facebook", "twitter", "google");
        List<String> countries =  Arrays.asList("Belgium", "Netherlands", "Germany", "France", "Spain");
        for (String country : countries) {
            for (int i = 1; i < 32; i++) {
                Random rand = new Random();
                String day = ((i < 10) ? "0":"")+i;
                logger.info("Inserting -> {}",
                    repository.save(
                        new Fraud(
                            country,
                            "123456789012",
                            companies.get(rand.nextInt(companies.size())),
                            new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(date.replace("DD",day))
                        )
                    )
                );
            }
        }
    }
}
