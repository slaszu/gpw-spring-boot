package pl.slaszu.gpw;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FullyQualifiedAnnotationBeanNameGenerator;
import org.springframework.scheduling.annotation.EnableScheduling;

// TODO: 27.04.2023 ./stop with params app|mysql
// TODO: 27.04.2023 cache data and refresh after db data chenged
// TODO: 06/05/2023 on production dont show full exception stack 

@SpringBootApplication
@ComponentScan(nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class)
@EnableScheduling
@OpenAPIDefinition(
    info = @Info(title = "GPW stocks API", version = "1.0"),
    servers = {@Server(url = "/", description = "Default Server URL")}
)
@EnableCaching
public class GpwApplication {

    public static void main(String[] args) {
        SpringApplication.run(GpwApplication.class, args);
    }

}
