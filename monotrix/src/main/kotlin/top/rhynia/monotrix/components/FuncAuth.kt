package top.rhynia.monotrix.components

import cn.hutool.core.util.RandomUtil
import cn.hutool.crypto.digest.DigestAlgorithm
import cn.hutool.crypto.digest.Digester
import org.springframework.stereotype.Component
import top.rhynia.monotrix.configs.MainConf
import top.rhynia.monotrix.interfaces.Log
import top.rhynia.monotrix.interfaces.Log.Companion.log

@Log
@Component
class FuncAuth(private val conf: MainConf) {
    private var tokenGlobalHash = ""

    fun init() {
        var token = conf.token
        val sha256 = Digester(DigestAlgorithm.SHA256)
        if (token.isEmpty()) {
            token = RandomUtil.randomString(32)
            log.warn("Token NOT SET, giving random by $token")
        }
        tokenGlobalHash = sha256.digestHex(token)
    }

    fun authHash(hash: String): Boolean {
        val result = (hash == tokenGlobalHash)
        log.info("Hash challenge by $hash, result $result")
        return result
    }
}
