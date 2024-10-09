package vis.rhynia.monotrix.controllers.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import vis.rhynia.monotrix.components.FuncCodex
import vis.rhynia.monotrix.elements.web.ApiResponse
import vis.rhynia.monotrix.elements.web.post.PostCodex

@RestController
@RequestMapping("/api")
class CodexController(
    private val func: FuncCodex,
) {
    @GetMapping("/codex")
    fun getCodex(): ApiResponse = func.codex("玩玩抽象差不多得了")

    @PostMapping("/codex")
    fun postCodex(
        @RequestBody post: PostCodex,
    ): ApiResponse = func.codex(post.text, post.type, post.code)

    @GetMapping("/codex/info")
    fun getCodexInfo(): ApiResponse = ApiResponse()
}
