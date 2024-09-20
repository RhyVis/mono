package top.rhynia.monotrix

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan("top.rhynia.monotrix.configs")
class MonotrixApplication

fun main(args: Array<String>) {
    runApplication<MonotrixApplication>(*args)
}
