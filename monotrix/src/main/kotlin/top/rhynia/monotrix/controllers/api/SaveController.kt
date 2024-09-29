package top.rhynia.monotrix.controllers.api

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import top.rhynia.monotrix.components.FuncSave
import top.rhynia.monotrix.elements.web.ApiResponse
import top.rhynia.monotrix.elements.web.post.PostStrSave

@RestController
@RequestMapping("/api")
class SaveController(private val funcSave: FuncSave) {
    @GetMapping("/save")
    fun getSave(): ApiResponse {
        return ApiResponse(funcSave.queryStr(0))
    }

    @PostMapping("/save")
    fun postSave(@RequestBody post: PostStrSave): ApiResponse {
        return ApiResponse(funcSave.queryStr(post.id))
    }

    @PutMapping("/save")
    fun updateSave(@RequestBody post: PostStrSave): ApiResponse {
        try {
            funcSave.updateStr(post.id, post.text, post.note)
            return ApiResponse()
        } catch (e: Exception) {
            return ApiResponse.fail(e)
        }
    }

    @DeleteMapping("/save")
    fun saveDelete(@RequestBody post: PostStrSave): ApiResponse {
        try {
            funcSave.deleteStr(post.id)
            return ApiResponse()
        } catch (e: Exception) {
            return ApiResponse.fail(e)
        }
    }
}
