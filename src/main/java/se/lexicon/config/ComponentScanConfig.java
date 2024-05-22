package se.lexicon.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import se.lexicon.util.ScannerInputService;

import java.util.Scanner;

@Configuration
@ComponentScan(basePackages = "se.lexicon")
public class ComponentScanConfig {

    @Bean
    public Scanner scanner () {
        return new Scanner(System.in);
    }

}
