package de.adesso.demo.config;

import de.adesso.demo.entity.User;
import de.adesso.demo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("seeding")
public class DataSeedingConfig {

    @Bean
    public CommandLineRunner seedDatabase(UserRepository userRepository) {
        return args -> {
            // Add your seeding logic here
            userRepository.save(new User(1l,"Dimitris","Evangelopoulos","dimi@email.com"));
            userRepository.save(new User(2l,"Thomas","Müller","thomas@email.com"));
        };
    }
}