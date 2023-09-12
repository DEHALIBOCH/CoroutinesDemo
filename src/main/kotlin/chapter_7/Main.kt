package chapter_7

import kotlinx.coroutines.*
import java.util.concurrent.Executors

fun main() {
    val executorDispatcher = Executors
        .newWorkStealingPool()
        .asCoroutineDispatcher()

    GlobalScope.launch(context = executorDispatcher) {
        println(Thread.currentThread().name)
    }
    Thread.sleep(50)

}