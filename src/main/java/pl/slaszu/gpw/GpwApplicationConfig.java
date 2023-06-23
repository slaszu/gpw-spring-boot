package pl.slaszu.gpw;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

@Configuration
public class GpwApplicationConfig {

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {

        return new LettuceConnectionFactory(
            // todo rozdzielic na prod i dev
            new RedisStandaloneConfiguration("127.0.0.1", 3308)
        );
    }
}
