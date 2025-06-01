package bg.sofia.uni.fmi.javaweb.sports_marketplace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class SportsMarketplaceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SportsMarketplaceApplication.class, args);
    }

}