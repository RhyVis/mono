package top.rhynia.monotrix.controllers.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import top.rhynia.monotrix.components.FuncSpam
import top.rhynia.monotrix.elements.web.ApiResponse
import top.rhynia.monotrix.elements.web.post.PostSpam

@RestController
@RequestMapping("/api")
class SpamController(private val funcSpam: FuncSpam) {
    @GetMapping("/spam")
    fun getSpam(): ApiResponse {
        return ApiResponse(funcSpam.fetchSpam("genshin", ""))
    }

    @PostMapping("/spam")
    fun postSpam(@RequestBody post: PostSpam): ApiResponse {
        return funcSpam.fetchSpam(post)
    }
}
