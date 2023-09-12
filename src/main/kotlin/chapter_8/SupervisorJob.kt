package chapter_8

import kotlinx.coroutines.*

fun main() = runBlocking {
    val supervisor = SupervisorJob()
    with(CoroutineScope(coroutineContext + supervisor)) {
        val firstChild = launch {
            println("First child throwing an exception")
            throw ArithmeticException()
        }
        val secondChild = launch {
            println("First child is canceled: ${firstChild.isCancelled}")
            try {
                delay(5000)
            } catch (e: CancellationException) {
                println("Second child cancelled because supervisor got cancelled.")
            }
        }
        firstChild.join()
        println("Second child is active: ${secondChild.isActive}")
        supervisor.cancel()
        secondChild.join()
    }
}