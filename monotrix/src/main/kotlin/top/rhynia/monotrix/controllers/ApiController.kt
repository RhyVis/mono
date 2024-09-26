package top.rhynia.monotrix.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import top.rhynia.monotrix.components.FuncAuth
import top.rhynia.monotrix.components.FuncDict
import top.rhynia.monotrix.components.FuncNav
import top.rhynia.monotrix.components.FuncSave
import top.rhynia.monotrix.components.FuncSpam
import top.rhynia.monotrix.components.FuncTarot
import top.rhynia.monotrix.elements.ApiResponse
import top.rhynia.monotrix.elements.web.post.PostCodex
import top.rhynia.monotrix.elements.web.post.PostSpam
import top.rhynia.monotrix.elements.web.post.PostStr
import top.rhynia.monotrix.elements.web.post.PostStrSave
import top.rhynia.monotrix.elements.web.post.PostTarot

@RestController
@RequestMapping("/api")
class ApiController(
    private val funcSpam: FuncSpam,
    private val funcTarot: FuncTarot,
    private val funcAuth: FuncAuth,
    private val funcSave: FuncSave,
    private val funcNav: FuncNav
) {

    @RequestMapping("/")
    fun root(): ApiResponse {
        return ApiResponse("API Root")
    }

    // region Auth
    @PostMapping("/auth")
    fun auth(@RequestBody post: PostStr): ApiResponse {
        return ApiResponse(funcAuth.authHash(post.text))
    }
    // endregion

    // region Codex
    @GetMapping("/codex")
    fun codex(): ApiResponse {
        return FuncDict.codex("玩玩抽象差不多得了")
    }

    @PostMapping("/codex")
    fun codex(@RequestBody post: PostCodex): ApiResponse {
        return FuncDict.codex(post.text, post.type, post.code)
    }
    // endregion

    // region Spam
    @GetMapping("/spam")
    fun spam(): ApiResponse {
        return ApiResponse(funcSpam.fetchSpam("genshin", ""))
    }

    @PostMapping("/spam")
    fun spam(@RequestBody post: PostSpam): ApiResponse {
        return funcSpam.fetchSpam(post)
    }
    // endregion

    // region Tarot
    @GetMapping("/tarot")
    fun tarot(): ApiResponse {
        return ApiResponse(funcTarot.draw("waite", true, 1)[0].name)
    }

    @PostMapping("/tarot")
    fun tarot(@RequestBody post: PostTarot): ApiResponse {
        return funcTarot.draw(post)
    }

    @GetMapping("/tarot/info")
    fun tarotInfo(): ApiResponse {
        return funcTarot.deckInfo()
    }
    // endregion

    // region Save
    @GetMapping("/save")
    fun save(): ApiResponse {
        return ApiResponse(funcSave.queryStr(0))
    }

    @PostMapping("/save")
    fun save(@RequestBody post: PostStrSave): ApiResponse {
        return ApiResponse(funcSave.queryStr(post.id))
    }

    @RequestMapping("/save", method = [RequestMethod.PUT])
    fun saveUpdate(@RequestBody post: PostStrSave): ApiResponse {
        try {
            funcSave.updateStr(post.id, post.text, post.note)
            return ApiResponse()
        } catch (e: Exception) {
            return ApiResponse(-1, e)
        }
    }

    @RequestMapping("/save", method = [RequestMethod.DELETE])
    fun saveDelete(@RequestBody post: PostStrSave): ApiResponse {
        try {
            funcSave.deleteStr(post.id)
            return ApiResponse()
        } catch (e: Exception) {
            return ApiResponse(-1, e)
        }
    }
    // endregion

    // region Nav
    @GetMapping("/nav")
    fun navIndexList(): ApiResponse {
        return funcNav.getIndexList()
    }
    // endregion
}
