package pl.slaszu.gpw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FullyQualifiedAnnotationBeanNameGenerator;
import org.springframework.scheduling.annotation.EnableScheduling;

// TODO: 20/03/2023 logging to file with rotate log, logging only excption in PROD profile
// TODO: 20/03/2023 create PROD profile 
// TODO: 20/03/2023 add last_updated date to product_stock 
// TODO: 20/03/2023 create endoints form stocks and stocks_price with filtering 

@SpringBootApplication
@ComponentScan(nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class)
@EnableScheduling
public class GpwApplication {

    public static void main(String[] args) {
        SpringApplication.run(GpwApplication.class, args);
    }

}
