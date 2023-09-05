import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main() {
    GlobalScope.launch {
        println("Hello coroutine")
        delay(500)
        println("Right back at ya!")
    }
    Thread.sleep(1000)
}