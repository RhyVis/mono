package vis.rhynia.monotrix.configs

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "monotrix")
data class MainConf(
    var endpoint: String = "",
    var token: String = "",
)
