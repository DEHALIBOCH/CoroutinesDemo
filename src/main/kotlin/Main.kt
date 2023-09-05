import kotlinx.coroutines.*

@OptIn(DelicateCoroutinesApi::class)
fun main() {
    GlobalScope.launch {
        val bgTreadName = Thread.currentThread().name
        println("I`m Job 1 in thread $$bgTreadName")
        delay(200)
        GlobalScope.launch(Dispatchers.Main) {
            val uiThreadName = Thread.currentThread().name
            println("I`m Job 2 in thread $uiThreadName")
        }
    }
    Thread.sleep(1000)
}