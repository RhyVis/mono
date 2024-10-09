package vis.rhynia.monotrix.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class MainController {
    @RequestMapping("/")
    fun main(): String = "index"
}
