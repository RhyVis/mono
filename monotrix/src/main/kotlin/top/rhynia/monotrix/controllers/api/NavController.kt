package top.rhynia.monotrix.controllers.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import top.rhynia.monotrix.components.FuncNav
import top.rhynia.monotrix.elements.web.ApiResponse

@RestController
@RequestMapping("/api")
class NavController(private val funcNav: FuncNav) {
    @GetMapping("/nav")
    fun navIndexList(): ApiResponse {
        return funcNav.getIndexList()
    }
}
