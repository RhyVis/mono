package top.rhynia.monotrix.controllers.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import top.rhynia.monotrix.components.FuncDict
import top.rhynia.monotrix.elements.web.ApiResponse
import top.rhynia.monotrix.elements.web.post.PostCodex

@RestController
@RequestMapping("/api")
class CodexController(private val func: FuncDict) {
    @GetMapping("/codex")
    fun getCodex(): ApiResponse {
        return func.codex("玩玩抽象差不多得了")
    }

    @PostMapping("/codex")
    fun postCodex(@RequestBody post: PostCodex): ApiResponse {
        return func.codex(post.text, post.type, post.code)
    }
}
