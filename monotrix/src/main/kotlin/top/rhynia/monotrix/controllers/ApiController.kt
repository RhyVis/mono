package top.rhynia.monotrix.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import top.rhynia.monotrix.components.FuncAuth
import top.rhynia.monotrix.components.FuncDict
import top.rhynia.monotrix.components.FuncSave
import top.rhynia.monotrix.components.FuncSpam
import top.rhynia.monotrix.components.FuncTarot
import top.rhynia.monotrix.elements.web.post.PostCodex
import top.rhynia.monotrix.elements.web.post.PostSpam
import top.rhynia.monotrix.elements.web.post.PostStr
import top.rhynia.monotrix.elements.web.post.PostStrSave
import top.rhynia.monotrix.elements.web.post.PostTarot
import top.rhynia.monotrix.elements.web.result.PackedResult

@RestController
@RequestMapping("/api")
class ApiController(
    private val funcSpam: FuncSpam,
    private val funcTarot: FuncTarot,
    private val funcAuth: FuncAuth,
    private val funcSave: FuncSave
) {

    @RequestMapping("/")
    fun root(): String {
        return "API Root"
    }

    // region Auth
    @PostMapping("/auth")
    fun auth(@RequestBody post: PostStr): PackedResult {
        return PackedResult(funcAuth.authHash(post.text))
    }
    // endregion

    // region Codex
    @GetMapping("/codex")
    fun codex(): String {
        return FuncDict.codexRaw("玩玩抽象差不多得了")
    }

    @PostMapping("/codex")
    fun codex(@RequestBody post: PostCodex): PackedResult {
        return FuncDict.codex(post.text, post.type, post.code)
    }
    // endregion

    // region Spam
    @GetMapping("/spam")
    fun spam(): String {
        return funcSpam.fetchSpam("genshin", "")[0].text
    }

    @PostMapping("/spam")
    fun spam(@RequestBody post: PostSpam): PackedResult {
        return funcSpam.fetchSpam(post)
    }
    // endregion

    // region Tarot
    @GetMapping("/tarot")
    fun tarot(): String {
        return funcTarot.draw("waite", true, 1)[0].name
    }

    @PostMapping("/tarot")
    fun tarot(@RequestBody post: PostTarot): PackedResult {
        return funcTarot.draw(post)
    }
    // endregion

    // region Save
    @GetMapping("/save")
    fun save(): String {
        return funcSave.queryStr(0).text
    }

    @PostMapping("/save")
    fun save(@RequestBody post: PostStrSave): PackedResult {
        return PackedResult(funcSave.queryStr(post.id))
    }

    @RequestMapping("/save", method = [RequestMethod.PUT])
    fun saveUpdate(@RequestBody post: PostStrSave): PackedResult {
        try {
            funcSave.updateStr(post.id, post.text, post.note)
            return PackedResult(0)
        } catch (e: Exception) {
            return PackedResult(e)
        }
    }

    @RequestMapping("/save", method = [RequestMethod.DELETE])
    fun saveDelete(@RequestBody post: PostStrSave): PackedResult {
        try {
            funcSave.deleteStr(post.id)
            return PackedResult(0)
        } catch (e: Exception) {
            return PackedResult(e)
        }
    }
    // endregion
}
