package controller

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
@SpringBootApplication(scanBasePackages = ["controller", "entities", "repositories","services"])
@EnableJpaRepositories(basePackages = ["repositories"])
@EntityScan(basePackages = ["entities"])
class Application


fun main(args: Array<String>){
    runApplication<Application>(*args)
}