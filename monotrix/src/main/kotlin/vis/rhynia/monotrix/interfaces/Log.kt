package vis.rhynia.monotrix.interfaces

import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * 通过该接口省略Logger引入
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class Log {
    companion object {
        val <reified T> T.log: Logger
            inline get() = LoggerFactory.getLogger(T::class.java)
    }
}
