package top.rhynia.monotrix

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import vis.rhynia.monotrix.components.FuncCodex

@SpringBootTest
class MonotrixApplicationTests {
    @Test
    fun contextLoads() {
        val unit = "哈基米"
        val origin = "我"
        val map = unit.toCharArray().mapIndexed { i, c -> i to c.toString() }.toMap()
        val encoded = FuncCodex.encodeNary(origin, "", 3, map)
        println()
        println()
        println("encoded = $encoded")
        println()
        println()
        val decoded = FuncCodex.decodeNary(encoded, "", 3, map)
        println("decoded = $decoded")
    }
}
