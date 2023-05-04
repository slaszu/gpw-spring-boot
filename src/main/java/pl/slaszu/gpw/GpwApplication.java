package pl.slaszu.gpw;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FullyQualifiedAnnotationBeanNameGenerator;
import org.springframework.scheduling.annotation.EnableScheduling;

// TODO: 20/03/2023 logging to file with rotate log, logging only excption in PROD profile
// TODO: 20/03/2023 create PROD profile
// TODO: 27.04.2023 ./stop with params app|mysql
// TODO: 27.04.2023 cache data and refresh after db data chenged 
// TODO: 04.05.2023 add calendar days to turn of scheduler on day without session

@SpringBootApplication
@ComponentScan(nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class)
@EnableScheduling
@OpenAPIDefinition(
    info = @Info(title = "GPW stocks API", version = "1.0"),
    servers = {@Server(url = "/", description = "Default Server URL")}
)

public class GpwApplication {

    public static void main(String[] args) {
        SpringApplication.run(GpwApplication.class, args);
    }

}
