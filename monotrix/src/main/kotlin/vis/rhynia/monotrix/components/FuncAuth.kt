package vis.rhynia.monotrix.components

import cn.hutool.core.util.RandomUtil
import cn.hutool.crypto.SecureUtil
import org.springframework.stereotype.Component
import vis.rhynia.monotrix.configs.MainConf
import vis.rhynia.monotrix.interfaces.Log
import vis.rhynia.monotrix.interfaces.Log.Companion.log

@Log
@Component
class FuncAuth(
    private val conf: MainConf,
) {
    private var tokenHash = ""

    fun init() {
        var token = conf.token
        if (token.isEmpty()) {
            token = RandomUtil.randomString(32)
            log.warn("Token NOT SET, giving random by $token")
        }
        tokenHash = SecureUtil.sha256(token)
    }

    fun authHash(hash: String): Boolean {
        val result = (hash == tokenHash)
        log.info("Hash challenge by $hash, result $result")
        return result
    }
}
