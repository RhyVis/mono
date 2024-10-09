package vis.rhynia.monotrix.configs

import org.springframework.boot.autoconfigure.web.WebProperties
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.Resource
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.resource.PathResourceResolver

@Configuration
class SinglePageMvcConf(
    private val webProperties: WebProperties,
) : WebMvcConfigurer {
    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry
            .addResourceHandler("/**")
            .addResourceLocations(*webProperties.resources.staticLocations)
            .resourceChain(true)
            .addResolver(
                object : PathResourceResolver() {
                    override fun getResource(
                        resourcePath: String,
                        location: Resource,
                    ): Resource? = super.getResource(resourcePath, location) ?: super.getResource("index.html", location)
                },
            )
    }
}
