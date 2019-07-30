package com.bcaliskan.postgresgroovydemo

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing


@EnableJpaAuditing
@SpringBootApplication
class PostgresGroovyDemoApplication {

    static void main(String[] args) {
        SpringApplication.run(PostgresGroovyDemoApplication.class, args)
    }

}
