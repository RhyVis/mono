package top.rhynia.monotrix.components

import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import top.rhynia.monotrix.interfaces.Log
import top.rhynia.monotrix.interfaces.Log.Companion.log
import top.rhynia.monotrix.util.DictAssembly

@Log
@Component
class Preloader(
    private val funcAuth: FuncAuth,
    private val funcTarot: FuncTarot
) : ApplicationRunner {
    override fun run(args: ApplicationArguments?) {
        // Static
        DictAssembly.init()

        // Bean
        funcTarot.init()
        funcAuth.init()

        log.info("Preload completed")
    }
}
