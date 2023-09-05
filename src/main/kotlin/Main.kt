import kotlinx.coroutines.*

@OptIn(DelicateCoroutinesApi::class)
fun main() {
    var isDoorOpen = false

    println("Unlocking the door... please wait.\n")
    GlobalScope.launch {
        delay(3000)

        isDoorOpen = true
    }

    GlobalScope.launch {
        repeat(4) {
            println("Trying to open the door...\n")
            delay(800)

            println(if (isDoorOpen) {
                "Open the door!\n"
            } else {
                "The door is still locked\n"
            })
        }
    }

    Thread.sleep(5000)
}