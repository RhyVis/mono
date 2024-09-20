package top.rhynia.monotrix.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class MainController {
    @GetMapping("/")
    fun main(): String {
        return "/index.html"
    }
}
